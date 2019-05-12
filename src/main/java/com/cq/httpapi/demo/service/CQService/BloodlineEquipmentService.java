package com.cq.httpapi.demo.service.CQService;

import com.cq.httpapi.demo.entity.CQ.BloodlineEquipment;

public interface BloodlineEquipmentService {

    BloodlineEquipment getEquipment(Long cardId, String type);

    boolean createEquipmentRecord(String cardNickname, String equipmentName, String type);
}
