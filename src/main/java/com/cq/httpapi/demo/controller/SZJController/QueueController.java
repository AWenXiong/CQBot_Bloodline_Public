package com.cq.httpapi.demo.controller.SZJController;

import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.*;
import com.cq.httpapi.demo.dto.SZJ.Response.QueueResponse.*;
import com.cq.httpapi.demo.exception.SZJException.SZJErrorCode;
import com.cq.httpapi.demo.exception.SZJException.SZJException;
import com.cq.httpapi.demo.service.SZJService.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

@Api(tags = "配队相关")
@RestController
@RequestMapping(value = "/szj")
public class QueueController {

    @Resource
    private SZJQueueInfoConfigSerivce szjQueueInfoConfigSerivce;
    @Resource
    private SZJQueueInfoService szjQueueInfoService;
    @Resource
    private SZJQueueLevelService szjQueueLevelService;
    @Resource
    private SZJQueueCardService szjQueueCardService;
    @Resource
    private SZJUserCardsEditingService szjUserCardsEditingService;


    @ApiOperation(value = "获取用户配队规则信息")
    @RequestMapping(value = "/GetUserQueueInfoConfig", method = RequestMethod.POST)
    public GetUserQueueInfoConfigResponse getUserQueueInfoConfig(@RequestBody GetUserQueueInfoConfigRequest request) {
        GetUserQueueInfoConfigResponse response = new GetUserQueueInfoConfigResponse();
        try {
            ArrayList<GetUserQueueInfoConfigResponseData> data = szjQueueInfoConfigSerivce.getUserQueueInfoConfig(request);
            response.setSuccess(true);
            response.setData(data);
        } catch (SZJException e) {
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return response;
    }

    /**
     * 新增/编辑用户配队规则信息
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "新增/编辑用户配队规则信息")
    @RequestMapping(value = "/EditUserQueueInfoConfig", method = RequestMethod.POST)
    public EditUserQueueInfoConfigResponse editUserQueueInfoConfig(@RequestBody EditUserQueueInfoConfigRequest request) {
        EditUserQueueInfoConfigResponse response = new EditUserQueueInfoConfigResponse();
        try {
            szjQueueInfoConfigSerivce.editUserQueueInfoConfig(request);
            response.setSuccess(true);
        } catch (SZJException e) {
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return response;
    }

    /**
     * 新增新的自动配队
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "新增新的自动配队")
    @RequestMapping(value = "/CreateQueueInfo", method = RequestMethod.POST)
    public CreateQueueInfoResponse createQueueInfo(@RequestBody CreateQueueInfoRequest request) {
        CreateQueueInfoResponse response = new CreateQueueInfoResponse();
        try {
            response = szjQueueInfoService.createQueueInfo(request);
            response.setSuccess(true);
        } catch (SZJException e) {
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return response;
    }


    @ApiOperation(value = "获取自动配队的卡牌信息")
    @RequestMapping(value = "/GetQueueInfoCard", method = RequestMethod.POST)
    public GetQueueInfoCardResponse getQueueInfoCard(@RequestBody GetQueueInfoCardRequest request) {
        GetQueueInfoCardResponse response = new GetQueueInfoCardResponse();
        try {
            ArrayList<GetQueueInfoCardResponseData> data = szjQueueCardService.getQueueInfoCard(request);
            System.err.println();
            response.setSuccess(true);
            response.setData(data);
        } catch (SZJException e) {
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return response;
    }


    @ApiOperation(value = "新增/编辑用户配队卡牌信息")
    @RequestMapping(value = "/EditUserQueueCard", method = RequestMethod.POST)
    public EditUserQueueCardResponse EditUserQueueCard(@RequestBody EditUserQueueCardRequest request) {
        EditUserQueueCardResponse response = new EditUserQueueCardResponse();
        try {
            szjQueueCardService.editUserQueueCard(request);
            response.setSuccess(true);
        } catch (SZJException e) {
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return response;
    }


    @ApiOperation(value = "获取待选卡牌")
    @RequestMapping(value = "/GetUserCardsEditing", method = RequestMethod.POST)
    public GetUserCardsEditingResponse getUserCardsEditing(@RequestBody GetUserCardsEditingRequest request) {
        GetUserCardsEditingResponse response = new GetUserCardsEditingResponse();
        try {
            ArrayList<GetUserCardsEditingResponseData> data = szjUserCardsEditingService.getUserCardsEditing(request);
            response.setSuccess(true);
            response.setData(data);
        } catch (SZJException e) {
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return response;
    }


    @ApiOperation(value = "新增待选卡牌")
    @RequestMapping(value = "/AddUserCardsEditing", method = RequestMethod.POST)
    public AddUserCardsEditingResponse addUserCardsEditing(@RequestBody AddUserCardsEditingRequest request) {
        AddUserCardsEditingResponse response = new AddUserCardsEditingResponse();
        try {
            szjUserCardsEditingService.addUserCardsEditing(request);
            response.setSuccess(true);
        } catch (SZJException e) {
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return response;
    }


    @ApiOperation(value = "删除待选卡牌")
    @RequestMapping(value = "/DeletionUserCardsEditing", method = RequestMethod.POST)
    public DeletionUserCardsEditingResponse deletionUserCardsEditing(@RequestBody DeletionUserCardsEditingRequest request) {
        DeletionUserCardsEditingResponse response = new DeletionUserCardsEditingResponse();
        try {
            szjUserCardsEditingService.deletionUserCardsEditing(request);
            response.setSuccess(true);
        } catch (SZJException e) {
            response.setError(e);
        } catch (Exception e) {
            response.setError(SZJErrorCode.UNKNOWN_EXCEPTION);
        }
        return response;
    }
}
