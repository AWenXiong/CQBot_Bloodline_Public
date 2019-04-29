package com.cq.httpapi.demo.service.impl.SZJServiceImpl.CardInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjspellinfoDao;
import com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest.GetAllSpellsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse.GetAllSpellsInfoResponseData;
import com.cq.httpapi.demo.entity.SZJ.Szjspellinfo;
import com.cq.httpapi.demo.exception.SZJException.AllCardsInfoException.GetAllSpellsInfoException;
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
            throws GetAllSpellsInfoException {
        String openId = request.getOpenid();
        if (openId != null && !openId.isEmpty()) {
            if (!szjUserInfoService.existOpenId(openId)) {
                throw new GetAllSpellsInfoException(1, "登录码不存在！");
            }
        }
        ArrayList<Szjspellinfo> datas = szjspellinfoDao.getAll();
        if (datas == null) {
            throw new GetAllSpellsInfoException(2, "获取全部法阵信息失败！");
        }
        ArrayList<GetAllSpellsInfoResponseData> res = new ArrayList<>();
        try {
            for (Szjspellinfo data : datas) {
                GetAllSpellsInfoResponseData newData = new GetAllSpellsInfoResponseData(data);
                res.add(newData);
            }
            return res;
        } catch (Exception e) {
            throw new GetAllSpellsInfoException(9, e.getMessage());
        }
    }
}
