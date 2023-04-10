package com.thinkstu.controller;

import com.thinkstu.entity.*;
import com.thinkstu.utils.*;
import okhttp3.RequestBody;
import okhttp3.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;

/**
 * @author : ThinkStu
 * @since : 2023/4/9, 11:15, 周日
 **/
@RestController
@RequestMapping("/")
public class LoginController {
    @Autowired
    OkHttpClient client;
    String user_agent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36";
    @Autowired
    @Qualifier("noRedirect")
    OkHttpClient noRedirectOkhttp;
    @Autowired
    TipsUtils tipsUtils;


    @GetMapping("/{id}")
    ResultEntity login(@PathVariable("id") String id) throws IOException {
        Request request = new Request.Builder()
                .url("http://www.sknow.com.cn/login.php?schoolno=11232")
                .addHeader("User-Agent", user_agent)
                .build();
        Response response   = client.newCall(request).execute();
        String   set_cookie = response.header("set-cookie");
        String   PHPSESSION = set_cookie.substring(set_cookie.indexOf("=") + 1, set_cookie.lastIndexOf(";"));
        response.close();
        // 利用此发送第二个请求
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "studentno=" + id +
                "&studentpassword=21218cca77804d2ba1922c33e0151105&btnlogin=登　　录");
        request = new Request.Builder()
                .url("http://www.sknow.com.cn/login.php?type=student")
                .method("POST", body)
                .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.9")
                .addHeader("Cache-Control", "max-age=0")
                .addHeader("DNT", "1")
                .addHeader("Origin", "http://www.sknow.com.cn")
                .addHeader("Proxy-Connection", "keep-alive")
                .addHeader("Referer", "http://www.sknow.com.cn/login.php?schoolno=11232")
                .addHeader("Upgrade-Insecure-Requests", "1")
                .addHeader("User-Agent", user_agent)
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        response = noRedirectOkhttp.newCall(request).execute();
        set_cookie = response.header("set-cookie");
        response.close();

        // 发送第三个请求
        request = new Request.Builder()
                .url("http://www.sknow.com.cn/main.php")
                .addHeader("Cookie", "PHPSESSID=" + PHPSESSION + "; userKey=" + set_cookie.substring(set_cookie.indexOf("=") + 1))
                .addHeader("User-Agent", user_agent)
                .build();
        response = client.newCall(request).execute();
        String data = response.body().string();
        response.close();
        Document doc     = Jsoup.parse(data);
        String   name    = doc.select("tr.table_wbg td:containsOwn(姓名)").first().nextElementSibling().text();
        String   sex     = doc.select("tr.table_wbg td:containsOwn(性别)").first().nextElementSibling().text();
        Element  ytxs    = null;
        Element  runLong = null;

        String  height    = doc.select("tr.table_wbg td:containsOwn(身高)").first().nextElementSibling().text();
        String  weight    = doc.select("tr.table_wbg td:containsOwn(体重)").first().nextElementSibling().text();
        Element bmi       = doc.select("tr.table_wbg td:containsOwn(身体形态(BMI))").first().nextElementSibling();
        Element ldty      = doc.select("tr.table_wbg td:containsOwn(立定跳远)").first().nextElementSibling();
        Element fhl       = doc.select("tr.table_wbg td:containsOwn(身体机能(肺活量))").first().nextElementSibling();
        Element run_short = doc.select("tr.table_wbg td:containsOwn(50米跑)").first().nextElementSibling();
        Element xltqq     = doc.select("tr.table_wbg td:containsOwn(坐立体前屈)").first().nextElementSibling();
        Element yy        = doc.select("tr.table_wbg td:containsOwn(左眼视力(无需测试))").first().nextElementSibling();
        Element zy        = doc.select("tr.table_wbg td:containsOwn(右眼视力(无需测试))").first().nextElementSibling();
        if ("男".equals(sex)) {
            ytxs = doc.select("tr.table_wbg td:containsOwn(引体向上)").first().nextElementSibling();
            runLong = doc.select("tr.table_wbg td:containsOwn(1000米跑)").first().nextElementSibling();
        } else {
            ytxs = doc.select("tr.table_wbg td:containsOwn(1分钟仰卧起坐)").first().nextElementSibling();
            runLong = doc.select("tr.table_wbg td:containsOwn(800米跑)").first().nextElementSibling();
        }
        Other other = new Other();
        Info  info  = new Info();
        info.setName(name);
        info.setSex(sex);
        info.setHeight(height);
        info.setWeight(weight);
        other.setBmi(new CommonEntity(bmi, "BMI"));
        other.setLdty(new CommonEntity(ldty, "立定跳远"));
        other.setFhl(new CommonEntity(fhl, "肺活量"));
        if ("男".equals(sex)) {
            other.setYtxs(new CommonEntity(ytxs, "引体向上"));
            other.setRun_long(new CommonEntity(runLong, "1000米跑"));
        } else {
            other.setYtxs(new CommonEntity(ytxs, "仰卧起坐"));
            other.setRun_long(new CommonEntity(runLong, "800米跑"));
        }
        other.setRun_short(new CommonEntity(run_short, "50米跑"));
        other.setXltqq(new CommonEntity(xltqq, "坐位体前屈"));
        other.setYy(new CommonEntity(yy, "右眼视力"));
        other.setZy(new CommonEntity(zy, "右眼视力"));

//        return new ManEntity(info, other, tipsUtils.bmiEvaluate(bmi.text()));
        return tipsUtils.bmiEvaluate(info, other);
    }
}
