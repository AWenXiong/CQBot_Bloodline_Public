package com.cq.httpapi.demo.service.impl.SZJServiceImpl.UserSpellInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjuserspellinfoDao;
import com.cq.httpapi.demo.dto.SZJ.Request.UserSpellsInfoRequest.DeleteUserSpellRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserSpellsInfoRequest.EditUserSpellsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserSpellsInfoRequest.EditUserSpellsInfoRequestData;
import com.cq.httpapi.demo.dto.SZJ.Request.UserSpellsInfoRequest.GetUserSpellsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.UserSpellsInfoResponse.GetUserSpellsInfoResponseData;
import com.cq.httpapi.demo.entity.SZJ.Szjuserinfo;
import com.cq.httpapi.demo.entity.SZJ.Szjuserspellinfo;
import com.cq.httpapi.demo.exception.SZJException.UserSpellInfoException.DeleteUserSpellException;
import com.cq.httpapi.demo.exception.SZJException.UserSpellInfoException.EditUserSpellsInfoException;
import com.cq.httpapi.demo.exception.SZJException.UserSpellInfoException.GetUserSpellsInfoException;
import com.cq.httpapi.demo.kit.TimeKit;
import com.cq.httpapi.demo.service.SZJService.SZJUserInfoService;
import com.cq.httpapi.demo.service.SZJService.SZJUserSpellInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class SZJUserSpellInfoServiceImpl implements SZJUserSpellInfoService {

    @Resource
    private SZJUserInfoService szjUserInfoService;
    @Resource
    private SzjuserspellinfoDao szjuserspellinfoDao;

    @Override
    public ArrayList<GetUserSpellsInfoResponseData> getSpellsInfo(GetUserSpellsInfoRequest request)
            throws GetUserSpellsInfoException {
        String openId = request.getOpenid();
        if (openId == null || !szjUserInfoService.existOpenId(openId)) {
            throw new GetUserSpellsInfoException(1, "登录码不存在！");
        }

        Long groupId = request.getId();
        if (groupId == null) {
            throw new GetUserSpellsInfoException(2, "卡组主键缺失！");
        }

        try {
            Szjuserinfo userInfo = szjUserInfoService.getUserInfoByOpenId(openId);
            Long userId = userInfo.getId();
            ArrayList<Szjuserspellinfo> datas = szjuserspellinfoDao.getUserSpellsInfo(userId, groupId);
            ArrayList<GetUserSpellsInfoResponseData> res = new ArrayList<>();
            for (Szjuserspellinfo data : datas) {
                res.add(new GetUserSpellsInfoResponseData(data));
            }
            return res;
        } catch (Exception e) {
            throw new GetUserSpellsInfoException(9, e.getMessage());
        }
    }

    @Override
    public boolean updateSpellsInfo(EditUserSpellsInfoRequest request) throws EditUserSpellsInfoException {
        String openId = request.getOpenid();
        if (openId == null || !szjUserInfoService.existOpenId(openId)) {
            throw new EditUserSpellsInfoException(1, "登录码不存在！");
        }

        Long groupId = request.getId();
        if (groupId == null) {
            throw new EditUserSpellsInfoException(2, "卡组主键缺失！");
        }

        try {
            ArrayList<EditUserSpellsInfoRequestData> datas = request.getUserSpellInfo();
            for (EditUserSpellsInfoRequestData data : datas) {
                // spellInfoId 为 SZJUserSpellInfo 的主键
                Long spellInfoId = data.getSpellId();
                Szjuserinfo userInfo = szjUserInfoService.getUserInfoByOpenId(openId);
                Long userId = userInfo.getId();
                /*TODO 如果spellId为""会怎么样*/
                if (spellInfoId == null || spellInfoId.intValue() == 0) { // 新增法阵信息
                    // spellId 为 SZJSpellInfo 的主键
                    Long spellId = data.getSpellId();
                    Long fightingCapacity = data.getFightingCapacity();
                    if (spellId != null && fightingCapacity != null) {
                        szjuserspellinfoDao.insertSzjuserspellinfo(userId, groupId, spellId, fightingCapacity);
                        // id 为 新插入到 SZJUserSpellInfo 的记录的主键
                        Long id = szjuserspellinfoDao.getLastInsert(groupId);
                        szjuserspellinfoDao.updateCreateInfo(id, TimeKit.getFormalTime(), String.valueOf(userId), String.valueOf(userId));
                        szjuserspellinfoDao.updateDescription(id, "新增法阵信息");
                    } else {
                        throw new EditUserSpellsInfoException(3, "法阵信息缺失！");
                    }
                } else if (spellInfoId > 0) { // 编辑法阵信息
                    Long spellId = data.getSpellId();
                    Long fightingCapacity = data.getFightingCapacity();
                    boolean flag = false;
                    if (spellId != null && spellId.intValue() != 0) {
                        szjuserspellinfoDao.updateSpellId(spellInfoId, spellId);
                        flag = true;
                    }
                    if (fightingCapacity != null && fightingCapacity.intValue() > 0) {
                        szjuserspellinfoDao.updateFightingCapacity(spellInfoId, fightingCapacity);
                        flag = true;
                    }
                    if (flag) {
                        szjuserspellinfoDao.updateModifyInfo(spellInfoId, TimeKit.getFormalTime(), String.valueOf(userId), String.valueOf(userId));
                        szjuserspellinfoDao.updateDescription(spellInfoId, "编辑法阵信息");
                    }
                }
            }
            return true;
        } catch (Exception e) {
            throw new EditUserSpellsInfoException(9, e.getMessage());
        }
    }

    @Override
    public boolean deleteUserSpell(DeleteUserSpellRequest request) throws DeleteUserSpellException {
        String openId = request.getOpenid();
        if (openId == null || !szjUserInfoService.existOpenId(openId)) {
            throw new DeleteUserSpellException(1, "登录码不存在！");
        }

        Long spellId = request.getId();
        if (spellId == null) {
            throw new DeleteUserSpellException(2, "法阵主键缺失！");
        }

        try {
            Szjuserinfo userInfo = szjUserInfoService.getUserInfoByOpenId(openId);
            Long userId = userInfo.getId();
            szjuserspellinfoDao.deleteUserSpell(spellId);
            szjuserspellinfoDao.updateModifyInfo(spellId, TimeKit.getFormalTime(), String.valueOf(userId), String.valueOf(userId));
            szjuserspellinfoDao.updateDescription(spellId, "删除法阵信息");
            return true;
        } catch (Exception e) {
            throw new DeleteUserSpellException(9, e.getMessage());
        }
    }
}
