package com.cq.httpapi.demo.service.impl.SZJServiceImpl.UserSpellInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjuserspellinfoDao;
import com.cq.httpapi.demo.dto.SZJ.Request.UserSpellsInfoRequest.DeleteUserSpellRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserSpellsInfoRequest.EditUserSpellsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserSpellsInfoRequest.EditUserSpellsInfoRequestData;
import com.cq.httpapi.demo.dto.SZJ.Request.UserSpellsInfoRequest.GetUserSpellsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.UserSpellsInfoResponse.GetUserSpellsInfoResponseData;
import com.cq.httpapi.demo.entity.SZJ.Szjuserinfo;
import com.cq.httpapi.demo.entity.SZJ.Szjuserspellinfo;
import com.cq.httpapi.demo.exception.SZJException.SZJErrorCode;
import com.cq.httpapi.demo.exception.SZJException.SZJException;
import com.cq.httpapi.demo.kit.TimeKit;
import com.cq.httpapi.demo.service.SZJService.SZJUserInfoService;
import com.cq.httpapi.demo.service.SZJService.SZJUserSpellInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            throws SZJException {
        String openId = request.getOpenid();
        if (openId == null || !szjUserInfoService.existOpenId(openId)) {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }

        Long groupId = request.getId();
        if (groupId == null) {
            throw new SZJException(SZJErrorCode.USER_CARD_GROUP_ID_LOST);
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
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
    }

    @Override
    @Transactional
    public boolean updateSpellsInfo(EditUserSpellsInfoRequest request) throws SZJException {
        String openId = request.getOpenid();
        if (openId == null || !szjUserInfoService.existOpenId(openId)) {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }

        Long groupId = request.getId();
        if (groupId == null) {
            throw new SZJException(SZJErrorCode.USER_CARD_GROUP_ID_LOST);
        }

        ArrayList<EditUserSpellsInfoRequestData> datas = request.getUserSpellInfo();
        if (datas == null) {
            throw new SZJException(SZJErrorCode.USER_SPELL_INFO_LOST);
        }

        try {
            for (EditUserSpellsInfoRequestData data : datas) {
                Long spellInfoId = data.getId();
                Szjuserinfo userInfo = szjUserInfoService.getUserInfoByOpenId(openId);
                Long userId = userInfo.getId();
                if (spellInfoId == null || spellInfoId.intValue() == 0) { // 新增法阵信息
                    Long spellId = data.getSpellId();
                    Long fightingCapacity = data.getFightingCapacity();
                    if (spellId != null && fightingCapacity != null) {
                        szjuserspellinfoDao.insertSzjuserspellinfo(userId, groupId, spellId, fightingCapacity);
                        long id = szjuserspellinfoDao.getLastInsert(groupId);
                        szjuserspellinfoDao.updateCreateInfo(id, TimeKit.getFormalTime(), String.valueOf(userId), String.valueOf(userId));
                        szjuserspellinfoDao.updateDescription(id, "新增法阵信息");
                    } else {
                        throw new SZJException(SZJErrorCode.USER_SPELL_INFO_LOST);
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
        } catch (SZJException e) {
            throw e;
        } catch (Exception e) {
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
    }

    @Override
    @Transactional
    public boolean deleteUserSpell(DeleteUserSpellRequest request) throws SZJException {
        String openId = request.getOpenid();
        if (openId == null || !szjUserInfoService.existOpenId(openId)) {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }

        Long spellId = request.getId();
        if (spellId == null) {
            throw new SZJException(SZJErrorCode.USER_SPELL_INFO_LOST);
        }

        try {
            Szjuserinfo userInfo = szjUserInfoService.getUserInfoByOpenId(openId);
            Long userId = userInfo.getId();
            szjuserspellinfoDao.deleteUserSpell(spellId);
            szjuserspellinfoDao.updateModifyInfo(spellId, TimeKit.getFormalTime(), String.valueOf(userId), String.valueOf(userId));
            szjuserspellinfoDao.updateDescription(spellId, "删除法阵信息");
            return true;
        } catch (Exception e) {
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
    }
}
