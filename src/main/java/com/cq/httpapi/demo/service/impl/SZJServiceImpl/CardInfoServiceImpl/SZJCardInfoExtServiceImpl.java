package com.cq.httpapi.demo.service.impl.SZJServiceImpl.CardInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjcardinfoextDao;
import com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest.GetAllCardsInfoExtRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse.GetAllCardsInfoExtResponseData;
import com.cq.httpapi.demo.entity.SZJ.Szjcardinfoext;
import com.cq.httpapi.demo.exception.SZJException.AllCardsInfoException.GetAllCardsInfoExtException;
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
            throws GetAllCardsInfoExtException {
        String openId = request.getOpenid();
        if (openId != null && !szjUserInfoService.existOpenId(openId)) {
            throw new GetAllCardsInfoExtException(1, "登录码不存在！");
        }
        ArrayList<Szjcardinfoext> datas = szjcardinfoextDao.getAll();
        ArrayList<GetAllCardsInfoExtResponseData> res = new ArrayList<>();

        return res;
    }

}
