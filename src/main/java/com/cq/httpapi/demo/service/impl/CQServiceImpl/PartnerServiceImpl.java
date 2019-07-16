package com.cq.httpapi.demo.service.impl.CQServiceImpl;

import com.cq.httpapi.demo.dao.CQdao.CardDao;
import com.cq.httpapi.demo.dao.CQdao.PartnerDao;
import com.cq.httpapi.demo.entity.CQ.Card;
import com.cq.httpapi.demo.entity.CQ.Partner;
import com.cq.httpapi.demo.kit.TimeKit;
import com.cq.httpapi.demo.service.CQService.PartnerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class PartnerServiceImpl implements PartnerService {

    @Resource
    private CardDao cardDao;
    @Resource
    private PartnerDao partnerDao;

    @Override
    public ArrayList<Card> getMasters(String nickname) {
        ArrayList<Card> res = new ArrayList<>();
        Card card = cardDao.getCardIdByNickname(nickname);
        ArrayList<Partner> partners = partnerDao.getMasters(card.getId());
        for (Partner partner : partners) {
            Card tmp = cardDao.getCardInfoById(partner.getCardId());
            if (tmp != null) {
                res.add(tmp);
            }
        }
        return res;
    }

    @Override
    public boolean sync(String userId) {
        try {
            ArrayList<Card> cards = cardDao.getAllCardInfo();
            for (Card card : cards) {
                String partners = card.getPartner();
                if (partners != null && !partners.isEmpty() && partnerDao.countRecord(card.getId()) == 0) {
                    String[] p = partners.split("\\|");
                    if (p.length == 3) {
                        Partner partner = new Partner();
                        partner.setCardId(card.getId());
                        Long id1 = cardDao.getCardIdByNickname("/" + p[0].trim() + "/").getId();
                        Long id2 = cardDao.getCardIdByNickname("/" + p[1].trim() + "/").getId();
                        Long id3 = cardDao.getCardIdByNickname("/" + p[2].trim() + "/").getId();
                        if (id1 == null || id2 == null || id3 == null) {
                            continue;
                        }
                        partner.setPartner1(id1);
                        partner.setPartner2(id2);
                        partner.setPartner3(id3);
                        partnerDao.insert(partner);
                        int id = partnerDao.getLastInsert();
                        partnerDao.updateCreateTime(id, TimeKit.getFormalTime());
                        partnerDao.updateCreateUserId(id, userId);
                        partnerDao.updateDescription(id, "同步数据");
                    }
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
