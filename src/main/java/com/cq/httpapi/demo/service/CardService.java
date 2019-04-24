package com.cq.httpapi.demo.service;

import com.cq.httpapi.demo.entity.Card;

import java.util.ArrayList;

public interface CardService {

    boolean existCard(String nickname);

    Long getId(String nickname);

    Card getAllInfo(String nickname);

    Card getHobby(String nickname);

    Card getSkill(String nickname);

    Card getPartner(String nickname);

    Card getFaction(String nickname);

    boolean createNewCard(String fullName, String createUserId);

    boolean deleteCard(String nickname, String modifiedUserId);

    boolean appendFavourite(String nickname, String append, String modifiedUserId);

    boolean appendDislike(String nickname, String append, String modifiedUserId);

    boolean updateFullName(String nickname, String fullName, String modifiedUserId);

    boolean updateNickName(String nickname, String newNickname, String modifiedUserId);

    boolean appendNickName(String nickname, String append, String modifiedUserId);

    boolean updateCardSkill(String nickname, String skill1, String skill2, String skill3, String skill4);

    boolean updateCardSkill1(String nickname, String skill1);

    boolean updateCardSkill2(String nickname, String skill2);

    boolean updateCardSkill3(String nickname, String skill3);

    boolean updateCardSkill4(String nickname, String skill4);

    boolean updateCardFaction(String nickname, String faction);

    boolean updateCardColor(String nickname, String color);

    boolean updateCardSex(String nickname, String sex);

    boolean updateCardSpecies(String nickname, String species);

    boolean updateCardFavourate(String nickname, String favourate);

    boolean updateCardDislike(String nickname, String dislike);

    boolean updateCardCareer(String nickname, String career);

    boolean updateCardPartner(String nickname, String partner);

    boolean updateCardNickname(String nickname, String newNickname);

    ArrayList<Card> getCardByFavouriteItem(String item);

    ArrayList<Card> getCardByDislikeItem(String item);

    ArrayList<Card> getCardByFaction(String faction);
}
