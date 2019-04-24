package com.cq.httpapi.demo.dto.response.notice;

import com.cq.httpapi.demo.dto.response.Response;

public class GroupFileUploadResponse extends Response {

    private String id;
    private String name;
    private Long size;
    private Long busid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getBusid() {
        return busid;
    }

    public void setBusid(Long busid) {
        this.busid = busid;
    }

    @Override
    public String toString() {
        return "GroupFileUploadResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", busid=" + busid +
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
