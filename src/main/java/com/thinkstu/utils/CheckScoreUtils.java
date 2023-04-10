package com.thinkstu.utils;

import org.springframework.stereotype.*;

/**
 * @author : ThinkStu
 * @since : 2023/4/10, 10:40, 周一
 **/
@Component
public class CheckScoreUtils {
    Integer check(String value) {
        Integer parsedValue = -1;
        try {
            parsedValue = Integer.parseInt(value);
        } catch (Exception ex) {
            // 什么也不做
        }
        return parsedValue;
    }
}
