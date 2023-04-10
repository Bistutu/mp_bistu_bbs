package com.thinkstu.entity;


import lombok.*;

import java.util.*;

/**
 * @author : ThinkStu
 * @since : 2023/4/9, 21:44, 周日
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tips {
    String level;
    List<EvaluateEntity> message;
}
