package com.cq.httpapi.demo.service;

import com.cq.httpapi.demo.dto.SZJ.Request.CardGroupRequest.*;
import com.cq.httpapi.demo.entity.SZJ.Szjusercardgroupinfo;

import java.util.ArrayList;

public interface SZJUserCardGroupInfoService {

    ArrayList<Szjusercardgroupinfo> getAllGroups(GetAllGroupsRequest request);

    Long createGroupInfo(CreateGroupRequest request);

    boolean updateGroupInfo(UpdateGroupRequest request);

    Szjusercardgroupinfo getGroupInfo(GetGroupInfoRequest request);

    boolean delete(DeleteGroupRequest request);

    Szjusercardgroupinfo getById(Long id);

    ArrayList<Szjusercardgroupinfo> getByOpenId(String openId);
}
