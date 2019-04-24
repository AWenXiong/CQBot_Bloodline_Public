package com.cq.httpapi.demo.service.impl.SZJServiceImpl.UserCardGroupServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjusercardgroupinfoDao;
import com.cq.httpapi.demo.dao.SZJdao.SzjuserinfoDao;
import com.cq.httpapi.demo.dto.SZJ.Request.CardGroupRequest.*;
import com.cq.httpapi.demo.entity.SZJ.Szjusercardgroupinfo;
import com.cq.httpapi.demo.entity.SZJ.Szjuserinfo;
import com.cq.httpapi.demo.exception.SZJException.UserGroupInfoException.*;
import com.cq.httpapi.demo.kit.TimeKit;
import com.cq.httpapi.demo.service.SZJInvitationCodeService;
import com.cq.httpapi.demo.service.SZJUserCardGroupInfoService;
import com.cq.httpapi.demo.service.SZJUserInfoService;
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
    public ArrayList<Szjusercardgroupinfo> getAllGroups(GetAllGroupsRequest request) throws GetAllGroupsException {
        // 检查登录码是否合法
        String openId = request.getOpenid();
        if (openId == null || openId.isEmpty() || !szjUserInfoService.existOpenId(openId)) {
            throw new GetAllGroupsException(1, "登录码不存在！");
        }

        try {
            ArrayList<Szjusercardgroupinfo> groupInfos = this.getByOpenId(openId);
            return groupInfos;
        } catch (Exception e) {
            throw new GetAllGroupsException(9, e.getMessage());
        }
    }

    /**
     * 卡组创建
     *
     * @param request
     * @return
     * @throws CreateUserCardGroupException errorCode     message
     *                                      1            登录码不存在！
     *                                      2            邀请码不存在！
     *                                      3            卡组名称为空！
     *                                      9            未知错误！
     */
    @Override
    public Long createGroupInfo(CreateGroupRequest request) throws CreateUserCardGroupException {
        // 检查登录码是否合法
        String openId = request.getOpenid();
        if (openId == null || openId.isEmpty() || !szjUserInfoService.existOpenId(openId)) {
            throw new CreateUserCardGroupException(1, "登录码不存在！");
        }
        // 检查邀请码是否为空，若非空，检查邀请码是否合法
        String invitationCode = request.getInvitationCode();
        if (invitationCode != null && !invitationCode.isEmpty()) {
            if (!szjInvitationCodeService.existInvitationCode(invitationCode)) {
                throw new CreateUserCardGroupException(2, "邀请码不存在！");
            }
        }
        // 检查卡组名称是否为空
        String name = request.getName();
        if (name == null || name.isEmpty()) {
            throw new CreateUserCardGroupException(3, "卡组名称为空！");
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
            throw new CreateUserCardGroupException(9, e.getMessage());
        }

    }


    /**
     * 卡组编辑
     *
     * @param request
     * @return
     * @throws UpdateUserCardGroupException errorCode    message
     *                                      1            登录码不存在！
     *                                      2            邀请码不存在！
     *                                      3            卡组名称为空！
     *                                      4            卡组主键缺失！
     *                                      9            未知错误！
     */
    @Override
    public boolean updateGroupInfo(UpdateGroupRequest request) throws UpdateUserCardGroupException {
        // 检查登录码是否合法
        String openId = request.getOpenid();
        if (openId == null || openId.isEmpty() || !szjUserInfoService.existOpenId(openId)) {
            throw new UpdateUserCardGroupException(1, "登录码不存在！");
        }
        // 检查邀请码是否为空，若非空，检查邀请码是否合法
        String invitationCode = request.getInvitationCode();
        if (invitationCode != null && !invitationCode.isEmpty()) {
            if (!szjInvitationCodeService.existInvitationCode(invitationCode)) {
                throw new UpdateUserCardGroupException(2, "邀请码不存在！");
            }
        }
        // 检查卡组名称是否为空
        String name = request.getName();
        if (name == null || name.isEmpty()) {
            throw new UpdateUserCardGroupException(3, "卡组名称为空！");
        }
        // 检查卡组主键是否存在
        Long id = request.getId();
        if (id == null) {
            throw new UpdateUserCardGroupException(4, "卡组主键缺失！");
        }
        // 卡组编辑
        try {
            Szjuserinfo user = szjuserinfoDao.getByOpenId(openId);
            szjusercardgroupinfoDao.updateName(id, name);
            szjusercardgroupinfoDao.updateModifyInfo(id, TimeKit.getFormalTime(),
                    String.valueOf(user.getId()), String.valueOf(user.getId()));
            return true;
        } catch (Exception e) {
            throw new UpdateUserCardGroupException(9, e.getMessage());
        }
    }

    @Override
    public Szjusercardgroupinfo getGroupInfo(GetGroupInfoRequest request) throws GetGroupInfoException {
        // 检查登录码是否合法
        String openId = request.getOpenid();
        if (openId == null || openId.isEmpty() || !szjUserInfoService.existOpenId(openId)) {
            throw new GetGroupInfoException(1, "登录码不存在！");
        }
        // 检查卡组主键是否存在
        Long id = request.getId();
        if (id == null) {
            throw new GetGroupInfoException(2, "卡组主键缺失！");
        }
        // 获取卡组信息
        try {
            Szjusercardgroupinfo res = this.getById(id);
            return res;
        } catch (Exception e) {
            throw new GetGroupInfoException(9, e.getMessage());
        }

    }

    /**
     * 卡组删除（逻辑删除）
     *
     * @param request
     * @return
     * @throws DeleteUserCardGroupException errorCode    message
     *                                      1            登录码不存在！
     *                                      2            卡组主键缺失！
     *                                      9            未知错误！
     */
    @Override
    public boolean delete(DeleteGroupRequest request) throws DeleteUserCardGroupException {
        // 检查登录码是否合法
        String openId = request.getOpenid();
        if (openId == null || openId.isEmpty() || !szjUserInfoService.existOpenId(openId)) {
            throw new DeleteUserCardGroupException(1, "登录码不存在！");
        }
        // 检查卡组主键是否为空
        Long id = request.getId();
        if (id == null) {
            throw new DeleteUserCardGroupException(2, "卡组主键缺失！");
        }
        // 卡组删除
        try {
            szjusercardgroupinfoDao.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new DeleteUserCardGroupException(9, e.getMessage());
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
