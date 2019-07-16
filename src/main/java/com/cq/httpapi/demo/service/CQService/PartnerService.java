package com.cq.httpapi.demo.service.CQService;

import com.cq.httpapi.demo.entity.CQ.Card;

import java.util.ArrayList;

public interface PartnerService {

    ArrayList<Card> getMasters(String nickname);

    boolean sync(String userId);
}
