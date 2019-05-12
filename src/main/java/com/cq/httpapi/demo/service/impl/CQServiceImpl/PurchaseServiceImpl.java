package com.cq.httpapi.demo.service.impl.CQServiceImpl;

import com.cq.httpapi.demo.dao.CQdao.PurchaseDao;
import com.cq.httpapi.demo.entity.CQ.Purchase;
import com.cq.httpapi.demo.kit.TimeKit;
import com.cq.httpapi.demo.service.CQService.PurchaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Resource
    private PurchaseDao purchaseDao;

    @Override
    public boolean appendEndTimeByUserIdAndService(String userId, String service, Long day, String modifier) {
        Long id = purchaseDao.getPurchaseByUserIdAndService(userId, service);
        if (id != null) {
            purchaseDao.appendEndTimeById(id, day);
            purchaseDao.updateModifiedInfoById(id, TimeKit.getFormalTime(), modifier, "延长服务有效期");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<Purchase> getPurchaseByUserId(String userId) {
        ArrayList<Purchase> purchases = purchaseDao.getPurchaseByUserId(userId);
        return purchases;
    }

    @Override
    public boolean haveService(String userId, String service) {
        long existPurchase = purchaseDao.existPurchase(userId, service);
        if (existPurchase > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean updateEndTimeByUserIdAndService(String userId, String service, String endTime, String modifier) {
        try {
            Long id = purchaseDao.getPurchaseByUserIdAndService(userId, service);
            if (id != null) {
                purchaseDao.updateEndTimeById(id, endTime);
                purchaseDao.updateModifiedInfoById(id, TimeKit.getFormalTime(), modifier, "更新服务有效期");
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deletePurchaseByUserIdAndService(String userId, String service) {
        try {
            Long id = purchaseDao.getPurchaseByUserIdAndService(userId, service);
            if (id != null) {
                purchaseDao.deletePurchaseById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public boolean createPurchase(String guild, String service, String endTime, String createUserId) {
        purchaseDao.insertPurchase();
        Long id = purchaseDao.getMaxId();
        if (id != null) {
            purchaseDao.updateGuildById(id, guild);
            purchaseDao.updateServiceById(id, service);
            purchaseDao.updateEndTimeById(id, endTime);
            purchaseDao.updateCreaterInfoById(id, TimeKit.getFormalTime(), createUserId, "新建服务");
            return true;
        } else {
            return false;
        }
    }
}
