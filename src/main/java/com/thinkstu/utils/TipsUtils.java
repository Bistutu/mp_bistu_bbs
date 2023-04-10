package com.thinkstu.utils;

import com.thinkstu.data.*;
import com.thinkstu.entity.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * @author : ThinkStu
 * @since : 2023/4/9, 21:22, 周日
 **/
@Component
public class TipsUtils {
    @Autowired
    BMIStringConfig config;

    public ResultEntity bmiEvaluate(Info info, Other other, String bmi) {
        String msg      = "";
        String level    = "";
        Double bmiValue = 0.0;
        Tips   tips     = new Tips();
        try {
            bmiValue = Double.parseDouble(bmi);
            if (bmiValue < 18) {
                msg = config.get_18_low()[new Random().nextInt(10)];
                level = "王者";
            } else if (bmiValue < 25) {
                msg = config.get_18_25()[new Random().nextInt(10)];
                level = "钻石";
            } else if (bmiValue < 28) {
                msg = config.get_25_28()[new Random().nextInt(10)];
                level = "黄金";
            } else if (bmiValue < 35) {
                msg = config.get_28_35()[new Random().nextInt(10)];
                level = "白银";
            } else {
                msg = config.get_35_height()[new Random().nextInt(10)];
                level = "青铜";
            }
        } catch (Exception e) {
            // 解析错误，什么也不做
        }
        tips.setLevel(level);
        tips.setBMIMsg(msg);
        return new ResultEntity(200, info, other, tips);
    }
}
