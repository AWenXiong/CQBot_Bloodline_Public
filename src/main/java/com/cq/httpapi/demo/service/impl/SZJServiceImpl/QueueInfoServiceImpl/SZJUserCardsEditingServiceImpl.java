package com.cq.httpapi.demo.service.impl.SZJServiceImpl.QueueInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjusercardseditingDao;
import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.AddUserCardsEditingRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.DeletionUserCardsEditingRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.GetUserCardsEditingRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.QueueResponse.GetUserCardsEditingResponseData;
import com.cq.httpapi.demo.entity.SZJ.Szjusercardsediting;
import com.cq.httpapi.demo.entity.SZJ.Szjuserinfo;
import com.cq.httpapi.demo.exception.SZJException.SZJErrorCode;
import com.cq.httpapi.demo.exception.SZJException.SZJException;
import com.cq.httpapi.demo.service.SZJService.SZJUserCardsEditingService;
import com.cq.httpapi.demo.service.SZJService.SZJUserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class SZJUserCardsEditingServiceImpl implements SZJUserCardsEditingService {

    @Resource
    private SzjusercardseditingDao szjusercardseditingDao;
    @Resource
    private SZJUserInfoService szjUserInfoService;

    @Override
    public ArrayList<GetUserCardsEditingResponseData> getUserCardsEditing(GetUserCardsEditingRequest request)
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
            Szjuserinfo userInfo = szjUserInfoService.getUserInfoByOpenId(openId);
            Long userId = userInfo.getId();
            ArrayList<GetUserCardsEditingResponseData> res = new ArrayList<>();
            ArrayList<Szjusercardsediting> datas = szjusercardseditingDao.getUserCardsEditing(userId, groupId);
            for (Szjusercardsediting data : datas) {
                res.add(new GetUserCardsEditingResponseData(data));
            }
            return res;
        } catch (Exception e) {
            throw new SZJException(9, e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean addUserCardsEditing(AddUserCardsEditingRequest request)
            throws SZJException {
        String openId = request.getOpenid();
        if (openId == null || !szjUserInfoService.existOpenId(openId)) {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }

        Long groupId = request.getGroupId();
        if (groupId == null) {
            throw new SZJException(SZJErrorCode.USER_CARD_GROUP_ID_LOST);
        }

        Long cardInfoId = request.getCardInfoId();
        if (cardInfoId == null) {
            throw new SZJException(SZJErrorCode.USER_CARD_ID_LOST);
        }

        Double originLevel = request.getOriginLevel();
        if (originLevel == null) {
            throw new SZJException(SZJErrorCode.INITIAL_LEVEL_LOST);
        }

        Double originPosition = request.getOriginPosition();
        if (originPosition == null) {
            throw new SZJException(SZJErrorCode.INITIAL_POSITION_LOST);
        }

        try {
            Szjuserinfo userInfo = szjUserInfoService.getUserInfoByOpenId(openId);
            Long userId = userInfo.getId();
            szjusercardseditingDao.insertSzjitemscolor(userId, groupId, cardInfoId, originLevel, originPosition);
            return true;
        } catch (Exception e) {
            System.err.println(e);
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
    }

    @Override
    @Transactional
    public boolean deletionUserCardsEditing(DeletionUserCardsEditingRequest request)
            throws SZJException {
        String openId = request.getOpenid();
        if (openId == null || !szjUserInfoService.existOpenId(openId)) {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }

        Long id = request.getId();
        if (id == null) {
            throw new SZJException(SZJErrorCode.EDITING_CARD_ID_LOST);
        }

        try {
            szjusercardseditingDao.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
    }
}
