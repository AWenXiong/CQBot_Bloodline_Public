package com.cq.httpapi.demo.controller.SZJController;

import com.cq.httpapi.demo.dto.SZJ.Request.BackEndDataRequest.CreateInvitationCodeRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.BackEndDataResponse.CreateInvitationCodeResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/szj")
public class BackEndDataController {

    @RequestMapping(value = "/CreateInvitationCode", method = RequestMethod.POST)
    public CreateInvitationCodeResponse createInvitationCode(@RequestBody CreateInvitationCodeRequest request) {
        CreateInvitationCodeResponse response = new CreateInvitationCodeResponse();

        return response;
    }
}
