package com.cq.httpapi.demo.dto.SZJ.Request.BackEndDataRequest;

public class BatchAddUserCardsData {

    public Integer CardInfoId;
    public Integer Fate;
    public Integer IsGodofWar;
    public Integer FightingCapacity;

    public Integer getCardInfoId() {
        return CardInfoId;
    }

    public void setCardInfoId(Integer cardInfoId) {
        CardInfoId = cardInfoId;
    }

    public Integer getFate() {
        return Fate;
    }

    public void setFate(Integer fate) {
        Fate = fate;
    }

    public Integer getIsGodOfWar() {
        return IsGodofWar;
    }

    public void setIsGodofWar(Integer isGodofWar) {
        IsGodofWar = isGodofWar;
    }

    public Integer getFightingCapacity() {
        return FightingCapacity;
    }

    public void setFightingCapacity(Integer fightingCapacity) {
        FightingCapacity = fightingCapacity;
    }
}
