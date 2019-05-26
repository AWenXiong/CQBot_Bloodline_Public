package com.cq.httpapi.demo.service.impl.SZJServiceImpl.QueueInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjqueuecardDao;
import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.EditUserQueueCardRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.EditUserQueueCardRequestData;
import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.GetQueueInfoCardRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.QueueResponse.GetQueueInfoCardResponseData;
import com.cq.httpapi.demo.dto.SZJ.Response.QueueResponse.GetQueueInfoCardResponseDataCard;
import com.cq.httpapi.demo.entity.SZJ.Szjqueuecard;
import com.cq.httpapi.demo.entity.SZJ.Szjqueueinfo;
import com.cq.httpapi.demo.entity.SZJ.Szjqueuelevel;
import com.cq.httpapi.demo.entity.SZJ.Szjuserinfo;
import com.cq.httpapi.demo.exception.SZJException.SZJErrorCode;
import com.cq.httpapi.demo.exception.SZJException.SZJException;
import com.cq.httpapi.demo.kit.TimeKit;
import com.cq.httpapi.demo.service.SZJService.SZJQueueCardService;
import com.cq.httpapi.demo.service.SZJService.SZJQueueInfoService;
import com.cq.httpapi.demo.service.SZJService.SZJQueueLevelService;
import com.cq.httpapi.demo.service.SZJService.SZJUserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class SZJQueueCardServiceImpl implements SZJQueueCardService {

    @Resource
    private SzjqueuecardDao szjqueuecardDao;
    @Resource
    private SZJUserInfoService szjUserInfoService;
    @Resource
    private SZJQueueInfoService szjQueueInfoService;
    @Resource
    private SZJQueueLevelService szjQueueLevelService;

    @Override
    @Transactional
    public Long insertSzjqueuecard(Long userId, Long queueInfoId, Double level, Long cardInfoId, Double position) {
        try {
            // 插入新的数据到 SZJQueueCard
            szjqueuecardDao.insertSzjqueuecard(queueInfoId, level, cardInfoId, position);
            Long newQueueCardId = szjqueuecardDao.getLastInsertByQueueInfoId(queueInfoId);
            // 更新相关信息
            szjqueuecardDao.updateCreateInfo(newQueueCardId, TimeKit.getFormalTime(), String.valueOf(userId), String.valueOf(userId));
            szjqueuecardDao.updateDescription(newQueueCardId, "新增新的自动配队");
            return newQueueCardId;
        } catch (Exception e) {
            System.err.println(e);
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
    }

    @Override
    public ArrayList<GetQueueInfoCardResponseData> getQueueInfoCard(GetQueueInfoCardRequest request)
            throws SZJException {
        String openId = request.getOpenid();
        if (openId == null || !szjUserInfoService.existOpenId(openId)) {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }

        Long groupId = request.getGroupId();
        if (groupId == null) {
            throw new SZJException(SZJErrorCode.USER_CARD_GROUP_ID_LOST);
        }

        try {
            ArrayList<GetQueueInfoCardResponseData> res = new ArrayList<>();
            // 获取用户信息
            Szjuserinfo userInfo = szjUserInfoService.getUserInfoByOpenId(openId);
            Long userId = userInfo.getId();

            // 获取启用的阵容配置信息
            Szjqueueinfo queueInfo = szjQueueInfoService.getQueueInfo(userId, groupId);
            Long queueInfoId = queueInfo.getId();

            // 获取相应的阵容关信息，获取法阵配置信息
            ArrayList<Szjqueuelevel> levelInfos = szjQueueLevelService.getQueueLevel(queueInfoId);

            // 获取相应的阵容卡信息
            for (int i = 0; i < levelInfos.size(); i++) {
                Szjqueuelevel levelInfo = levelInfos.get(i);
                ArrayList<Szjqueuecard> cards = szjqueuecardDao.getQueueInfoCard(queueInfoId, levelInfo.getLevel());
                ArrayList<GetQueueInfoCardResponseDataCard> cards1 = new ArrayList<>();
                for (Szjqueuecard card : cards) {
                    cards1.add(new GetQueueInfoCardResponseDataCard(card));
                }
                GetQueueInfoCardResponseData data = new GetQueueInfoCardResponseData();
                data.setLevel(levelInfo.getLevel());
                data.setUserSpellInfo(levelInfo.getUserSpellInfo());
                data.setCards(cards1);
                res.add(data);
            }
            return res;
        } catch (SZJException e) {
            throw e;
        } catch (Exception e) {
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
    }

    @Override
    @Transactional
    public boolean editUserQueueCard(EditUserQueueCardRequest request) throws SZJException {
        String openId = request.getOpenid();
        if (openId == null || !szjUserInfoService.existOpenId(openId)) {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }

        Long queueInfoId = request.getId();
        if (queueInfoId == null) {
            throw new SZJException(SZJErrorCode.QUEUE_INFO_ID_LOST);
        }

        ArrayList<EditUserQueueCardRequestData> datas = request.getUserQueueCard();
        if (datas == null) {
            throw new SZJException(SZJErrorCode.ARGUMENT_NULL);
        }

        try {
            Szjuserinfo userInfo = szjUserInfoService.getUserInfoByOpenId(openId);
            Long userId = userInfo.getId();
            for (EditUserQueueCardRequestData data : datas) {
                Long queueCardId = data.getId();
                Double level = data.getLevel();
                Long cardInfoId = data.getCardInfoId();
                Double position = data.getPosition();

                if (queueCardId == null || queueCardId.intValue() == 0) { // 新增用户配队卡牌信息
                    this.insertSzjqueuecard(userId, queueInfoId, level, cardInfoId, position);
                } else { // 编辑用户配队卡牌信息
                    szjqueuecardDao.updateLevel(queueCardId, level);
                    szjqueuecardDao.updateCardInfoId(queueCardId, cardInfoId);
                    szjqueuecardDao.updatePosition(queueCardId, position);
                }
            }
            return true;
        } catch (SZJException e) {
            System.err.println(e);
            throw e;
        } catch (Exception e) {
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
    }
}
