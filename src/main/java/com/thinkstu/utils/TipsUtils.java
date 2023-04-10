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
    BMIStringConfig bmiStringConfig;
    @Autowired
    FhlStringConfig fhlStringConfig;
    @Autowired
    LdtyStringConfig ldtyStringConfig;
    @Autowired
    RunLongStringConfig runLongStringConfig;
    @Autowired
    RunShortStringConfig runShortStringConfig;
    @Autowired
    YTXSStringConfig ytxsStringConfig;
    @Autowired
    YwqzStringConfig ywqzStringConfig;
    @Autowired
    ZwtqqStringConfig zwtqqStringConfig;
    // 如果解析失败，则返回 -1
    @Autowired
    CheckScoreUtils checkScoreUtils;

    public ResultEntity bmiEvaluate(Info info, Other other) {
        String                  bmi     = other.getBmi().getDetail();
        HashMap<String, String> hashMap = new HashMap<>();

        Integer ldty     = checkScoreUtils.check(other.getLdty().getScore());
        Integer fhl      = checkScoreUtils.check(other.getFhl().getScore());
        Integer runShort = checkScoreUtils.check(other.getRun_short().getScore());
        Integer runLong  = checkScoreUtils.check(other.getRun_long().getScore());
        Integer zwtqq    = checkScoreUtils.check(other.getXltqq().getScore());

        // 特殊案例
        Integer ytxs = checkScoreUtils.check(other.getYtxs().getScore());

        String BMIMsg   = "";
        String level    = "";
        Double bmiValue = 0.0;
        Tips   tips     = new Tips();
        try {
            bmiValue = Double.parseDouble(bmi);
            if (bmiValue < 18) {
                BMIMsg = bmiStringConfig.get_18_low()[new Random().nextInt(10)];
                level = "王者";
            } else if (bmiValue < 25) {
                BMIMsg = bmiStringConfig.get_18_25()[new Random().nextInt(10)];
                level = "钻石";
            } else if (bmiValue < 28) {
                BMIMsg = bmiStringConfig.get_25_28()[new Random().nextInt(10)];
                level = "黄金";
            } else if (bmiValue < 35) {
                BMIMsg = bmiStringConfig.get_28_35()[new Random().nextInt(10)];
                level = "白银";
            } else {
                BMIMsg = bmiStringConfig.get_35_height()[new Random().nextInt(10)];
                level = "青铜";
            }
        } catch (Exception e) {
            // 解析错误，什么也不做
        }


        String ldtyString = "";
        // 超长代码 start
        if (ldty >= 0 && ldty <= 59) {
            ldtyString = ldtyStringConfig.getExcellent()[new Random().nextInt(5)];
        } else if (ldty >= 60 && ldty <= 79) {
            ldtyString = ldtyStringConfig.getGood()[new Random().nextInt(5)];
        } else if (ldty >= 80 && ldty <= 89) {
            ldtyString = ldtyStringConfig.getPass()[new Random().nextInt(5)];
        } else if (ldty >= 90 && ldty <= 100) {
            ldtyString = ldtyStringConfig.getFail()[new Random().nextInt(5)];
        }

        String fhlString = "";
        // 超长代码 start
        if (fhl >= 0 && fhl <= 59) {
            fhlString = fhlStringConfig.getExcellent()[new Random().nextInt(5)];
        } else if (fhl >= 60 && fhl <= 79) {
            fhlString = fhlStringConfig.getGood()[new Random().nextInt(5)];
        } else if (fhl >= 80 && fhl <= 89) {
            fhlString = fhlStringConfig.getPass()[new Random().nextInt(5)];
        } else if (fhl >= 90 && fhl <= 100) {
            fhlString = fhlStringConfig.getFail()[new Random().nextInt(5)];
        }

        String runShortString = "";
        // 超长代码 start
        if (runShort >= 0 && runShort <= 59) {
            runShortString = runShortStringConfig.getExcellent()[new Random().nextInt(5)];
        } else if (runShort >= 60 && runShort <= 79) {
            runShortString = runShortStringConfig.getGood()[new Random().nextInt(5)];
        } else if (runShort >= 80 && runShort <= 89) {
            runShortString = runShortStringConfig.getPass()[new Random().nextInt(5)];
        } else if (runShort >= 90 && runShort <= 100) {
            runShortString = runShortStringConfig.getFail()[new Random().nextInt(5)];
        }

        String runLongString = "";
        // 超长代码 start
        if (runLong >= 0 && runLong <= 59) {
            runLongString = runLongStringConfig.getExcellent()[new Random().nextInt(5)];
        } else if (runLong >= 60 && runLong <= 79) {
            runLongString = runLongStringConfig.getGood()[new Random().nextInt(5)];
        } else if (runLong >= 80 && runLong <= 89) {
            runLongString = runLongStringConfig.getPass()[new Random().nextInt(5)];
        } else if (runLong >= 90 && runLong <= 100) {
            runLongString = runLongStringConfig.getFail()[new Random().nextInt(5)];
        }

        String zwtqqString = "";
        // 超长代码 start
        if (zwtqq >= 0 && zwtqq <= 59) {
            zwtqqString = zwtqqStringConfig.getExcellent()[new Random().nextInt(5)];
        } else if (zwtqq >= 60 && zwtqq <= 79) {
            zwtqqString = zwtqqStringConfig.getGood()[new Random().nextInt(5)];
        } else if (zwtqq >= 80 && zwtqq <= 89) {
            zwtqqString = zwtqqStringConfig.getPass()[new Random().nextInt(5)];
        } else if (zwtqq >= 90 && zwtqq <= 100) {
            zwtqqString = zwtqqStringConfig.getFail()[new Random().nextInt(5)];
        }

        String ytxsString = "";
        // 区分
        if ("男".equals(info.getSex())) {
            // 超长代码 start
            if (ytxs >= 0 && ytxs <= 59) {
                ytxsString = ytxsStringConfig.getExcellent()[new Random().nextInt(5)];
            } else if (ytxs >= 60 && ytxs <= 79) {
                ytxsString = ytxsStringConfig.getGood()[new Random().nextInt(5)];
            } else if (ytxs >= 80 && ytxs <= 89) {
                ytxsString = ytxsStringConfig.getPass()[new Random().nextInt(5)];
            } else if (ytxs >= 90 && ytxs <= 100) {
                ytxsString = ytxsStringConfig.getFail()[new Random().nextInt(5)];
            }
            hashMap.put("引体向上", ytxsString);
            hashMap.put("1000米跑", runLongString);
        } else {
            if (ytxs >= 0 && ytxs <= 59) {
                ytxsString = ywqzStringConfig.getExcellent()[new Random().nextInt(5)];
            } else if (ytxs >= 60 && ytxs <= 79) {
                ytxsString = ywqzStringConfig.getGood()[new Random().nextInt(5)];
            } else if (ytxs >= 80 && ytxs <= 89) {
                ytxsString = ywqzStringConfig.getPass()[new Random().nextInt(5)];
            } else if (ytxs >= 90 && ytxs <= 100) {
                ytxsString = ywqzStringConfig.getFail()[new Random().nextInt(5)];
            }
            hashMap.put("仰卧起坐", ytxsString);
            hashMap.put("800米跑", runLongString);
        }

        // 对 Tips 进行修改
        hashMap.put("BMI", BMIMsg);
        hashMap.put("立定跳远", ldtyString);
        hashMap.put("肺活量", fhlString);
        hashMap.put("50米跑", runShortString);
        hashMap.put("坐位体前屈", zwtqqString);

        tips.setLevel(level);
        tips.setMessage(hashMap);

        return new ResultEntity(info, other, tips);
    }
}
