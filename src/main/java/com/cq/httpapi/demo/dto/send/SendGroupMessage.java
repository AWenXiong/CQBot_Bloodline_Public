package com.cq.httpapi.demo.dto.send;

import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.dto.response.Response;
import com.cq.httpapi.demo.kit.UrlKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendGroupMessage extends Response {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String ip;
    private String group_id;
    private String message;
    private boolean auto_escape;
    private String[] splitStrings;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isAuto_escape() {
        return auto_escape;
    }

    public void setAuto_escape(boolean auto_escape) {
        this.auto_escape = auto_escape;
    }

    public String[] getSplitStrings() {
        return splitStrings;
    }

    public void setSplitStrings(String[] splitStrings) {
        this.splitStrings = splitStrings;
    }

    @Override
    public void execute() {
        try {
            StringBuilder sb = new StringBuilder(message);
            if (sb.length() > 170) { // 不知道为啥是170，以后有机会再测试消息长度具体设置成多少再分段比较好
                while (sb.length() > 170) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("group_id", this.getGroup_id());
                    jsonObject.put("message", sb.substring(0, 170));
                    jsonObject.put("auto_escape", this.isAuto_escape());
                    UrlKit.sendPost(this.ip + ApiPath.SEND_GROUP_MESSAGE.getUrlPath(), jsonObject);
                    sb.replace(0, 170, "");
                    logger.warn(jsonObject.toString());
                }
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("group_id", this.getGroup_id());
            jsonObject.put("message", sb.toString());
            jsonObject.put("auto_escape", this.isAuto_escape());
            UrlKit.sendPost(this.ip + ApiPath.SEND_GROUP_MESSAGE.getUrlPath(), jsonObject);
            logger.warn(jsonObject.toString());
        } catch (Exception e) {

        }
    }


    public void executeWithSplitWord() {
        try {
            StringBuilder sb = new StringBuilder(message);
            if (sb.length() > 170 && this.splitStrings.length > 0) { // 不知道为啥是170，以后有机会再测试消息长度具体设置成多少再分段比较好
                for (int i = 0; i < this.splitStrings.length; i++) {
                    StringBuilder message = new StringBuilder(sb.substring(0, sb.indexOf(splitStrings[i])));
                    while (message.length() > 170) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("group_id", this.getGroup_id());
                        jsonObject.put("message", message.toString());
                        jsonObject.put("auto_escape", this.isAuto_escape());
                        UrlKit.sendPost(this.ip + ApiPath.SEND_GROUP_MESSAGE.getUrlPath(), jsonObject);
                        message.replace(0, 170, "");
                        logger.warn(jsonObject.toString());
                    }
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("group_id", this.getGroup_id());
                    jsonObject.put("message", message.toString());
                    jsonObject.put("auto_escape", this.isAuto_escape());
                    UrlKit.sendPost(this.ip + ApiPath.SEND_GROUP_MESSAGE.getUrlPath(), jsonObject);
                    logger.warn(jsonObject.toString());
                    sb.replace(0, sb.indexOf(splitStrings[i]), "");
                }
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("group_id", this.getGroup_id());
            jsonObject.put("message", sb.toString());
            jsonObject.put("auto_escape", this.isAuto_escape());
            UrlKit.sendPost(this.ip + ApiPath.SEND_GROUP_MESSAGE.getUrlPath(), jsonObject);
            logger.warn(jsonObject.toString());
        } catch (Exception e) {

        }
    }
}
