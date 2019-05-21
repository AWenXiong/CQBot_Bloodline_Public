package com.cq.httpapi.demo.service.impl.SZJServiceImpl.CardInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjcardinfoextDao;
import com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest.GetAllCardsInfoExtRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse.GetAllCardsInfoExtResponseData;
import com.cq.httpapi.demo.entity.SZJ.Szjcardinfoext;
import com.cq.httpapi.demo.exception.SZJException.SZJErrorCode;
import com.cq.httpapi.demo.exception.SZJException.SZJException;
import com.cq.httpapi.demo.service.SZJService.SZJCardInfoExtService;
import com.cq.httpapi.demo.service.SZJService.SZJUserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class SZJCardInfoExtServiceImpl implements SZJCardInfoExtService {

    @Resource
    SzjcardinfoextDao szjcardinfoextDao;
    @Resource
    SZJUserInfoService szjUserInfoService;

    @Override
    public ArrayList<GetAllCardsInfoExtResponseData> getAllCardInfoExt(GetAllCardsInfoExtRequest request)
            throws SZJException {
        String openId = request.getOpenid();
        if (openId != null && !openId.isEmpty() && !szjUserInfoService.existOpenId(openId)) {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }
        ArrayList<Szjcardinfoext> datas = szjcardinfoextDao.getAll();
        if (datas == null) {
            throw new SZJException(SZJErrorCode.GET_CARD_EXT_INFO_FAILURE);
        }

        ArrayList<GetAllCardsInfoExtResponseData> res = new ArrayList<>();
        try {
            for (Szjcardinfoext data : datas) {
                res.add(new GetAllCardsInfoExtResponseData(data));
            }
        } catch (Exception e) {
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return res;
    }

}
