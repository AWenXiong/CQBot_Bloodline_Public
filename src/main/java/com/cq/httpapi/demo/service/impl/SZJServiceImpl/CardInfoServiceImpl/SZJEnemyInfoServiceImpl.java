package com.cq.httpapi.demo.service.impl.SZJServiceImpl.CardInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjenemyinfoDao;
import com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest.GetEnemyInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse.GetEnemyInfoResponseData;
import com.cq.httpapi.demo.entity.SZJ.Szjenemyinfo;
import com.cq.httpapi.demo.exception.SZJException.SZJErrorCode;
import com.cq.httpapi.demo.exception.SZJException.SZJException;
import com.cq.httpapi.demo.service.SZJService.SZJEnemyInfoService;
import com.cq.httpapi.demo.service.SZJService.SZJUserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SZJEnemyInfoServiceImpl implements SZJEnemyInfoService {

    @Resource
    private SZJUserInfoService szjUserInfoService;
    @Resource
    private SzjenemyinfoDao szjenemyinfoDao;

    @Override
    public GetEnemyInfoResponseData getEnemyInfo(GetEnemyInfoRequest request)
            throws SZJException {
        String openId = request.getOpenid();
        if (openId != null && !openId.isEmpty() && !szjUserInfoService.existOpenId(openId)) {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }

        Szjenemyinfo data = szjenemyinfoDao.getUsable();
        if (data == null) {
            throw new SZJException(SZJErrorCode.GET_ENEMY_INFO_FAILURE);
        }

        try {
            GetEnemyInfoResponseData res = new GetEnemyInfoResponseData(data);
            return res;
        } catch (Exception e) {
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
    }
}
