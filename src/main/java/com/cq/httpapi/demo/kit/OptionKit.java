package com.cq.httpapi.demo.kit;

import com.cq.httpapi.demo.exception.TooManyOptionsException;

import java.util.ArrayList;

public class OptionKit {

    /**
     * 从options中选取一个，若msg中不包含任何一个选项，则返回""
     *
     * @param msg
     * @param defaultOptions
     * @param options
     * @return
     */
    public static String selectOption(String msg, String defaultOptions, String... options) {
        ArrayList<String> result = new ArrayList<>();
        for (String option : options) {
            if (msg.contains(option)) {
                result.add(option);
            }
        }
        if (result.size() == 0) {
            return defaultOptions;
        }
        if (result.size() != 1) {
            StringBuilder exceptionDesc = new StringBuilder("Too many options in parameter msg, msg contains :");
            for (String option : result) {
                exceptionDesc.append("\n");
                exceptionDesc.append(option);
            }
            throw new TooManyOptionsException(exceptionDesc.toString());
        } else {
            return result.get(0);
        }
    }
}
