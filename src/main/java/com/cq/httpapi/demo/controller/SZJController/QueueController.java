package com.cq.httpapi.demo.controller.SZJController;

import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.*;
import com.cq.httpapi.demo.dto.SZJ.Response.QueueResponse.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/szj")
public class QueueController {

    /**
     * 获取用户配队规则信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetUserQueueInfoConfig", method = RequestMethod.POST)
    public GetUserQueueInfoConfigResponse getUserQueueInfoConfig(@RequestBody GetUserQueueInfoConfigRequest request) {
        return null;
    }

    /**
     * 新增/编辑用户配队规则信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/EditUserQueueInfoConfig", method = RequestMethod.POST)
    public EditUserQueueInfoConfigResponse editUserQueueInfoConfig(@RequestBody EditUserQueueInfoConfigRequest request) {
        return null;
    }

    /**
     * 新增新的自动配队
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/CreateQueueInfo", method = RequestMethod.POST)
    public CreateQueueInfoResponse createQueueInfo(@RequestBody CreateQueueInfoRequest request) {
        return null;
    }

    /**
     * 获取自动配队的卡牌信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetQueueInfoCard", method = RequestMethod.POST)
    public GetQueueInfoCardResponse getQueueInfoCard(@RequestBody GetQueueInfoCardRequest request) {
        return null;
    }

    /**
     * 新增/编辑用户配队卡牌信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/EditUserQueueCard", method = RequestMethod.POST)
    public EditUserQueueCardResponse EditUserQueueCard(@RequestBody EditUserQueueCardRequest request) {
        return null;
    }

    /**
     * 获取待选卡牌
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/GetUserCardsEditing", method = RequestMethod.POST)
    public GetUserCardsEditingResponse getUserCardsEditing(@RequestBody GetUserCardsEditingRequest request) {
        return null;
    }

    /**
     * 新增待选卡牌
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/AddUserCardsEditing", method = RequestMethod.POST)
    public AddUserCardsEditingResponse addUserCardsEditing(@RequestBody AddUserCardsEditingRequest request) {
        return null;
    }

    /**
     * 物理删除待选卡牌
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/DeletionUserCardsEditing", method = RequestMethod.POST)
    public DeletionUserCardsEditingResponse deletionUserCardsEditing(@RequestBody DeletionUserCardsEditingRequest request) {
        return null;
    }
}
