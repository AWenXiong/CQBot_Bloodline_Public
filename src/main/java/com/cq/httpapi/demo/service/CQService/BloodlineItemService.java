package com.cq.httpapi.demo.service.CQService;

import com.cq.httpapi.demo.entity.CQ.BloodlineItem;

import java.util.ArrayList;

public interface BloodlineItemService {

    BloodlineItem getItemById(Long id);

    BloodlineItem getItemByName(String name);

    ArrayList<BloodlineItem> getItemsByType(String type);

    boolean createItem(String name, String type, String description);

    boolean updateItemType(String name, String type);

    boolean updateItemDesc(String name, String desc);

    boolean deleteItem(String name);
}
