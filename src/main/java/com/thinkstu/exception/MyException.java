package com.thinkstu.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;

import java.util.*;

/**
 * @author : ThinkStu
 * @since : 2023/4/9, 21:36, 周日
 **/
@RestControllerAdvice
public class MyException {
    @ExceptionHandler(CrawlException.class)
    ResponseEntity<String> crawlException() {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> index() {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    Map exception() {
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("message", "账号不存在或密码错误！");
        return hashMap;
    }
}
