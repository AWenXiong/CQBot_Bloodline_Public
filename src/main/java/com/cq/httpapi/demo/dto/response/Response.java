package com.cq.httpapi.demo.dto.response;

public abstract class Response {

    private boolean flag = false;
    private boolean opFlag = false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isOpFlag() {
        return opFlag;
    }

    public void setOpFlag(boolean opFlag) {
        this.opFlag = opFlag;
    }

    public abstract void execute();
}
