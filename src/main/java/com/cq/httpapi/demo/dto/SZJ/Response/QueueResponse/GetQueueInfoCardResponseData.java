package com.cq.httpapi.demo.dto.SZJ.Response.QueueResponse;

import java.util.ArrayList;

public class GetQueueInfoCardResponseData {

    public Double Level;
    public Long UserSpellInfo;
    public ArrayList<GetQueueInfoCardResponseDataCard> Cards;

    public void setLevel(Double level) {
        Level = level;
    }

    public void setUserSpellInfo(Long userSpellInfo) {
        UserSpellInfo = userSpellInfo;
    }

    public void setCards(ArrayList<GetQueueInfoCardResponseDataCard> cards) {
        Cards = cards;
    }
}
