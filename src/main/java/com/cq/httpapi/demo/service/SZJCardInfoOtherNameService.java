package com.cq.httpapi.demo.service;

import com.cq.httpapi.demo.entity.SZJ.Szjcardinfoothername;

import java.util.ArrayList;

public interface SZJCardInfoOtherNameService {

    ArrayList<String> getCardNickname(String nickname);

    ArrayList<Szjcardinfoothername> getAllCardNickname();
}
