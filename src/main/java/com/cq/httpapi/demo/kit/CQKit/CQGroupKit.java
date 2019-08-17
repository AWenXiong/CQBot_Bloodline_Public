package com.cq.httpapi.demo.kit.CQKit;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.config.Account;
import com.cq.httpapi.demo.dto.send.ApiPath;
import com.cq.httpapi.demo.kit.UrlKit;

import java.util.ArrayList;

public class CQGroupKit {

    public static ArrayList<String> getGroupAdmin(String selfId, String groupId) {

        JSONObject data = new JSONObject();
        data.put("group_id", groupId);
        Account account = Account.getById(selfId);
        JSONObject list = UrlKit.sendPost(account.getName() + ApiPath.GET_GROUP_MEMBER_LIST.getUrlPath(), data);

        ArrayList<String> admins = new ArrayList<>();
        JSONArray members = list.getJSONArray("data");
        for (int i = 0; i < members.size(); i++) {
            JSONObject tmp = JSONObject.parseObject(members.get(i).toString());
            if (tmp.getString("role").equals("admin") || tmp.getString("role").equals("owner")) {
                admins.add(tmp.getString("user_id"));
            }
        }
        return admins;
    }
}
