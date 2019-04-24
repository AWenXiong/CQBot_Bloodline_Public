package com.cq.httpapi.demo.kit;

public class CQCodeKit {

    public static String atSomebody(String userId) {
        String res = "[CQ:at,qq=" + userId + "]";
        return res;
    }

    public static String dice() {
        String res = "[CQ:dice]";
        return res;
    }

    public static String rps() {
        String res = "[CQ:rps]";
        return res;
    }

    public static String shake() {
        String res = "[CQ:shake]";
        return res;
    }

    public static String music(String type, String musicId) {
        String res = "[CQ:music, type=" + type + ",id=" + musicId + "]";
        return res;
    }

    public static String parseAtToFormalString(String message) {
        StringBuilder res = new StringBuilder(message);
        while (res.toString().contains("[CQ:at,qq=")) {
            res.replace(res.indexOf("[CQ:at,qq="), res.indexOf("[CQ:at,qq=") + "[CQ:at,qq=".length(), "@");
            res.replace(res.indexOf("]"), res.indexOf("]") + 1, "");
        }
        return res.toString();
    }
}
