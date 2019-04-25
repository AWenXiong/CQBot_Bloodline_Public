package com.cq.httpapi.demo.service.impl.SZJServiceImpl.BackEndDataServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjinvitationcodeDao;
import com.cq.httpapi.demo.dto.SZJ.Request.BackEndDataRequest.CreateInvitationCodeRequest;
import com.cq.httpapi.demo.entity.SZJ.Szjinvitationcode;
import com.cq.httpapi.demo.service.SZJInvitationCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SZJInvitationCodeServiceImpl implements SZJInvitationCodeService {

    @Resource
    private SzjinvitationcodeDao invitationCodeDao;

    @Override
    public Long createInvitationCode(CreateInvitationCodeRequest request) {
        return null;
    }

    @Override
    public Long deleteInvitationCode() {
        return null;
    }

    @Override
    public boolean existInvitationCode(String invitationCode) {
        long isExist = invitationCodeDao.existInvitationCode(invitationCode);
        if (isExist > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Szjinvitationcode getById(long id) {
        return invitationCodeDao.getById(id);
    }

    @Override
    public Szjinvitationcode getByCode(String invitationCode) {
        if (invitationCode == null || invitationCode.isEmpty()) {
            return null;
        }
        return invitationCodeDao.getByCode(invitationCode);
    }
}
