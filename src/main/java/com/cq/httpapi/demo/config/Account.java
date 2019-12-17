package com.cq.httpapi.demo.config;

import java.util.ArrayList;

public enum Account {

    ROBOT1("大多莉", "2078003617", "http://localhost:5700"),
    ROBOT2("中多莉", "1239298446", "http://localhost:5701"),
    ROBOT3("小多莉", "3416875131", "http://localhost:5702"),
    TEST_ROBOT("测试多莉", "3210530348", "http://localhost:5703"),
    TEST_ROBOT2("测试狗屎", "502063298", "http://localhost:5704");

    private String name;
    private String id;
    private String ip;

    Account(String name, String id, String ip) {
        this.name = name;
        this.id = id;
        this.ip = ip;
    }

    public static Account getById(String id) {
        for (Account acc : Account.values()) {
            if (acc.id.equals(id)) {
                return acc;
            }
        }
        return null;
    }

    public static String getIpById(String id) {
        for (Account acc : Account.values()) {
            if (acc.id.equals(id)) {
                return acc.getIp();
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public ArrayList<Account> getAllAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();
        for (Account acc : Account.values()) {
            accounts.add(acc);
        }
        return accounts;
    }
}
