package com.cq.httpapi.demo.service.impl.CQServiceImpl;

import com.cq.httpapi.demo.dao.CQdao.BloodlineItemDao;
import com.cq.httpapi.demo.entity.CQ.BloodlineItem;
import com.cq.httpapi.demo.service.CQService.BloodlineItemService;
import com.cq.httpapi.demo.service.CQService.CardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class BloodlineItemServiceImpl implements BloodlineItemService {

    @Resource
    private BloodlineItemDao itemDao;
    @Resource
    private CardService cardService;

    @Override
    public BloodlineItem getItemById(Long id) {
        return itemDao.getById(id);
    }

    @Override
    public BloodlineItem getItemByName(String name) {
        return itemDao.getByName(name);
    }

    @Override
    public ArrayList<BloodlineItem> getItemsByType(String type) {
        return itemDao.getByType(type);
    }

    @Override
    public boolean createItem(String name, String type, String description) {
        try {
            if (!this.existItemName(name)) {
                itemDao.createItem(name, type, description);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteItem(String name) {
        try {
            if (this.existItemName(name)) {
                itemDao.deleteByName(name);
            }
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateItemType(String name, String type) {
        try {
            BloodlineItem item = itemDao.getByName(name);
            if (item != null) {
                itemDao.updateType(item.getId(), type);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateItemDesc(String name, String desc) {
        try {
            BloodlineItem item = itemDao.getByName(name);
            if (item != null) {
                itemDao.updateDescription(item.getId(), desc);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private boolean existItemName(String name) {
        if (itemDao.existItemName(name) == 0) {
            return false;
        } else {
            return true;
        }
    }

}
