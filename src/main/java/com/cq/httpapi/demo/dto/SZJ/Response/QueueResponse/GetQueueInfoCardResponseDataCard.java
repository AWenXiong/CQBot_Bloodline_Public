package com.cq.httpapi.demo.dto.SZJ.Response.QueueResponse;

import com.cq.httpapi.demo.entity.SZJ.Szjqueuecard;
import com.cq.httpapi.demo.kit.ObjectKit;

public class GetQueueInfoCardResponseDataCard {

    public Long Id;
    public Long CardInfoId;
    public Double Position;

    public GetQueueInfoCardResponseDataCard(Szjqueuecard szjqueuecard) throws Exception {
        ObjectKit.deliverPropIgnoreCase(this, szjqueuecard);
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setCardInfoId(Long cardInfoId) {
        CardInfoId = cardInfoId;
    }

    public void setPosition(Double position) {
        Position = position;
    }
}
