package com.cq.httpapi.demo.service.CQService;

import com.cq.httpapi.demo.entity.Purchase;

import java.util.ArrayList;

public interface PurchaseService {

    boolean createPurchase(String userId, String service, String endTime, String createUserId);

    boolean haveService(String userId, String service);

    ArrayList<Purchase> getPurchaseByUserId(String userId);

    boolean updateEndTimeByUserIdAndService(String userId, String service, String endTime, String modifier);

    boolean appendEndTimeByUserIdAndService(String userId, String service, Long day, String modifier);

    boolean deletePurchaseByUserIdAndService(String userId, String service);
}
