package com.cq.httpapi.demo.controller.SZJController;

import com.cq.httpapi.demo.dto.SZJ.Request.CardGroupRequest.*;
import com.cq.httpapi.demo.dto.SZJ.Response.CardGroupResponse.*;
import com.cq.httpapi.demo.entity.SZJ.Szjinvitationcode;
import com.cq.httpapi.demo.entity.SZJ.Szjusercardgroupinfo;
import com.cq.httpapi.demo.exception.SZJException.UserGroupInfoException.*;
import com.cq.httpapi.demo.kit.ObjectKit;
import com.cq.httpapi.demo.service.SZJService.SZJInvitationCodeService;
import com.cq.httpapi.demo.service.SZJService.SZJUserCardGroupInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/szj")
public class CardGroupController {

    @Resource
    private SZJUserCardGroupInfoService cardGroupInfoService;
    @Resource
    private SZJInvitationCodeService invitationCodeService;

    /**
     * 卡组查询
     *
     * @param request 卡组查询请求
     * @return errorCode    message
     * 0            正常
     * 1            登录码不存在！
     * 9            未知错误！
     */
    @RequestMapping(value = "/GetAllGroups", method = RequestMethod.POST)
    public GetAllGroupsResponse getAllGroups(@RequestBody GetAllGroupsRequest request) {
        GetAllGroupsResponse response = new GetAllGroupsResponse();
        try {
            ArrayList<Szjusercardgroupinfo> cardGroupInfos = cardGroupInfoService.getAllGroups(request);
            ArrayList<GetAllGroupsResponseData> datas = new ArrayList<>();
            for (Szjusercardgroupinfo cardGroupInfo : cardGroupInfos) {
                GetAllGroupsResponseData data = new GetAllGroupsResponseData(cardGroupInfo);
                datas.add(data);
            }
            response.setSuccess(true);
            response.setData(datas);
        } catch (GetAllGroupsException e) {
            response.setError(false, e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            response.setError(false, 9, e.getMessage());
        }
        return response;
    }

    /**
     * 卡组创建
     *
     * @param request 卡组创建查询
     * @return errorCode    message
     * 0            正常
     * 1            登录码不存在
     */
    @RequestMapping(value = "/CreateGroup", method = RequestMethod.POST)
    public CreateGroupResponse createGroup(@RequestBody CreateGroupRequest request) {
        CreateGroupResponse response = new CreateGroupResponse();
        try {
            long id = cardGroupInfoService.createGroupInfo(request);
            response.setSuccess(true);
            response.setId(id);
        } catch (CreateUserCardGroupException e) {
            response.setError(false, e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            response.setError(false, 9, e.getMessage());
        }
        return response;
    }

    /**
     * 卡组编辑
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/UpdateGroup", method = RequestMethod.POST)
    public UpdateGroupResponse updateGroup(@RequestBody UpdateGroupRequest request) {
        UpdateGroupResponse response = new UpdateGroupResponse();
        try {
            cardGroupInfoService.updateGroupInfo(request);
            response.setSuccess(true);
            response.setId(request.getId());
        } catch (UpdateUserCardGroupException e) {
            response.setError(false, e.getErrorCode(), e.getMessage());
        }
        return response;
    }

    /**
     * 卡组信息获取
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetGroupInfo", method = RequestMethod.POST)
    public GetGroupInfoResponse getGroupInfo(@RequestBody GetGroupInfoRequest request) {
        GetGroupInfoResponse response = new GetGroupInfoResponse();
        try {
            Szjusercardgroupinfo cardGroupInfo = cardGroupInfoService.getGroupInfo(request);
            Szjinvitationcode invitationCode = invitationCodeService.getByCode(cardGroupInfo.getInvitationCode());

            try {
                response.setSuccess(true);
                ObjectKit.deliverPropIgnoreCase(response, cardGroupInfo);
                ObjectKit.deliverPropIgnoreCase(response, invitationCode);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (GetGroupInfoException e) {
            response.setError(false, e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            response.setError(false, 9, e.getMessage());
        }
        return response;
    }

    /**
     * 卡组删除（逻辑删除）
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/DeleteGroup", method = RequestMethod.POST)
    public DeleteGroupResponse deleteGroup(@RequestBody DeleteGroupRequest request) {
        DeleteGroupResponse response = new DeleteGroupResponse();
        try {
            cardGroupInfoService.delete(request);
            response.setSuccess(true);
        } catch (DeleteUserCardGroupException e) {
            response.setError(false, e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            response.setError(false, 9, e.getMessage());
        }
        return response;
    }
}
