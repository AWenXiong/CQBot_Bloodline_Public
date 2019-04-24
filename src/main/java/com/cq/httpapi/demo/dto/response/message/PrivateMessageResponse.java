package com.cq.httpapi.demo.dto.response.message;

public class PrivateMessageResponse extends MessageResponse {

    private String reply;
    private boolean auto_escape;

    public String getReply() {
        return reply;
    }

    @Override
    public void setReply(String reply) {
        this.reply = reply;
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

    public boolean isAuto_escape() {
        return auto_escape;
    }

    @Override
    public void setAuto_escape(boolean auto_escape) {
        this.auto_escape = auto_escape;
    }

    @Override
    public String toString() {
        return "PrivateMessageResponse{" +
                "reply=" + reply +
                ", auto_escape=" + auto_escape +
                '}';
    }
}
