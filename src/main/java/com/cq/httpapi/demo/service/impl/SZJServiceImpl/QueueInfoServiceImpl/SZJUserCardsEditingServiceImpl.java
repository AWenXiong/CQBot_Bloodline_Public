package com.cq.httpapi.demo.service.impl.SZJServiceImpl.QueueInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjusercardseditingDao;
import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.AddUserCardsEditingRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.DeletionUserCardsEditingRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.GetUserCardsEditingRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.QueueResponse.GetUserCardsEditingResponseData;
import com.cq.httpapi.demo.entity.SZJ.Szjusercardsediting;
import com.cq.httpapi.demo.entity.SZJ.Szjuserinfo;
import com.cq.httpapi.demo.exception.SZJException.QueueException.AddUserCardsEditingException;
import com.cq.httpapi.demo.exception.SZJException.QueueException.DeletionUserCardsEditingException;
import com.cq.httpapi.demo.exception.SZJException.QueueException.GetUserCardsEditingException;
import com.cq.httpapi.demo.service.SZJService.SZJUserCardsEditingService;
import com.cq.httpapi.demo.service.SZJService.SZJUserInfoService;
import org.springframework.stereotype.Service;

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
            throws GetUserCardsEditingException {
        String openId = request.getOpenid();
        if (openId == null || !szjUserInfoService.existOpenId(openId)) {
            throw new GetUserCardsEditingException(1, "登录码不存在！");
        }

        Long groupId = request.getGroupId();
        if (groupId == null) {
            throw new GetUserCardsEditingException(2, "卡组主键缺失！");
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
            throw new GetUserCardsEditingException(9, e.getMessage());
        }
    }

    @Override
    public boolean addUserCardsEditing(AddUserCardsEditingRequest request)
            throws AddUserCardsEditingException {
        String openId = request.getOpenid();
        if (openId == null || !szjUserInfoService.existOpenId(openId)) {
            throw new AddUserCardsEditingException(1, "登录码不存在！");
        }

        Long groupId = request.getGroupId();
        if (groupId == null) {
            throw new AddUserCardsEditingException(2, "卡组主键缺失");
        }

        Long cardInfoId = request.getCardInfoId();
        /*TODO*/

        Double originLevel = request.getOriginLevel();
        if (originLevel == null) {
            throw new AddUserCardsEditingException(3, "原始关数缺失！");
        }

        Double originPosition = request.getOriginPosition();
        if (originPosition == null) {
            throw new AddUserCardsEditingException(4, "原始位置缺失！");
        }

        try {
            Szjuserinfo userInfo = szjUserInfoService.getUserInfoByOpenId(openId);
            Long userId = userInfo.getId();
            szjusercardseditingDao.insertSzjitemscolor(userId, groupId, cardInfoId, originLevel, originPosition);
            return true;
        } catch (Exception e) {
            throw new AddUserCardsEditingException(9, e.getMessage());
        }
    }

    @Override
    public boolean deletionUserCardsEditing(DeletionUserCardsEditingRequest request)
            throws DeletionUserCardsEditingException {
        String openId = request.getOpenid();
        if (openId == null || !szjUserInfoService.existOpenId(openId)) {
            throw new DeletionUserCardsEditingException(1, "登录码不存在！");
        }

        Long id = request.getId();
        if (id == null) {
            throw new DeletionUserCardsEditingException(2, "主键缺失！");
        }

        try {
            szjusercardseditingDao.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new DeletionUserCardsEditingException(9, e.getMessage());
        }
    }
}
