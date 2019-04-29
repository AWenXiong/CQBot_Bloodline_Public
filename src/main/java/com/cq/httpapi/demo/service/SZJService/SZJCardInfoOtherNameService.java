package com.cq.httpapi.demo.service.SZJService;

import com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest.GetAllCardsOtherNameRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse.GetAllCardsOtherNameResponseData;

import java.util.ArrayList;

public interface SZJCardInfoOtherNameService {

    ArrayList<String> getCardNickname(String nickname);

    ArrayList<GetAllCardsOtherNameResponseData> getAllCardNickname(GetAllCardsOtherNameRequest request);

}
