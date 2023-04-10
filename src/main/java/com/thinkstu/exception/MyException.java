package com.thinkstu.exception;

import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author : ThinkStu
 * @since : 2023/4/9, 21:36, 周日
 **/
@RestControllerAdvice
public class MyException {
    @ExceptionHandler(Exception.class)
    Map exception() {
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("message", "账号不存在！");
        return hashMap;
    }
}
