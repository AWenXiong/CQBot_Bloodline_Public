package com.cq.httpapi.demo.service.impl.SZJServiceImpl.CardInfoServiceImpl;

import com.cq.httpapi.demo.dao.SZJdao.SzjcardinfoDao;
import com.cq.httpapi.demo.dto.SZJ.Request.AllCardInfoRequest.GetAllCardsInfoRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.AllCardInfoResponse.GetAllCardsInfoResponseData;
import com.cq.httpapi.demo.entity.CQ.Card;
import com.cq.httpapi.demo.entity.SZJ.Szjcardinfo;
import com.cq.httpapi.demo.exception.SZJException.SZJErrorCode;
import com.cq.httpapi.demo.exception.SZJException.SZJException;
import com.cq.httpapi.demo.kit.ObjectKit;
import com.cq.httpapi.demo.kit.TranslateKit;
import com.cq.httpapi.demo.service.CQService.CardService;
import com.cq.httpapi.demo.service.SZJService.SZJCardInfoService;
import com.cq.httpapi.demo.service.SZJService.SZJUserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class SZJCardInfoServiceImpl implements SZJCardInfoService {

    @Resource
    private CardService cardService;
    @Resource
    private SzjcardinfoDao szjcardinfoDao;
    @Resource
    private SZJUserInfoService szjUserInfoService;

    //获取全部卡牌信息
    @Override
    public ArrayList<GetAllCardsInfoResponseData> getAllCards(GetAllCardsInfoRequest request) throws SZJException {

        String openId = request.getOpenid();
        if (openId != null && !openId.isEmpty() && !szjUserInfoService.existOpenId(openId)) {
            throw new SZJException(SZJErrorCode.OPENID_ERROR);
        }

        ArrayList<Szjcardinfo> datas = szjcardinfoDao.getAllCards();
        if (datas == null) {
            throw new SZJException(SZJErrorCode.GET_ALL_CARD_INFO_FAILURE);
        }

        // 处理双属性的情况
        try {
            ArrayList<GetAllCardsInfoResponseData> res = new ArrayList<>();
            for (Szjcardinfo data : datas) {
                String color = data.getColor();
                if (color != null && color.length() > 1) {
                    Szjcardinfo newData = (Szjcardinfo) ObjectKit.deepClone(data);
                    String color1 = color.substring(0, 1);
                    String color2 = color.substring(1);
                    newData.setColor(color1);
                    newData.setColor2(color2);
                    res.add(new GetAllCardsInfoResponseData(newData));
                } else {
                    res.add(new GetAllCardsInfoResponseData(data));
                }
            }
            return res;
        } catch (Exception e) {
            throw new SZJException(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
    }

    @Override
    public void insertData(Szjcardinfo szjcardinfo) {
        try {
            szjcardinfoDao.insertCardInfo(szjcardinfo);
        } catch (Exception e) {
            throw new SZJException(SZJErrorCode.INSERT_CARD_INFO_ERROR);
        }
    }

    @Override
    public void init() {
        ArrayList<Card> cards = cardService.getAllCard();
        for (Card card : cards) {
            Szjcardinfo c = new Szjcardinfo();
            c.setId(card.getId());
            c.setCode(String.valueOf(card.getId()));

            String fullName = TranslateKit.formatPinYin(card.getFullname());

            c.setName(fullName);
            c.setNickName(card.getNickname());

            String color = card.getColor();
            if (color != null && color.length() > 1) {
                String color1 = color.substring(0, 1);
                String color2 = color.substring(1);
                c.setColor(color1);
                c.setColor2(color2);
            } else {
                c.setColor(color);
            }

            c.setSex(card.getSex());
            c.setOccupation(card.getCareer());
            c.setCamp(card.getFaction());
            c.setDescription(card.getDescription());
            c.setCreateOn(card.getCreateTime());
            c.setCreateUserId(card.getCreateUserId());
            c.setCreateBy(card.getCreateUserId());
            c.setModifiedOn(card.getModifiedTime());
            c.setModifiedUserId(card.getModifiedUserId());
            c.setModifiedBy(card.getModifiedUserId());
            try {
                szjcardinfoDao.insertCardInfo(c);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
