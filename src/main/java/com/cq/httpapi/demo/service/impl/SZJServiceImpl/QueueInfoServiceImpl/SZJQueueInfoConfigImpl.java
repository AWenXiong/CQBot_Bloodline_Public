package com.cq.httpapi.demo.service.impl.SZJServiceImpl.QueueInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjqueueinfoconfigDao;
import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.EditUserQueueInfoConfigRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.EditUserQueueInfoConfigRequestData;
import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.GetUserQueueInfoConfigRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.QueueResponse.GetUserQueueInfoConfigResponseData;
import com.cq.httpapi.demo.entity.SZJ.Szjqueueinfoconfig;
import com.cq.httpapi.demo.entity.SZJ.Szjuserinfo;
import com.cq.httpapi.demo.exception.SZJException.QueueException.EditUserQueueInfoConfigException;
import com.cq.httpapi.demo.exception.SZJException.QueueException.GetUserQueueInfoConfigException;
import com.cq.httpapi.demo.kit.TimeKit;
import com.cq.httpapi.demo.service.SZJService.SZJQueueInfoConfigSerivce;
import com.cq.httpapi.demo.service.SZJService.SZJUserInfoService;
import org.springframework.stereotype.Service;

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
            throws GetUserQueueInfoConfigException {
        String openId = request.getOpenid();
        if (openId == null || !szjUserInfoService.existOpenId(openId)) {
            throw new GetUserQueueInfoConfigException(1, "登录码不存在！");
        }

        Long groupId = request.getGroupId();
        if (groupId == null) {
            throw new GetUserQueueInfoConfigException(2, "卡组主键缺失！");
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
            throw new GetUserQueueInfoConfigException(9, e.getMessage());
        }
    }

    @Override
    public boolean editUserQueueInfoConfig(EditUserQueueInfoConfigRequest request)
            throws EditUserQueueInfoConfigException {
        String openId = request.getOpenid();
        if (openId == null || !szjUserInfoService.existOpenId(openId)) {
            throw new EditUserQueueInfoConfigException(1, "登录码不存在！");
        }

        Long groupId = request.getId();
        if (groupId == null) {
            throw new EditUserQueueInfoConfigException(2, "卡组主键缺失！");
        }

        try {
            Szjuserinfo userInfo = szjUserInfoService.getUserInfoByOpenId(openId);
            Long userId = userInfo.getId();
            ArrayList<EditUserQueueInfoConfigRequestData> datas = request.getUserQueueInfoConfig();
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
                        throw new EditUserQueueInfoConfigException(3, "配队规则参数缺失！");
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
        } catch (Exception e) {
            throw new EditUserQueueInfoConfigException(9, e.getMessage());
        }
    }

}
