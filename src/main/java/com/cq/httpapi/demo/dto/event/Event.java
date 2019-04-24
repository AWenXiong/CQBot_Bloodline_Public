package com.cq.httpapi.demo.dto.event;

public class Event {

    private String post_type;
    private Long time;
    private Long self_id;

    @Override
    public String toString() {
        return "event{" +
                "post_type='" + post_type + '\'' +
                ", time=" + time +
                ", self_id=" + self_id +
                '}';
    }

    public String getPost_type() {
        return post_type;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getSelf_id() {
        return self_id;
    }

    public void setSelf_id(Long self_id) {
        this.self_id = self_id;
    }
}
