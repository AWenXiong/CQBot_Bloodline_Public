package com.cq.httpapi.demo.dto.response.message;

public class DiscussionMessageResponse extends MessageResponse {

    private String reply;
    private boolean auto_escape;
    private boolean at_sender;

    @Override
    public boolean isFlag() {
        return super.isFlag();
    }

    @Override
    public void setFlag(boolean flag) {
        super.setFlag(flag);
    }

    @Override
    public void execute() {

    }

    public String getReply() {
        return reply;
    }

    @Override
    public void setReply(String reply) {
        this.reply = reply;
    }

    public boolean isAuto_escape() {
        return auto_escape;
    }

    @Override
    public void setAuto_escape(boolean auto_escape) {
        this.auto_escape = auto_escape;
    }

    public boolean isAt_sender() {
        return at_sender;
    }

    public void setAt_sender(boolean at_sender) {
        this.at_sender = at_sender;
    }

    @Override
    public String toString() {
        return "DiscussionMessageResponse{" +
                "reply=" + reply +
                ", auto_escape=" + auto_escape +
                ", at_sender=" + at_sender +
                '}';
    }
}
