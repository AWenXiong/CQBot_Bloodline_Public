package com.cq.httpapi.demo.service.impl.SZJServiceImpl.CardInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjspellinfoDao;
import com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest.GetAllSpellsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse.GetAllSpellsInfoResponseData;
import com.cq.httpapi.demo.entity.SZJ.Szjspellinfo;
import com.cq.httpapi.demo.exception.SZJException.SZJErrorCode;
import com.cq.httpapi.demo.exception.SZJException.SZJException;
import com.cq.httpapi.demo.service.SZJService.SZJSpellInfoService;
import com.cq.httpapi.demo.service.SZJService.SZJUserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class SZJSpellInfoServiceImpl implements SZJSpellInfoService {

    @Resource
    private SZJUserInfoService szjUserInfoService;
    @Resource
    private SzjspellinfoDao szjspellinfoDao;

    @Override
    public ArrayList<GetAllSpellsInfoResponseData> getAllSpells(GetAllSpellsInfoRequest request)
            throws SZJException {

        String openId = request.getOpenid();
        if (openId != null && !openId.isEmpty() && !szjUserInfoService.existOpenId(openId)) {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }

        ArrayList<Szjspellinfo> datas = szjspellinfoDao.getAll();
        if (datas == null) {
            throw new SZJException(SZJErrorCode.GET_ALL_SPELL_INFO_FAILURE);
        }

        ArrayList<GetAllSpellsInfoResponseData> res = new ArrayList<>();
        try {
            for (Szjspellinfo data : datas) {
                res.add(new GetAllSpellsInfoResponseData(data));
            }
        } catch (Exception e) {
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return res;
    }
}
