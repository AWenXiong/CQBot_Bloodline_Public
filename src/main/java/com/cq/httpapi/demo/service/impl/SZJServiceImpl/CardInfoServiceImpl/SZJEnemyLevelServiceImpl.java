package com.cq.httpapi.demo.service.impl.SZJServiceImpl.CardInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjenemylevelDao;
import com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest.GetEnemyLevelRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse.GetEnemyLevelResponseData;
import com.cq.httpapi.demo.entity.SZJ.Szjenemylevel;
import com.cq.httpapi.demo.exception.SZJException.SZJErrorCode;
import com.cq.httpapi.demo.exception.SZJException.SZJException;
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
    public ArrayList<GetEnemyLevelResponseData> getEnemyInfo(GetEnemyLevelRequest request) throws SZJException {

        String openId = request.getOpenid();
        if (openId != null && !openId.isEmpty() && !szjUserInfoService.existOpenId(openId)) {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }

        Long id = request.getId();
        if (id == null) {
            throw new SZJException(SZJErrorCode.ENEMY_INFO_ID_LOST);
        }

        ArrayList<Szjenemylevel> datas = szjenemylevelDao.getAll();
        if (datas == null) {
            throw new SZJException(SZJErrorCode.GET_ENEMY_LEVEL_FAILURE);
        }

        ArrayList<GetEnemyLevelResponseData> res = new ArrayList<>();
        try {
            for (Szjenemylevel data : datas) {
                res.add(new GetEnemyLevelResponseData(data));
            }
        } catch (Exception e) {
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return res;
    }
}
