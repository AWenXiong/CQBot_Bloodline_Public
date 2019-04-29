package com.cq.httpapi.demo.service.impl.SZJServiceImpl.UserDataServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjuserinfoDao;
import com.cq.httpapi.demo.dto.SZJ.Request.UserLoginRequest.GetUserInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserLoginRequest.UserLoginRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserLoginRequest.UserRegisterRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.UserLoginResponse.UserLoginResponseData;
import com.cq.httpapi.demo.entity.SZJ.Szjuserinfo;
import com.cq.httpapi.demo.exception.SZJException.UserLoginException.*;
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

    /**
     * @param userRegisterRequest 注册请求
     * @return 新增的用户记录主键
     * @throws UserRegisterException errorCode    message
     *                               1            1/2/3/4/5/6/7
     *                               2            用户名已存在！
     *                               9            未知错误！
     */
    @Override
    @Transactional
    public long regist(UserRegisterRequest userRegisterRequest) throws UserRegisterException {
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
            throw new UserRegisterException(1, String.valueOf(errorMessage));
        } else if (existCode(code)) {
            throw new UserRegisterException(2, "用户名已存在！");
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
                throw new UserRegisterException(9, e.getMessage());
            }
        }
    }

    /**
     * 登录
     *
     * @param userLoginRequest 登录请求
     * @return 封装好的用户数据
     * @throws UserNotExistException errorCode    message
     *                               1            用户名为空！
     *                               2            密码为空！
     *                               3            ChangeOpenid为空！
     *                               4            用户不存在！
     *                               5            用户名或密码错误！
     *                               9            未知错误！
     */
    @Override
    @Transactional
    public UserLoginResponseData login(UserLoginRequest userLoginRequest) throws UserNotExistException {
        String code = userLoginRequest.getCode();
        if (code == null || code.isEmpty()) {
            throw new UserLoginException(1, "用户名为空！");
        }

        String password = userLoginRequest.getPassword();
        if (password == null || password.isEmpty()) {
            throw new UserLoginException(2, "密码为空！");
        }
        password = PasswordKit.encode(password);

        Boolean changeOpenId = userLoginRequest.isChangeOpenid();
        if (changeOpenId == null) {
            throw new UserLoginException(3, "ChangeOpenid为空！");
        }

        if (!existCode(code)) {
            throw new UserLoginException(4, "用户不存在！");
        }

        Szjuserinfo szjuserinfo = szjuserinfoDao.login(code, password);
        if (szjuserinfo == null) {
            throw new UserLoginException(5, "用户名或密码错误！");
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
            throw new UserLoginException(9, e.getMessage());
        }
    }

    /**
     * 获取用户信息
     *
     * @param request 获取用户信息请求
     * @return 封装好的用户信息
     * @throws GetUserInfoException errorCode    message
     *                              1            登录码不存在！
     *                              9            未知错误！
     */
    @Override
    public Szjuserinfo getUserInfo(GetUserInfoRequest request) throws GetUserInfoException {
        // 检查登录码是否合法
        String openId = request.getOpenid();
        if (openId == null || openId.isEmpty() || !existOpenId(openId)) {
            throw new GetUserInfoException(1, "登录码不存在！");
        }

        try {
            Szjuserinfo res = szjuserinfoDao.getByOpenId(openId);
            return res;
        } catch (Exception e) {
            throw new GetUserInfoException(9, e.getMessage());
        }
    }

    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    @Override
    public Szjuserinfo getById(Long id) {
        Szjuserinfo szjuserinfo = szjuserinfoDao.getById(id);
        return szjuserinfo;
    }

    /**
     * 根据OpenId获取用户信息
     *
     * @param openId 登录码
     * @return 用户信息
     * @throws UserNotExistException 用户不存在
     */
    @Override
    public Szjuserinfo getUserInfoByOpenId(String openId) throws UserNotExistException {
        if (existOpenId(openId)) {
            return szjuserinfoDao.getByOpenId(openId);
        } else {
            throw new UserOpenIdNotExistException(1, "登录码错误！");
        }
    }

    /**
     * 检查OpenId是否存在
     *
     * @param openId 登录码
     * @return
     */
    @Override
    public boolean existOpenId(String openId) {
        Long openIdNum = szjuserinfoDao.existOpenId(openId);
        if (openIdNum.intValue() == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据Code检查数据库中是否存在用户
     *
     * @param userName
     * @return
     */
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

}
