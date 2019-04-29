package com.cq.httpapi.demo.dto.SZJ.Request.UserCardInfoRequest;

public class EditUserCardsInfoData {

    public Long Id;
    public Long CardInfoId;
    public Long FightingCapacity;
    public Long Fate;
    public Long IsGodofWar;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getCardInfoId() {
        return CardInfoId;
    }

    public void setCardInfoId(Long cardInfoId) {
        CardInfoId = cardInfoId;
    }

    public Long getFightingCapacity() {
        return FightingCapacity;
    }

    public void setFightingCapacity(Long fightingCapacity) {
        FightingCapacity = fightingCapacity;
    }

    public Long getFate() {
        return Fate;
    }

    public void setFate(Long fate) {
        Fate = fate;
    }

    public Long getIsGodofWar() {
        return IsGodofWar;
    }

    public void setIsGodofWar(Long isGodofWar) {
        IsGodofWar = isGodofWar;
    }
}
