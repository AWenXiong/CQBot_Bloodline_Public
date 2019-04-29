package com.cq.httpapi.demo.dto.SZJ.Request.QueueRequest;

public class EditUserQueueInfoConfigRequestData {

    public Long Id;
    public String ParameterCode;
    public String ParameterValue;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getParameterCode() {
        return ParameterCode;
    }

    public void setParameterCode(String parameterCode) {
        ParameterCode = parameterCode;
    }

    public String getParameterValue() {
        return ParameterValue;
    }

    public void setParameterValue(String parameterValue) {
        ParameterValue = parameterValue;
    }
}
