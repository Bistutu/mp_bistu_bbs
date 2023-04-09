package com.thinkstu.entity;

import lombok.*;
import org.jsoup.nodes.*;

/**
 * @author : ThinkStu
 * @since : 2023/4/9, 11:38, 周日
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonEntity {
    String detail;
    String score;
    String evaluate;

    public CommonEntity(Element one) {
        this.detail = one.text();
        this.score = one.nextElementSibling().text();
        this.evaluate = one.nextElementSibling().nextElementSibling().text();
    }
}
