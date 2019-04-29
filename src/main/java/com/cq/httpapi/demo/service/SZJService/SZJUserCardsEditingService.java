package com.cq.httpapi.demo.service.SZJService;

import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.AddUserCardsEditingRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.DeletionUserCardsEditingRequest;
import com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest.GetUserCardsEditingRequest;
import com.cq.httpapi.demo.dto.SZJ.Response.QueueResponse.GetUserCardsEditingResponseData;

import java.util.ArrayList;

public interface SZJUserCardsEditingService {

    ArrayList<GetUserCardsEditingResponseData> getUserCardsEditing(GetUserCardsEditingRequest request);

    boolean addUserCardsEditing(AddUserCardsEditingRequest request);

    boolean deletionUserCardsEditing(DeletionUserCardsEditingRequest request);

}
