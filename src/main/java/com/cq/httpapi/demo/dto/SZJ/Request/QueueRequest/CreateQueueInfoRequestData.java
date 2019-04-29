package com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest;

import java.util.ArrayList;

public class CreateQueueInfoRequestData {

    public Double Level;
    public Long UserSpellInfo;
    public ArrayList<CreateQueueInfoRequestDataCard> Cards;

    public Double getLevel() {
        return Level;
    }

    public void setLevel(Double level) {
        Level = level;
    }

    public Long getUserSpellInfo() {
        return UserSpellInfo;
    }

    public void setUserSpellInfo(Long userSpellInfo) {
        UserSpellInfo = userSpellInfo;
    }

    public ArrayList<CreateQueueInfoRequestDataCard> getCards() {
        return Cards;
    }

    public void setCards(ArrayList<CreateQueueInfoRequestDataCard> cards) {
        Cards = cards;
    }
}
