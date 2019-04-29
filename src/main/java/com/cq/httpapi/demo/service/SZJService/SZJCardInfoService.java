package com.cq.httpapi.demo.service.SZJService;

import com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest.GetAllCardsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse.GetAllCardsInfoResponseData;

import java.util.ArrayList;

public interface SZJCardInfoService {

    ArrayList<GetAllCardsInfoResponseData> getAllCards(GetAllCardsInfoRequest request);

}
