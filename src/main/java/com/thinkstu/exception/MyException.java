package com.thinkstu.exception;

import com.thinkstu.entity.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author : ThinkStu
 * @since : 2023/4/9, 21:36, 周日
 **/
@RestControllerAdvice
public class MyException {
    @ExceptionHandler(Exception.class)
    ResultEntity exception() {
        return new ResultEntity(404, null, null, null);
    }
}
