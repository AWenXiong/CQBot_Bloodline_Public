package com.cq.httpapi.demo.handler.httphandler;


import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.Enumeration;

public class CQHttpHandler {

    public static JSONObject getHeader(HttpServletRequest httpServletRequest) {
        Enumeration<String> requestHeader = httpServletRequest.getHeaderNames();
        JSONObject jsonObject = new JSONObject();
        while (requestHeader.hasMoreElements()) {
            String headerKey = requestHeader.nextElement().toString();
            jsonObject.put(headerKey, httpServletRequest.getHeader(headerKey));
        }
        return jsonObject;
    }

    public static JSONObject getBody(HttpServletRequest httpServletRequest) {
        try {
            BufferedReader reader = httpServletRequest.getReader();
            String str;
            StringBuffer wholeStr = new StringBuffer();
            while ((str = reader.readLine()) != null) {
                wholeStr.append(str);
            }
            return (JSONObject) JSONObject.parse(wholeStr.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }
}
