package com.cq.httpapi.demo.service.impl.SZJServiceImpl.UserCardInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjusercardinfoDao;
import com.cq.httpapi.demo.dto.SZJ.Request.UserCardInfoRequest.DeleteUserCardRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserCardInfoRequest.EditUserCardsInfoData;
import com.cq.httpapi.demo.dto.SZJ.Request.UserCardInfoRequest.EditUserCardsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserCardInfoRequest.GetUserCardsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.UserCardInfoResponse.GetUserCardsInfoResponseData;
import com.cq.httpapi.demo.entity.SZJ.Szjusercardinfo;
import com.cq.httpapi.demo.entity.SZJ.Szjuserinfo;
import com.cq.httpapi.demo.exception.SZJException.UserCardInfoException.DeleteUserCardException;
import com.cq.httpapi.demo.exception.SZJException.UserCardInfoException.GetUserCardsInfoException;
import com.cq.httpapi.demo.exception.SZJException.UserCardInfoException.UpdateUserCardsInfoExcpetion;
import com.cq.httpapi.demo.kit.TimeKit;
import com.cq.httpapi.demo.service.SZJService.SZJUserCardInfoService;
import com.cq.httpapi.demo.service.SZJService.SZJUserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class SZJUserCardInfoServiceImpl implements SZJUserCardInfoService {

    @Resource
    private SZJUserInfoService szjUserInfoService;
    @Resource
    private SzjusercardinfoDao szjusercardinfoDao;

    @Override
    public ArrayList<GetUserCardsInfoResponseData> getCardInfo(GetUserCardsInfoRequest request)
            throws GetUserCardsInfoException {
        String openId = request.getOpenid();
        if (openId == null || !szjUserInfoService.existOpenId(openId)) {
            throw new GetUserCardsInfoException(1, "登录码不存在！");
        }

        Long groupId = request.getId();
        if (groupId == null) {
            throw new GetUserCardsInfoException(2, "卡组主键缺失！");
        }

        Szjuserinfo szjuserinfo = szjUserInfoService.getUserInfoByOpenId(openId);
        if (szjuserinfo == null) {
            throw new GetUserCardsInfoException(3, "获取用户失败！");
        }

        try {
            long userId = szjuserinfo.getId();
            ArrayList<Szjusercardinfo> datas = szjusercardinfoDao.getByUserId(userId, groupId);
            ArrayList<GetUserCardsInfoResponseData> res = new ArrayList<>();
            for (Szjusercardinfo data : datas) {
                res.add(new GetUserCardsInfoResponseData(data));
            }
            return res;
        } catch (Exception e) {
            throw new GetUserCardsInfoException(9, e.getMessage());
        }
    }

    /*TODO 需要详尽的测试！！！！*/
    @Override
    public boolean updateCardInfo(EditUserCardsInfoRequest request)
            throws UpdateUserCardsInfoExcpetion {
        String openId = request.getOpenid();
        if (openId == null || !szjUserInfoService.existOpenId(openId)) {
            throw new UpdateUserCardsInfoExcpetion(1, "登录码不存在！");
        }

        Long groupId = request.getId();
        if (groupId == null) {
            throw new UpdateUserCardsInfoExcpetion(2, "卡组主键缺失！");
        }

        try {
            ArrayList<EditUserCardsInfoData> datas = request.getUserCardInfo();
            Szjuserinfo userInfo = szjUserInfoService.getUserInfoByOpenId(openId);
            long userId = userInfo.getId();
            for (EditUserCardsInfoData data : datas) {
                Long cardId = data.getCardInfoId();
                /*TODO 如果cardId为""会怎么样*/
                if (cardId == null || cardId.intValue() == 0) { // 新增卡片
                    Long fightingCapacity = data.getFightingCapacity();
                    Long fate = data.getFate();
                    Long isGodofWar = data.getIsGodofWar();
                    if (fightingCapacity != null && fate != null && isGodofWar != null) {
                        szjusercardinfoDao.insertSzjusercardinfo(cardId, userId, groupId, fightingCapacity, fate, isGodofWar);
                        long id = szjusercardinfoDao.getLastInsert(groupId);
                        szjusercardinfoDao.updateCreateInfo(id, TimeKit.getFormalTime(), String.valueOf(userId), String.valueOf(userId));
                        szjusercardinfoDao.updateDescription(id, "增加卡牌信息");
                    } else {
                        throw new UpdateUserCardsInfoExcpetion(3, "卡牌数据缺失！");
                    }
                } else if (cardId.intValue() > 0) { // 编辑卡片
                    Long fightingCapacity = data.getFightingCapacity();
                    Long fate = data.getFate();
                    Long isGodofWar = data.getIsGodofWar();
                    boolean flag = false;
                    if (fightingCapacity != null && fightingCapacity.intValue() > 0) {
                        szjusercardinfoDao.updateFightingCapacity(cardId, fightingCapacity);
                        flag = true;
                    }
                    if (fate != null && fate.intValue() > -1) {
                        szjusercardinfoDao.updateFate(cardId, fate);
                        flag = true;
                    }
                    if (isGodofWar != null && isGodofWar.intValue() > -1) {
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
        } catch (Exception e) {
            throw new UpdateUserCardsInfoExcpetion(9, e.getMessage());
        }
    }

    @Override
    public boolean deleteCardInfo(DeleteUserCardRequest request) throws DeleteUserCardException {
        String openId = request.getOpenid();
        if (openId == null || !szjUserInfoService.existOpenId(openId)) {
            throw new DeleteUserCardException(1, "登录码不存在！");
        }

        Long id = request.getId();
        if (id == null) {
            throw new DeleteUserCardException(2, "卡牌主键缺失！");
        }

        try {
            Szjuserinfo userInfo = szjUserInfoService.getUserInfoByOpenId(openId);
            Long userId = userInfo.getId();
            szjusercardinfoDao.deleteById(id);
            szjusercardinfoDao.updateModifyInfo(id, TimeKit.getFormalTime(), String.valueOf(userId), String.valueOf(userId));
            szjusercardinfoDao.updateDescription(id, "删除卡牌信息");
            return true;
        } catch (Exception e) {
            throw new DeleteUserCardException(9, e.getMessage());
        }
    }
}
