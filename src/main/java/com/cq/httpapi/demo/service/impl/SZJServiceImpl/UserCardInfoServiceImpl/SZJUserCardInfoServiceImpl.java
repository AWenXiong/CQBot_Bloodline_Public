package com.cq.httpapi.demo.service.impl.SZJServiceImpl.UserCardInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjusercardinfoDao;
import com.cq.httpapi.demo.dto.SZJ.Request.BackEndDataRequest.BatchAddUserCardsData;
import com.cq.httpapi.demo.dto.SZJ.Request.BackEndDataRequest.BatchAddUserCardsRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserCardInfoRequest.DeleteUserCardRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserCardInfoRequest.EditUserCardsInfoData;
import com.cq.httpapi.demo.dto.SZJ.Request.UserCardInfoRequest.EditUserCardsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserCardInfoRequest.GetUserCardsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.BackEndDataResponse.BatchAddUserCardsResponse;
import com.cq.httpapi.demo.dto.SZJ.Response.UserCardInfoResponse.GetUserCardsInfoResponseData;
import com.cq.httpapi.demo.entity.SZJ.Szjusercardinfo;
import com.cq.httpapi.demo.entity.SZJ.Szjuserinfo;
import com.cq.httpapi.demo.exception.SZJException.SZJErrorCode;
import com.cq.httpapi.demo.exception.SZJException.SZJException;
import com.cq.httpapi.demo.kit.TimeKit;
import com.cq.httpapi.demo.service.SZJService.SZJQueueInfoService;
import com.cq.httpapi.demo.service.SZJService.SZJUserCardInfoService;
import com.cq.httpapi.demo.service.SZJService.SZJUserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class SZJUserCardInfoServiceImpl implements SZJUserCardInfoService {

    @Resource
    private SZJUserInfoService szjUserInfoService;
    @Resource
    private SzjusercardinfoDao szjusercardinfoDao;
    @Resource
    private SZJQueueInfoService szjQueueInfoService;

    @Override
    public ArrayList<GetUserCardsInfoResponseData> getCardInfo(GetUserCardsInfoRequest request)
            throws SZJException {
        String openId = request.getOpenid();
        if (openId == null || !szjUserInfoService.existOpenId(openId)) {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }

        Long groupId = request.getId();
        if (groupId == null) {
            throw new SZJException(SZJErrorCode.ARGUMENT_NULL);
        }

        Szjuserinfo szjuserinfo = szjUserInfoService.getUserInfoByOpenId(openId);
        if (szjuserinfo == null) {
            throw new SZJException(SZJErrorCode.GET_USER_INFO_FAILURE);
        }

        try {
            long userId = szjuserinfo.getId();
            ArrayList<Szjusercardinfo> datas = szjusercardinfoDao.getByUserIdAndGroupId(userId, groupId);
            ArrayList<GetUserCardsInfoResponseData> res = new ArrayList<>();
            for (Szjusercardinfo data : datas) {
                res.add(new GetUserCardsInfoResponseData(data));
            }
            return res;
        } catch (Exception e) {
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
    }

    @Override
    @Transactional
    public boolean updateCardInfo(EditUserCardsInfoRequest request)
            throws SZJException {
        String openId = request.getOpenid();
        if (openId == null || !szjUserInfoService.existOpenId(openId)) {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }

        Long groupId = request.getId();
        if (groupId == null) {
            throw new SZJException(SZJErrorCode.USER_CARD_GROUP_ID_LOST);
        }

        ArrayList<EditUserCardsInfoData> cardInfo = request.getUserCardInfo();
        if (cardInfo == null) {
            throw new SZJException(SZJErrorCode.ARGUMENT_NULL);
        }

        try {
            Szjuserinfo userInfo = szjUserInfoService.getUserInfoByOpenId(openId);
            long userId = userInfo.getId();
            for (EditUserCardsInfoData data : cardInfo) {
                Long cardId = data.getId();
                if (cardId == null || cardId.intValue() == 0) { // 若cardId为 null/0 表示新增卡片
                    Long cardInfoId = data.getCardInfoId();
                    int exists = szjusercardinfoDao.exists(userId, groupId, cardInfoId);
                    if (exists < 1) {
                        Long fightingCapacity = data.getFightingCapacity();
                        Long fate = data.getFate();
                        Long isGodofWar = data.getIsGodofWar();
                        if (fightingCapacity != null && fate != null && isGodofWar != null) {
                            szjusercardinfoDao.insertSzjusercardinfo(cardInfoId, userId, groupId, fightingCapacity, fate, isGodofWar);
                            long id = szjusercardinfoDao.getLastInsert(groupId);
                            szjusercardinfoDao.updateCreateInfo(id, TimeKit.getFormalTime(), String.valueOf(userId), String.valueOf(userId));
                            szjusercardinfoDao.updateDescription(id, "增加卡牌信息");
                        } else {
                            throw new SZJException(SZJErrorCode.USER_CARD_INFO_LOST);
                        }
                    } else {
                        throw new SZJException(SZJErrorCode.INSERT_CARD_INFO_ERROR);
                    }
                } else if (cardId.intValue() > 0) { // 否则 表示编辑卡片
                    Long cardInfoId = data.getCardInfoId();
                    Long fightingCapacity = data.getFightingCapacity();
                    Long fate = data.getFate();
                    Long isGodofWar = data.getIsGodofWar();
                    boolean flag = false;
                    /*TODO 需要修改SQL，然后修改DAO层的接口*/
                    if (cardInfo != null) {
                        szjusercardinfoDao.updateCardInfoId(cardId, cardInfoId);
                        flag = true;
                    }
                    if (fightingCapacity != null) {
                        szjusercardinfoDao.updateFightingCapacity(cardId, fightingCapacity);
                        flag = true;
                    }
                    if (fate != null) {
                        szjusercardinfoDao.updateFate(cardId, fate);
                        flag = true;
                    }
                    if (isGodofWar != null) {
                        szjusercardinfoDao.updateIsGodOfWar(cardId, isGodofWar);
                        flag = true;
                    }
                    if (flag) {
                        szjusercardinfoDao.updateModifyInfo(cardId, TimeKit.getFormalTime(), String.valueOf(userId), String.valueOf(userId));
                        szjusercardinfoDao.updateDescription(cardId, "编辑卡牌信息");
                    }
                }
            }
            return true;
        } catch (SZJException e) {
            throw e;
        } catch (Exception e) {
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
    }

    @Override
    @Transactional
    public boolean deleteCardInfo(DeleteUserCardRequest request) throws SZJException {
        String openId = request.getOpenid();
        if (openId == null || !szjUserInfoService.existOpenId(openId)) {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }

        Long id = request.getId();
        if (id == null) {
            throw new SZJException(SZJErrorCode.USER_CARD_ID_LOST);
        }

        try {
            Szjuserinfo userInfo = szjUserInfoService.getUserInfoByOpenId(openId);
            Long userId = userInfo.getId();
            szjusercardinfoDao.deleteById(id);
            szjusercardinfoDao.updateModifyInfo(id, TimeKit.getFormalTime(), String.valueOf(userId), String.valueOf(userId));
            szjusercardinfoDao.updateDescription(id, "删除卡牌信息");
            return true;
        } catch (Exception e) {
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
    }

    @Override
    @Transactional
    public BatchAddUserCardsResponse batchAddUserCards(BatchAddUserCardsRequest request) {
        BatchAddUserCardsResponse response = new BatchAddUserCardsResponse();
        String openId = request.getOpenId();
        if (!szjUserInfoService.existOpenId(openId)) {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }
        Long groupId = request.getGroupId();
        ArrayList<BatchAddUserCardsData> cards = request.getUserCards();
        Long userId = szjUserInfoService.getUserInfoByOpenId(openId).getId();

        // 删除用户卡组中原有的卡牌信息
        ArrayList<Szjusercardinfo> cardInfos = szjusercardinfoDao.getByUserIdAndGroupId(userId, groupId);
        for (Szjusercardinfo cardInfo : cardInfos) {
            szjusercardinfoDao.deleteById(cardInfo.getId());
            szjusercardinfoDao.updateModifyInfo(cardInfo.getId(), TimeKit.getFormalTime(), "admin", "batch");
            szjusercardinfoDao.updateDescription(cardInfo.getId(), "数据迁移-删除原有卡牌信息");
        }

        // 插入新的数据
        for (BatchAddUserCardsData card : cards) {
            szjusercardinfoDao.insertSzjusercardinfo(card.getCardInfoId(), userId, groupId,
                    card.getFightingCapacity(), card.getFate(), card.getIsGodOfWar());
            long newCardInfoId = szjusercardinfoDao.getLastInsert(groupId);
            szjusercardinfoDao.updateCreateInfo(newCardInfoId, TimeKit.getFormalTime(), "admin", "batch");
            szjusercardinfoDao.updateDescription(newCardInfoId, "数据迁移-导入数据");
        }

        // 逻辑删除原有的配队
        if (!szjQueueInfoService.deleteQueueInfo(userId, groupId)) {
            throw new SZJException(SZJErrorCode.BATCH_ADD_DELETE_QUEUE_INFO_ERROR);
        }

        return response;
    }
}
