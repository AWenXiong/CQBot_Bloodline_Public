package com.cq.httpapi.demo.generator.CacheKeyGenerator;

import com.alibaba.fastjson.JSON;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CQKeyGenerator {

    private static String cqSp = ":";

    // 方法名:参数类型?参数值:参数类型?参数值:...
    @Bean
    public KeyGenerator towerKeyGenerator() {
        return (target, method, params) -> {
            StringBuilder key = new StringBuilder();
            key.append("tower");
            key.append(cqSp);
            for (Object param : params) {
                if (param != null) {
//                    key.append(param.getClass().getName());
//                    key.append("?");
                    key.append(JSON.toJSONString(param));
                    key.append(cqSp);
                }
            }
            return key.toString();
//            return "hello";
        };
    }

}
