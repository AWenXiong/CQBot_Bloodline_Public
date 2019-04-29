package com.cq.httpapi.demo.kit;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cq.httpapi.demo.service.CQService.CardService;

import java.util.Scanner;

public class SQLKit {

    public static JSONArray getCardData(String filePath) {
        JSONArray res = new JSONArray();
        JSONObject jsonObject = ObjectKit.getJSONObjectFromFileUsingBufferedReader(filePath);
        JSONArray datas = jsonObject.getJSONArray("cardData");
        for (int i = 0; i < datas.size(); i++) {
            JSONObject data = datas.getJSONObject(i);
            res.add(data);

        }
        return res;
    }

    public static void importDataToDB(CardService cardService, JSONArray cardData) {
        for (int i = cardData.size() - 1; i >= 0; i--) {
            JSONObject card = cardData.getJSONObject(i);
            String fullName = card.getString("Name");
            String star = card.getString("Star");
            System.out.println(star + " " + fullName);
            Scanner scanner = new Scanner(System.in);
            String nickname = scanner.next();
            if (nickname.equals("1")) {
                continue;
            }

            int flag = 0;
            while (cardService.getId(nickname) == -1) {
                System.out.println("Card not exist!");
                nickname = scanner.next();
                if (nickname.equals("1")) {
                    flag = 1;
                    break;
                }
            }

            if (flag == 0) {
                String skill1 = card.getString("BsDesc");
                String skill2 = card.getString("TfDesc");
                String skill3 = card.getString("DzDesc");
                String skill4 = card.getString("MyDesc");
                String career = card.getString("Job");
                String partner = card.getString("RelateArr");
                if (star.equals("5")) {
                    StringBuilder sb = new StringBuilder(partner);
                    sb.delete(sb.indexOf(","), sb.indexOf("|"));
                    sb.delete(sb.indexOf(","), sb.lastIndexOf("|"));
                    sb.delete(sb.indexOf(","), sb.length());
                    System.err.println(sb);
                    cardService.updateCardPartner(nickname, sb.toString());
                }

                if (cardService.appendNickName(nickname, fullName, "多莉贝露") &&
                        cardService.updateCardSkill(nickname, skill1, skill2, skill3, skill4) &&
                        cardService.updateCardCareer(nickname, career)) {
                    System.out.println("更新成功");
                }
            }
        }
    }
}
