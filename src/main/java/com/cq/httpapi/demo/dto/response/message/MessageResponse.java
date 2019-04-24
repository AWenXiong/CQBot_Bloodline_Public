package com.cq.httpapi.demo.dto.response.message;

import com.cq.httpapi.demo.dto.response.Response;

public abstract class MessageResponse extends Response {

    @Override
    public boolean isFlag() {
        return super.isFlag();
    }

    @Override
    public void setFlag(boolean flag) {
        super.setFlag(flag);
    }

    @Override
    public boolean isOpFlag() {
        return super.isOpFlag();
    }

    @Override
    public void setOpFlag(boolean opFlag) {
        super.setOpFlag(opFlag);
    }

    @Override
    public void execute() {

    }

    public void setReply(String reply) {
    }

    public void setAuto_escape(boolean auto_escape) {
    }

}
