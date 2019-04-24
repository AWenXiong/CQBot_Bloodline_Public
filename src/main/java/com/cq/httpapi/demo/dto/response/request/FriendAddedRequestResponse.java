package com.cq.httpapi.demo.dto.response.request;

import com.cq.httpapi.demo.dto.response.Response;

public class FriendAddedRequestResponse extends Response {

    private boolean approve;
    private String remark;

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FriendAddedRequestResponse{" +
                "approve=" + approve +
                ", remark='" + remark + '\'' +
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
