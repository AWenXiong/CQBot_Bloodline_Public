package com.cq.httpapi.demo.service.impl.SZJServiceImpl.QueueInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjqueueinfoconfigDao;
import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.EditUserQueueInfoConfigRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.EditUserQueueInfoConfigRequestData;
import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.GetUserQueueInfoConfigRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.QueueResponse.GetUserQueueInfoConfigResponseData;
import com.cq.httpapi.demo.entity.SZJ.Szjqueueinfoconfig;
import com.cq.httpapi.demo.entity.SZJ.Szjuserinfo;
import com.cq.httpapi.demo.exception.SZJException.SZJErrorCode;
import com.cq.httpapi.demo.exception.SZJException.SZJException;
import com.cq.httpapi.demo.kit.TimeKit;
import com.cq.httpapi.demo.service.SZJService.SZJQueueInfoConfigSerivce;
import com.cq.httpapi.demo.service.SZJService.SZJUserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class SZJQueueInfoConfigImpl implements SZJQueueInfoConfigSerivce {

    @Resource
    private SzjqueueinfoconfigDao szjqueueinfoconfigDao;
    @Resource
    private SZJUserInfoService szjUserInfoService;

    @Override
    public ArrayList<GetUserQueueInfoConfigResponseData> getUserQueueInfoConfig(GetUserQueueInfoConfigRequest request)
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
            ArrayList<Szjqueueinfoconfig> datas = szjqueueinfoconfigDao.getUserQueueInfoConfig(userId, groupId);
            ArrayList<GetUserQueueInfoConfigResponseData> res = new ArrayList<>();
            for (Szjqueueinfoconfig data : datas) {
                res.add(new GetUserQueueInfoConfigResponseData(data));
            }
            return res;
        } catch (Exception e) {
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
    }

    @Override
    @Transactional
    public boolean editUserQueueInfoConfig(EditUserQueueInfoConfigRequest request)
            throws SZJException {
        String openId = request.getOpenid();
        if (openId == null || !szjUserInfoService.existOpenId(openId)) {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }

        Long groupId = request.getId();
        if (groupId == null) {
            throw new SZJException(SZJErrorCode.USER_CARD_GROUP_ID_LOST);
        }

        ArrayList<EditUserQueueInfoConfigRequestData> datas = request.getUserQueueInfoConfig();
        if (datas == null) {
            throw new SZJException(SZJErrorCode.ARGUMENT_NULL);
        }

        try {
            Szjuserinfo userInfo = szjUserInfoService.getUserInfoByOpenId(openId);
            Long userId = userInfo.getId();
            for (EditUserQueueInfoConfigRequestData data : datas) {
                Long configId = data.getId();
                if (configId == null || configId.intValue() == 0) { // 新增用户配队规则
                    String parameterCode = data.getParameterCode();
                    String parameterValue = data.getParameterValue();
                    if (parameterCode != null && parameterValue != null) {
                        szjqueueinfoconfigDao.insertUserQueueInfoConfig(userId, groupId, parameterCode, parameterValue);
                        Long newConfigId = szjqueueinfoconfigDao.getLastInsert(groupId);
                        szjqueueinfoconfigDao.updateCreateInfo(newConfigId, TimeKit.getFormalTime(), String.valueOf(userId), String.valueOf(userId));
                    } else {
                        throw new SZJException(SZJErrorCode.ARGUMENT_NULL);
                    }
                } else if (groupId.intValue() > 0) { // 编辑用户配队规则
                    String parameterCode = data.getParameterCode();
                    String parameterValue = data.getParameterValue();
                    boolean flag = false;
                    if (parameterCode != null) {
                        szjqueueinfoconfigDao.updateParameterCode(configId, parameterCode);
                        flag = true;
                    }

                    if (parameterValue != null) {
                        szjqueueinfoconfigDao.updateParameterValue(configId, parameterValue);
                        flag = true;
                    }

                    if (flag) {
                        szjqueueinfoconfigDao.updateModifyInfo(configId, TimeKit.getFormalTime(), String.valueOf(userId), String.valueOf(userId));
                        szjqueueinfoconfigDao.updateDescription(configId, "编辑配队规则信息");
                    }
                }
            }
            return true;
        } catch (SZJException e) {
            throw e;
        } catch (Exception e) {
            System.err.println(e);
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
    }

}
