package com.cq.httpapi.demo.service.impl.SZJServiceImpl.CardInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjenemylevelDao;
import com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest.GetEnemyLevelRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse.GetEnemyLevelResponseData;
import com.cq.httpapi.demo.entity.SZJ.Szjenemylevel;
import com.cq.httpapi.demo.exception.SZJException.AllCardsInfoException.GetEnemyLevelException;
import com.cq.httpapi.demo.service.SZJService.SZJEnemyLevelService;
import com.cq.httpapi.demo.service.SZJService.SZJUserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class SZJEnemyLevelServiceImpl implements SZJEnemyLevelService {

    @Resource
    private SzjenemylevelDao szjenemylevelDao;
    @Resource
    private SZJUserInfoService szjUserInfoService;

    @Override
    public ArrayList<GetEnemyLevelResponseData> getEnemyInfo(GetEnemyLevelRequest request) {
        String openId = request.getOpenid();
        if (openId != null && !szjUserInfoService.existOpenId(openId)) {
            throw new GetEnemyLevelException(1, "登录码不存在！");
        }
        Long id = request.getId();
        if (id == null) {
            throw new GetEnemyLevelException(2, "敌阵容信息主键缺失！");
        }
        try {
            ArrayList<Szjenemylevel> datas = szjenemylevelDao.getAll();
            ArrayList<GetEnemyLevelResponseData> res = new ArrayList<>();
            for (Szjenemylevel data : datas) {
                res.add(new GetEnemyLevelResponseData(data));
            }
            return res;
        } catch (Exception e) {
            throw new GetEnemyLevelException(9, e.getMessage());
        }
    }
}
