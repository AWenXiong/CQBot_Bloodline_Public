package com.cq.httpapi.demo.kit;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.dto.send.ApiPath;

import java.util.ArrayList;

public class CQGroupKit {

    public static ArrayList<String> getGroupAdmin(String groupId) {

        JSONObject data = new JSONObject();
        data.put("group_id", groupId);
        JSONObject list = UrlKit.sendPost(ApiPath.GET_GROUP_MEMBER_LIST, data);

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
