package com.cq.httpapi.demo.dto.SZJ.Response.QueueResponse;

import com.cq.httpapi.demo.dto.SZJ.SZJResponse;

public class GetUserQueueInfoConfigResponse extends SZJResponse {

    public Object data;

    @Override
    public String toString() {
        return "GetUserQueueInfoConfigResponse{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", errorCode=" + errorCode +
                ", success=" + success +
                '}';
    }

    public Object getDatat() {
        return data;
    }

    public void setDatat(Object datat) {
        this.data = datat;
    }
}
