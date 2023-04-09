package com.thinkstu.entity;

import lombok.*;

/**
 * @author : ThinkStu
 * @since : 2023/4/9, 11:33, 周日
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManEntity {
    Info info;
    Other other;
    String massage;
}
