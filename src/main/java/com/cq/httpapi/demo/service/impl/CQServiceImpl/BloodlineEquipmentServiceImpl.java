package com.cq.httpapi.demo.service.impl.CQServiceImpl;

import com.cq.httpapi.demo.dao.CQdao.BloodlineEquipmentDao;
import com.cq.httpapi.demo.entity.CQ.BloodlineEquipment;
import com.cq.httpapi.demo.entity.CQ.BloodlineItem;
import com.cq.httpapi.demo.entity.CQ.Card;
import com.cq.httpapi.demo.service.CQService.BloodlineEquipmentService;
import com.cq.httpapi.demo.service.CQService.BloodlineItemService;
import com.cq.httpapi.demo.service.CQService.CardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BloodlineEquipmentServiceImpl implements BloodlineEquipmentService {

    @Resource
    private BloodlineEquipmentDao equipmentDao;
    @Resource
    private CardService cardService;
    @Resource
    private BloodlineItemService itemService;

    @Override
    public BloodlineEquipment getEquipment(Long cardId, String type) {
        return equipmentDao.getByCardIdAndType(cardId, type);
    }

    @Override
    public boolean createEquipmentRecord(String cardNickname, String equipmentName, String type) {
        try {
            Card card = cardService.getAllInfo(cardNickname);
            if (card == null) {
                return false;
            }
            BloodlineItem equipment = itemService.getItemByName(equipmentName);
            if (equipment == null) {
                return false;
            }

            if (!this.existEquipmentRecord(cardNickname, type)) {
                equipmentDao.createBloodlineEquipment(card.getId(), equipment.getId(), type);
            } else {
                BloodlineEquipment equipmentRecord = equipmentDao.getByCardIdAndType(card.getId(), type);
                if (equipment.getId() != equipmentRecord.getEquipmentId()) {
                    equipmentDao.updateEquipmentId(equipmentRecord.getId(), equipment.getId());
                }
                if (!equipment.getType().equals(type)) {
                    equipmentDao.updateType(equipmentRecord.getId(), type);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean existEquipmentRecord(String cardName, String type) {
        try {
            Card card = cardService.getAllInfo(cardName);
            if (equipmentDao.existEquipmentRecord(card.getId(), type) == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
