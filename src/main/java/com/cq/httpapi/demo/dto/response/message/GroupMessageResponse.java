package com.cq.httpapi.demo.dto.response.message;

public class GroupMessageResponse extends MessageResponse {

    private String reply;
    private boolean auto_escape;
    private boolean at_sender;
    private boolean delete;
    private boolean kick;
    private boolean ban;
    private Long ban_duration;

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

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isKick() {
        return kick;
    }

    public void setKick(boolean kick) {
        this.kick = kick;
    }

    public boolean isBan() {
        return ban;
    }

    public void setBan(boolean ban) {
        this.ban = ban;
    }

    public Long getBan_duration() {
        return ban_duration;
    }

    public void setBan_duration(Long ban_duration) {
        this.ban_duration = ban_duration;
    }

    @Override
    public String toString() {
        return "GroupMessageResponse{" +
                "reply=" + reply +
                ", auto_escape=" + auto_escape +
                ", at_sender=" + at_sender +
                ", delete=" + delete +
                ", kick=" + kick +
                ", ban=" + ban +
                ", ban_duration=" + ban_duration +
                '}';
    }
}
