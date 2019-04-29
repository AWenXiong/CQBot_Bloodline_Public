package com.cq.httpapi.demo.service.SZJService;

import com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest.GetAllSpellsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse.GetAllSpellsInfoResponseData;

import java.util.ArrayList;

public interface SZJSpellInfoService {

    ArrayList<GetAllSpellsInfoResponseData> getAllSpells(GetAllSpellsInfoRequest request);

}
