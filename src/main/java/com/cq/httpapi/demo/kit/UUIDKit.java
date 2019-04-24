package com.cq.httpapi.demo.kit;

import java.util.UUID;

public class UUIDKit {

    public static String formUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

}
