package com.cq.httpapi.demo.kit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.dto.send.ApiPath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlKit {

    public static JSONObject sendPost(ApiPath url, Object Data) {
        try {
            JSONObject jsonObject = JSON.parseObject(Data.toString());

            try {
                URL path = new URL(url.getUrlPath()); //url地址

                HttpURLConnection connection = (HttpURLConnection) path.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                connection.setUseCaches(false);
                connection.setInstanceFollowRedirects(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.connect();

                try (OutputStream os = connection.getOutputStream()) {
                    os.write(jsonObject.toString().getBytes("UTF-8"));
                }

                StringBuffer sbf = new StringBuffer();
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()))) {
                    String lines;
                    while ((lines = reader.readLine()) != null) {
                        lines = new String(lines.getBytes(), "utf-8");
                        sbf.append(lines);
                    }
                }
                connection.disconnect();
                return JSONObject.parseObject(sbf.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {

        }
        return new JSONObject();
    }

}
