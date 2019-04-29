package com.cq.httpapi.demo.service.SZJService;

import com.cq.httpapi.demo.dto.SZJ.Request.UserCardInfoRequest.DeleteUserCardRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserCardInfoRequest.EditUserCardsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserCardInfoRequest.GetUserCardsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.UserCardInfoResponse.GetUserCardsInfoResponseData;

import java.util.ArrayList;

public interface SZJUserCardInfoService {

    ArrayList<GetUserCardsInfoResponseData> getCardInfo(GetUserCardsInfoRequest request);

    boolean updateCardInfo(EditUserCardsInfoRequest request);

    boolean deleteCardInfo(DeleteUserCardRequest request);
}
