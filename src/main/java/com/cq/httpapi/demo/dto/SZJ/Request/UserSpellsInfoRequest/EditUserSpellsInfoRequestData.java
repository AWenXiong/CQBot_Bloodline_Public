package com.cq.httpapi.demo.dto.SZJ.Request.UserSpellsInfoRequest;

public class EditUserSpellsInfoRequestData {
    public Long Id;
    public Long SpellId;
    public Long FightingCapacity;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getSpellId() {
        return SpellId;
    }

    public void setSpellId(Long spellId) {
        SpellId = spellId;
    }

    public Long getFightingCapacity() {
        return FightingCapacity;
    }

    public void setFightingCapacity(Long fightingCapacity) {
        FightingCapacity = fightingCapacity;
    }
}
