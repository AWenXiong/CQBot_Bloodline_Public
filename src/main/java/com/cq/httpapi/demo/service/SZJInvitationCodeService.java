package com.cq.httpapi.demo.service;

import com.cq.httpapi.demo.dto.SZJ.Request.BackEndDataRequest.CreateInvitationCodeRequest;
import com.cq.httpapi.demo.entity.SZJ.Szjinvitationcode;

public interface SZJInvitationCodeService {

    Long createInvitationCode(CreateInvitationCodeRequest request);

    Long deleteInvitationCode();

    boolean existInvitationCode(String invitationCode);

    Szjinvitationcode getById(long id);

    Szjinvitationcode getByCode(String invitationCode);
}
