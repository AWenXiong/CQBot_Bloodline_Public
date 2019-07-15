package com.cq.httpapi.demo.service.SZJService;

import com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest.GetAllCardsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse.GetAllCardsInfoResponseData;
import com.cq.httpapi.demo.entity.SZJ.Szjcardinfo;

import java.util.ArrayList;

public interface SZJCardInfoService {

    void init();

    ArrayList<GetAllCardsInfoResponseData> getAllCards(GetAllCardsInfoRequest request);

    void insertData(Szjcardinfo szjcardinfo);

    boolean deleteCard(long id);
}
