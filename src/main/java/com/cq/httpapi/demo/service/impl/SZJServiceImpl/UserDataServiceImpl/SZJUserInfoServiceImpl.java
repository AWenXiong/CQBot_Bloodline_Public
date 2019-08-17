package com.cq.httpapi.demo.service.impl.SZJServiceImpl.UserDataServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjuserinfoDao;
import com.cq.httpapi.demo.dto.SZJ.Request.UserLoginRequest.BindingWechatRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserLoginRequest.GetUserInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserLoginRequest.UserLoginRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserLoginRequest.UserRegisterRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.UserLoginResponse.UserLoginResponseData;
import com.cq.httpapi.demo.entity.SZJ.Szjuserinfo;
import com.cq.httpapi.demo.exception.SZJException.SZJErrorCode;
import com.cq.httpapi.demo.exception.SZJException.SZJException;
import com.cq.httpapi.demo.kit.PasswordKit;
import com.cq.httpapi.demo.kit.TimeKit;
import com.cq.httpapi.demo.kit.UUIDKit;
import com.cq.httpapi.demo.service.SZJService.SZJUserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class SZJUserInfoServiceImpl implements SZJUserInfoService {

    @Resource
    SzjuserinfoDao szjuserinfoDao;

    @Override
    @Transactional
    public long regist(UserRegisterRequest userRegisterRequest) throws SZJException {
        String code = userRegisterRequest.getCode();
        String password = PasswordKit.encode(userRegisterRequest.getPassword());
        String userName = userRegisterRequest.getName();

        int errorMessage = 0;
        if (code == null || code.isEmpty()) {
            errorMessage += 1;
        }
        if (password == null || password.isEmpty()) {
            errorMessage += 2;
        }
        if (userName == null || userName.isEmpty()) {
            errorMessage += 4;
        }
        if (errorMessage != 0) {
            throw new SZJException(SZJErrorCode.ARGUMENT_NULL);
        } else if (existCode(code)) {
            throw new SZJException(SZJErrorCode.ACCOUNT_ALREADY_EXIST);
        } else {
            try {
                String openId = UUIDKit.formUUID();
                szjuserinfoDao.insertSzjuserinfo(code, password, userName, openId);
                long id = szjuserinfoDao.getIdByCode(code);
                String mobile = userRegisterRequest.getMobile();
                String email = userRegisterRequest.getEmail();
                // 如果电话不为空，则修改电话
                if (mobile != null && !mobile.isEmpty()) {
                    szjuserinfoDao.updateMobile(id, mobile);
                }
                // 若邮箱地址不为空，则修改邮箱地址
                if (email != null && !email.isEmpty()) {
                    szjuserinfoDao.updateMobile(id, email);
                }
                szjuserinfoDao.updateCreateInfo(id, TimeKit.getFormalTime(), "register", "register");
                return id;
            } catch (Exception e) {
                throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
            }
        }
    }


    @Override
    @Transactional
    public UserLoginResponseData login(UserLoginRequest userLoginRequest) throws SZJException {
        String code = userLoginRequest.getCode();
        if (code == null || code.isEmpty()) {
            throw new SZJException(SZJErrorCode.ACCOUNT_EMPTY);
        }

        String password = userLoginRequest.getPassword();
        if (password == null || password.isEmpty()) {
            throw new SZJException(SZJErrorCode.PASSWORD_EMPTY);
        }
        password = PasswordKit.encode(password);

        Boolean changeOpenId = userLoginRequest.isChangeOpenid();
        if (changeOpenId == null) {
            throw new SZJException(SZJErrorCode.ARGUMENT_NULL);
        }

        if (!existCode(code)) {
            throw new SZJException(SZJErrorCode.ACCOUNT_NOT_EXIST);
        }

        Szjuserinfo szjuserinfo = szjuserinfoDao.login(code, password);
        if (szjuserinfo == null) {
            throw new SZJException(SZJErrorCode.LOG_IN_FAILURE);
        }

        try {
            UserLoginResponseData userLoginResponseData = new UserLoginResponseData(szjuserinfo);
            if (changeOpenId) {
                Long id = userLoginResponseData.Id;
                String newOpenId = UUIDKit.formUUID();
                szjuserinfoDao.updateOpenid(id, newOpenId);
                szjuserinfoDao.updateModifyInfo(id, TimeKit.getFormalTime(), "login", "login");
                userLoginResponseData.setOpenid(newOpenId);
            }
            return userLoginResponseData;
        } catch (Exception e) {
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
    }


    @Override
    public Szjuserinfo getUserInfo(GetUserInfoRequest request) throws SZJException {
        // 检查登录码是否合法
        String openId = request.getOpenid();
        if (openId == null || openId.isEmpty() || !existOpenId(openId)) {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }

        try {
            Szjuserinfo res = szjuserinfoDao.getByOpenId(openId);
            return res;
        } catch (Exception e) {
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
    }

    // 根据id获取用户信息
    @Override
    public Szjuserinfo getById(Long id) {
        Szjuserinfo szjuserinfo = szjuserinfoDao.getById(id);
        return szjuserinfo;
    }

    // 根据OpenId获取用户信息
    @Override
    public Szjuserinfo getUserInfoByOpenId(String openId) throws SZJException {
        if (existOpenId(openId)) {
            return szjuserinfoDao.getByOpenId(openId);
        } else {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }
    }

    // 检查OpenId是否存在
    @Override
    public boolean existOpenId(String openId) {
        Long openIdNum = szjuserinfoDao.existOpenId(openId);
        if (openIdNum.intValue() == 1) {
            return true;
        } else {
            return false;
        }
    }

    // 根据Code检查数据库中是否存在用户
    @Override
    public boolean existCode(String userName) {
        Long id = szjuserinfoDao.existCode(userName);
        if (id.intValue() == 0) {
            return false;
        } else {
            return true;
        }
    }

    // 暂时没有注销用户的操作
    @Override
    public long delete(UserRegisterRequest userRegisterRequest) {
        return 0;
    }

    // 暂时没有修改用户信息的操作
    @Override
    public long update(UserRegisterRequest userRegisterRequest) {
        return 0;
    }

    @Override
    @Transactional
    public boolean bindingUserWechat(BindingWechatRequest request) {
        String openId = request.getOpenid();
        if (openId == null || openId.isEmpty() || !existOpenId(openId)) {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }
        Szjuserinfo userInfo = szjuserinfoDao.getByOpenId(openId);
        String wechatOpenid = userInfo.getWechatOpenid();
        if (wechatOpenid == null || wechatOpenid.isEmpty()) {
            szjuserinfoDao.updateWechatOpenid(userInfo.getId(), request.getWechatOpenid());
            szjuserinfoDao.updateModifyInfo(userInfo.getId(), TimeKit.getFormalTime(), userInfo.getCode(), userInfo.getName());
            return true;
        } else {
            throw new SZJException(SZJErrorCode.Wechat_Already_Binded);
        }
    }
}
