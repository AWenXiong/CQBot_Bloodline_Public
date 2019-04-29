package com.cq.httpapi.demo.service.impl.SZJServiceImpl.CardInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjenemyinfoDao;
import com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest.GetEnemyInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse.GetEnemyInfoResponseData;
import com.cq.httpapi.demo.entity.SZJ.Szjenemyinfo;
import com.cq.httpapi.demo.exception.SZJException.AllCardsInfoException.GetEnemyInfoException;
import com.cq.httpapi.demo.service.SZJService.SZJEnemyInfoService;
import com.cq.httpapi.demo.service.SZJService.SZJUserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class SZJEnemyInfoServiceImpl implements SZJEnemyInfoService {

    @Resource
    private SZJUserInfoService szjUserInfoService;
    @Resource
    private SzjenemyinfoDao szjenemyinfoDao;

    @Override
    public ArrayList<GetEnemyInfoResponseData> getEnemyInfo(GetEnemyInfoRequest request)
            throws GetEnemyInfoException {
        String openId = request.getOpenid();
        if (openId != null && !szjUserInfoService.existOpenId(openId)) {
            throw new GetEnemyInfoException(1, "登录码不存在！");
        }
        ArrayList<Szjenemyinfo> datas = szjenemyinfoDao.getAll();
        if (datas == null) {
            throw new GetEnemyInfoException(2, "获取敌阵容信息失败！");
        }
        try {
            ArrayList<GetEnemyInfoResponseData> res = new ArrayList<>();
            for (Szjenemyinfo data : datas) {
                res.add(new GetEnemyInfoResponseData(data));
            }
            return res;
        } catch (Exception e) {
            throw new GetEnemyInfoException(9, e.getMessage());
        }
    }
}
