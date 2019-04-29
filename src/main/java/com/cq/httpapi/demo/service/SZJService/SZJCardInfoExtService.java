package com.cq.httpapi.demo.service.SZJService;

import com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest.GetAllCardsInfoExtRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse.GetAllCardsInfoExtResponseData;

import java.util.ArrayList;

public interface SZJCardInfoExtService {

    ArrayList<GetAllCardsInfoExtResponseData> getAllCardInfoExt(GetAllCardsInfoExtRequest request);

}
