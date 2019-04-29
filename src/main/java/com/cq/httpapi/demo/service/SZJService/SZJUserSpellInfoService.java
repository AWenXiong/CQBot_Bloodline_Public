package com.cq.httpapi.demo.service.SZJService;

import com.cq.httpapi.demo.dto.SZJ.Request.UserSpellsInfoRequest.DeleteUserSpellRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserSpellsInfoRequest.EditUserSpellsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.UserSpellsInfoRequest.GetUserSpellsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.UserSpellsInfoResponse.GetUserSpellsInfoResponseData;

import java.util.ArrayList;

public interface SZJUserSpellInfoService {
    ArrayList<GetUserSpellsInfoResponseData> getSpellsInfo(GetUserSpellsInfoRequest request);

    boolean updateSpellsInfo(EditUserSpellsInfoRequest request);

    boolean deleteUserSpell(DeleteUserSpellRequest request);
}
