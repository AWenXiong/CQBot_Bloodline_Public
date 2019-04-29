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

    // 发送文件系统内的图片
    // filePath为绝对路径
    public static String sendFSPic(String filePath) {
        StringBuilder res = new StringBuilder("[CQ:image,file=file:///");
        res.append(filePath);
        res.append("]");
        return res.toString();
    }

    // 发送自定义分享
    public static String sendShare(String url, String title, String content, String image) {
        StringBuilder res = new StringBuilder("[CQ:share,url=");
        if (url != null && !url.isEmpty()) {
            res.append(url);
        }
        if (title != null && !title.isEmpty()) {
            res.append(",title=");
            res.append(title);
        }
        if (content != null && !content.isEmpty()) {
            res.append(",content=");
            res.append(content);
        }
        if (image != null && !image.isEmpty()) {
            res.append(",image=");
            res.append(image);
        }
        res.append("]");
        return res.toString();
    }

//    public static String getQQMusic(String guild, String title) {
//        String qqMusicAPI = "https://api.bzqll.com/music/tencent/search?key=579621905&s=" + title + "&limit=100&offset=0&type=song";
//        JSONObject res = UrlKit.sendGet(qqMusicAPI);
//        JSONArray datas = res.getJSONArray("data");
//        JSONObject firstData = datas.getJSONObject(0);
//        String CQCode = music("qq", firstData.getString("id"));
//        System.err.println(CQCode);
//        return CQCode;
//    }
}
