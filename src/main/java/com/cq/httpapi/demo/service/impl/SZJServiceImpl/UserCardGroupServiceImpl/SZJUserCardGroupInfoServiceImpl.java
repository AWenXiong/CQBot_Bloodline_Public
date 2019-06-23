package com.cq.httpapi.demo.service.impl.SZJServiceImpl.UserCardGroupServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjusercardgroupinfoDao;
import com.cq.httpapi.demo.dao.SZJdao.SzjuserinfoDao;
import com.cq.httpapi.demo.dto.SZJ.Request.CardGroupRequest.*;
import com.cq.httpapi.demo.entity.SZJ.Szjusercardgroupinfo;
import com.cq.httpapi.demo.entity.SZJ.Szjuserinfo;
import com.cq.httpapi.demo.exception.SZJException.SZJErrorCode;
import com.cq.httpapi.demo.exception.SZJException.SZJException;
import com.cq.httpapi.demo.kit.TimeKit;
import com.cq.httpapi.demo.service.SZJService.SZJInvitationCodeService;
import com.cq.httpapi.demo.service.SZJService.SZJUserCardGroupInfoService;
import com.cq.httpapi.demo.service.SZJService.SZJUserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class SZJUserCardGroupInfoServiceImpl implements SZJUserCardGroupInfoService {

    @Resource
    private SzjusercardgroupinfoDao szjusercardgroupinfoDao;
    @Resource
    private SzjuserinfoDao szjuserinfoDao;
    @Resource
    private SZJUserInfoService szjUserInfoService;
    @Resource
    private SZJInvitationCodeService szjInvitationCodeService;

    @Override
    public ArrayList<Szjusercardgroupinfo> getAllGroups(GetAllGroupsRequest request) throws SZJException {
        // 检查登录码是否合法
        String openId = request.getOpenid();
        if (openId == null || openId.isEmpty() || !szjUserInfoService.existOpenId(openId)) {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }
        try {
            ArrayList<Szjusercardgroupinfo> groupInfos = this.getByOpenId(openId);
            return groupInfos;
        } catch (SZJException e) {
            throw new SZJException(e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            throw new SZJException(9, e.getMessage());
        }
    }


    @Override
    public Long createGroupInfo(CreateGroupRequest request) throws SZJException {
        // 检查登录码是否合法
        String openId = request.getOpenid();
        if (openId == null || openId.isEmpty() || !szjUserInfoService.existOpenId(openId)) {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }
        // 检查邀请码是否为空，若非空，检查邀请码是否合法
        String invitationCode = request.getInvitationCode();
        if (invitationCode != null && !invitationCode.isEmpty()) {
            if (!szjInvitationCodeService.existInvitationCode(invitationCode)) {
                throw new SZJException(SZJErrorCode.INVITATION_CODE_ERROR);
            }
        }

        // 检查卡组名称是否为空
        String name = request.getName();
        if (name == null || name.isEmpty()) {
            throw new SZJException(SZJErrorCode.ARGUMENT_NULL);
        }
        // 创建卡组
        try {
            Szjuserinfo user = szjuserinfoDao.getByOpenId(openId);
            long userId = user.getId();
            szjusercardgroupinfoDao.insertSZJUserCardGroupInfo(userId, name, invitationCode);
            long id = szjusercardgroupinfoDao.getLastInsert();
            szjusercardgroupinfoDao.updateCreateInfo(id, TimeKit.getFormalTime(),
                    String.valueOf(userId), String.valueOf(userId));
            return id;
        } catch (Exception e) {
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
    }

    @Override
    public boolean updateGroupInfo(UpdateGroupRequest request) throws SZJException {
        // 检查登录码是否合法
        String openId = request.getOpenid();
        if (openId == null || openId.isEmpty() || !szjUserInfoService.existOpenId(openId)) {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }
        // 检查邀请码是否为空，若非空，检查邀请码是否合法
        String invitationCode = request.getInvitationCode();
        if (invitationCode != null && !invitationCode.isEmpty()) {
            if (!szjInvitationCodeService.existInvitationCode(invitationCode)) {
                throw new SZJException(SZJErrorCode.INVITATION_CODE_ERROR);
            }
        }
        // 检查卡组名称是否为空
        String name = request.getName();
        if (name == null || name.isEmpty()) {
            throw new SZJException(SZJErrorCode.ARGUMENT_NULL);
        }
        // 检查卡组主键是否存在
        Long id = request.getId();
        if (id == null) {
            throw new SZJException(SZJErrorCode.ARGUMENT_NULL);
        }
        // 卡组编辑
        try {
            Szjuserinfo user = szjuserinfoDao.getByOpenId(openId);
            szjusercardgroupinfoDao.updateName(id, name);
            szjusercardgroupinfoDao.updateInvitationCode(id, invitationCode);
            szjusercardgroupinfoDao.updateModifyInfo(id, TimeKit.getFormalTime(),
                    String.valueOf(user.getId()), String.valueOf(user.getId()));
            szjusercardgroupinfoDao.updateDescription(id, "编辑卡组信息");
            return true;
        } catch (Exception e) {
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
    }

    @Override
    public Szjusercardgroupinfo getGroupInfo(GetGroupInfoRequest request) throws SZJException {
        // 检查登录码是否合法
        String openId = request.getOpenid();
        if (openId == null || openId.isEmpty() || !szjUserInfoService.existOpenId(openId)) {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }
        // 检查卡组主键是否存在
        Long id = request.getId();
        if (id == null) {
            throw new SZJException(SZJErrorCode.ARGUMENT_NULL);
        }
        // 获取卡组信息
        try {
            Szjusercardgroupinfo res = this.getById(id);
            return res;
        } catch (Exception e) {
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }

    }


    @Override
    public boolean delete(DeleteGroupRequest request) throws SZJException {
        // 检查登录码是否合法
        String openId = request.getOpenid();
        if (openId == null || openId.isEmpty() || !szjUserInfoService.existOpenId(openId)) {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }
        // 检查卡组主键是否为空
        Long id = request.getId();
        if (id == null) {
            throw new SZJException(SZJErrorCode.ARGUMENT_NULL);
        }
        // 卡组删除
        try {
            szjusercardgroupinfoDao.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
    }

    @Override
    public Szjusercardgroupinfo getById(Long id) {
        Szjusercardgroupinfo res = szjusercardgroupinfoDao.getById(id);
        return res;
    }

    @Override
    public ArrayList<Szjusercardgroupinfo> getByOpenId(String openId) {
        Szjuserinfo szjuserinfo = szjuserinfoDao.getByOpenId(openId);
        long userId = szjuserinfo.getId();
        ArrayList<Szjusercardgroupinfo> cardGroupInfos = szjusercardgroupinfoDao.getByUserId(userId);
        return cardGroupInfos;
    }
}
