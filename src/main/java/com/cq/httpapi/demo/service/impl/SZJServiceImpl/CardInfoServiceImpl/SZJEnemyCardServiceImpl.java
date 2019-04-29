package com.cq.httpapi.demo.service.impl.SZJServiceImpl.CardInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjenemycardDao;
import com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest.GetEnemyCardRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse.GetEnemyCardResponseData;
import com.cq.httpapi.demo.entity.SZJ.Szjenemycard;
import com.cq.httpapi.demo.exception.SZJException.AllCardsInfoException.GetEnemyCardException;
import com.cq.httpapi.demo.exception.SZJException.AllCardsInfoException.GetEnemyInfoException;
import com.cq.httpapi.demo.service.SZJService.SZJEnemyCardService;
import com.cq.httpapi.demo.service.SZJService.SZJUserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class SZJEnemyCardServiceImpl implements SZJEnemyCardService {

    @Resource
    private SZJUserInfoService szjUserInfoService;
    @Resource
    private SzjenemycardDao szjenemycardDao;

    @Override
    public ArrayList<GetEnemyCardResponseData> getEnemyInfo(GetEnemyCardRequest request)
            throws GetEnemyInfoException {
        String openId = request.getOpenid();
        if (openId != null && !szjUserInfoService.existOpenId(openId)) {
            throw new GetEnemyCardException(1, "登录码不存在！");
        }

        Long id = request.getId();
        if (id == null) {
            throw new GetEnemyCardException(2, "敌阵容主键缺失！");
        }

        Double level = request.getLevel();
        ArrayList<Szjenemycard> datas;
        if (level != null && level.intValue() > 0) {
            datas = szjenemycardDao.getByGroupAndLevel(id, level.longValue());
        } else {
            datas = szjenemycardDao.getByGroup(id);
        }

        if (datas == null) {
            throw new GetEnemyCardException(3, "获取敌阵容卡信息失败！");
        }

        try {
            ArrayList<GetEnemyCardResponseData> res = new ArrayList<>();
            for (Szjenemycard data : datas) {
                res.add(new GetEnemyCardResponseData(data));
            }
            return res;
        } catch (Exception e) {
            throw new GetEnemyCardException(9, e.getMessage());
        }
    }
}
