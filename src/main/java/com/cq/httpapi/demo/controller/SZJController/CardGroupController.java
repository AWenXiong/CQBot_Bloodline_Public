package com.cq.httpapi.demo.controller.SZJController;

import com.cq.httpapi.demo.dto.SZJ.Request.CardGroupRequest.*;
import com.cq.httpapi.demo.dto.SZJ.Response.CardGroupResponse.*;
import com.cq.httpapi.demo.entity.SZJ.Szjinvitationcode;
import com.cq.httpapi.demo.entity.SZJ.Szjusercardgroupinfo;
import com.cq.httpapi.demo.exception.SZJException.SZJErrorCode;
import com.cq.httpapi.demo.exception.SZJException.SZJException;
import com.cq.httpapi.demo.kit.ObjectKit;
import com.cq.httpapi.demo.service.SZJService.SZJInvitationCodeService;
import com.cq.httpapi.demo.service.SZJService.SZJUserCardGroupInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

@Api(tags = "用户卡组控制器")
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
    @ApiOperation(value = "卡组查询")
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
        } catch (SZJException e) {
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
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
    @ApiOperation(value = "卡组创建")
    @RequestMapping(value = "/CreateGroup", method = RequestMethod.POST)
    public CreateGroupResponse createGroup(@RequestBody CreateGroupRequest request) {
        CreateGroupResponse response = new CreateGroupResponse();
        try {
            long id = cardGroupInfoService.createGroupInfo(request);
            response.setSuccess(true);
            response.setId(id);
        } catch (SZJException e) {
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return response;
    }

    /**
     * 卡组编辑
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "卡组编辑")
    @RequestMapping(value = "/UpdateGroup", method = RequestMethod.POST)
    public UpdateGroupResponse updateGroup(@RequestBody UpdateGroupRequest request) {
        UpdateGroupResponse response = new UpdateGroupResponse();
        try {
            cardGroupInfoService.updateGroupInfo(request);
            response.setSuccess(true);
            response.setId(request.getId());
        } catch (SZJException e) {
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return response;
    }

    /**
     * 卡组信息获取
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "卡组信息获取")
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

        } catch (SZJException e) {
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return response;
    }

    /**
     * 卡组删除（逻辑删除）
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "卡组删除")
    @RequestMapping(value = "/DeleteGroup", method = RequestMethod.POST)
    public DeleteGroupResponse deleteGroup(@RequestBody DeleteGroupRequest request) {
        DeleteGroupResponse response = new DeleteGroupResponse();
        try {
            cardGroupInfoService.delete(request);
            response.setSuccess(true);
        } catch (SZJException e) {
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return response;
    }
}
