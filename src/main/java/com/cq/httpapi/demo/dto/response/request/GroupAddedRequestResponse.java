package com.cq.httpapi.demo.dto.response.request;

import com.cq.httpapi.demo.dto.response.Response;

public class GroupAddedRequestResponse extends Response {

    private boolean approve;
    private String reason;

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "GroupAddedRequestResponse{" +
                "approve=" + approve +
                ", reason='" + reason + '\'' +
                '}';
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFlag() {
        return super.isFlag();
    }

    @Override
    public void setFlag(boolean flag) {
        super.setFlag(flag);
    }
}
