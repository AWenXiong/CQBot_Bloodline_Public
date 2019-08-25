package com.cq.httpapi.demo.service.impl.SZJServiceImpl.CardInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjenemycardDao;
import com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest.GetEnemyCardRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse.GetEnemyCardResponseData;
import com.cq.httpapi.demo.entity.SZJ.Szjenemycard;
import com.cq.httpapi.demo.exception.SZJException.SZJErrorCode;
import com.cq.httpapi.demo.exception.SZJException.SZJException;
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
            throws SZJException {
        String openId = request.getOpenid();
        if (openId != null && !openId.isEmpty() && !szjUserInfoService.existOpenId(openId)) {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }

        Long id = request.getId();
        if (id == null) {
            throw new SZJException(SZJErrorCode.ENEMY_INFO_ID_LOST);
        }

        Double level = request.getLevel();
        ArrayList<Szjenemycard> datas;
        if (level != null && level.intValue() > 0) {
            datas = szjenemycardDao.getByGroupAndLevel(id, level.longValue());
        } else {
            datas = szjenemycardDao.getByGroup(id);
        }

        if (datas == null) {
            throw new SZJException(SZJErrorCode.GET_ENEMY_CARD_FAILURE);
        }

        ArrayList<GetEnemyCardResponseData> res = new ArrayList<>();
        try {
            for (Szjenemycard data : datas) {
                res.add(new GetEnemyCardResponseData(data));
            }
        } catch (Exception e) {
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return res;
    }

    @Override
    public ArrayList<Szjenemycard> getEnemyCardByLevelId(Long levelId) {
        return szjenemycardDao.getByLevelId(levelId);
    }
}
