package com.cq.httpapi.demo.controller.SZJController;

import com.cq.httpapi.demo.dto.SZJ.Request.BackEndDataRequest.*;
import com.cq.httpapi.demo.dto.SZJ.Response.BackEndDataResponse.*;
import com.cq.httpapi.demo.exception.SZJException.SZJErrorCode;
import com.cq.httpapi.demo.exception.SZJException.SZJException;
import com.cq.httpapi.demo.service.SZJService.SZJEnemyInfoService;
import com.cq.httpapi.demo.service.SZJService.SZJUserCardInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/szj")
public class BackEndDataController {

    @Autowired
    private SZJEnemyInfoService szjEnemyInfoService;
    @Autowired
    private SZJUserCardInfoService szjUserCardInfoService;


    @RequestMapping(value = "/CreateInvitationCode", method = RequestMethod.POST)
    public CreateInvitationCodeResponse createInvitationCode(@RequestBody CreateInvitationCodeRequest request) {
        CreateInvitationCodeResponse response = new CreateInvitationCodeResponse();

        return response;
    }

    @RequestMapping(value = "/CreateEnemyInfo", method = RequestMethod.POST)
    public CreateEnemyInfoResponse CreateEnemyInfo(@RequestBody CreateEnemyInfoRequest request) {
        CreateEnemyInfoResponse response = new CreateEnemyInfoResponse();
        try {
            response = szjEnemyInfoService.createEnemyInfo(request);
            response.setSuccess(true);
        } catch (SZJException e) {
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return response;
    }

    @RequestMapping(value = "/EnableEnemyInfo", method = RequestMethod.POST)
    public EnableEnemyInfoResponse EnableEnemyInfo(@RequestBody EnableEnemyInfoRequest request) {
        EnableEnemyInfoResponse response = new EnableEnemyInfoResponse();
        try {
            Long id = request.getEnemyInfoId();
            szjEnemyInfoService.EnableEnemyInfo(id);
            response.setSuccess(true);
        } catch (SZJException e) {
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return response;
    }

    @RequestMapping(value = "/CopyEnemyInfo", method = RequestMethod.POST)
    public CopyEnemyInfoResponse CopyEnemyInfo(@RequestBody CopyEnemyInfoRequest request) {
        CopyEnemyInfoResponse response = new CopyEnemyInfoResponse();
        try {

        } catch (SZJException e) {
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return response;
    }

    @RequestMapping(value = "/EditEnemyInfo", method = RequestMethod.POST)
    public EditEnemyInfoResponse EditEnemyInfo(@RequestBody EditEnemyInfoRequest request) {
        EditEnemyInfoResponse response = new EditEnemyInfoResponse();
        try {

        } catch (SZJException e) {
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return response;
    }

    @RequestMapping(value = "/GetAllEnemyInfo")
    public GetAllEnemyInfoResponse GetAllEnemyInfo() {
        GetAllEnemyInfoResponse response = new GetAllEnemyInfoResponse();
        try {
            response = szjEnemyInfoService.getAllEnemyInfo();
            response.setSuccess(true);
        } catch (SZJException e) {
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return response;
    }

    @RequestMapping(value = "/BatchAddUserCards", method = RequestMethod.POST)
    public BatchAddUserCardsResponse BatchAddUserCards(@RequestBody BatchAddUserCardsRequest request) {
        BatchAddUserCardsResponse response = new BatchAddUserCardsResponse();
        try {
            response = szjUserCardInfoService.batchAddUserCards(request);
            response.setSuccess(true);
        } catch (SZJException e) {
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return response;
    }
}
