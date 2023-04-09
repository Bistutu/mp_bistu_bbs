package com.thinkstu.controller;

import com.thinkstu.entity.*;
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

    @GetMapping("/{id}")
    ManEntity login(@PathVariable("id") String id) throws IOException {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "studentno=" + id
                + "&studentpassword=21218cca77804d2ba1922c33e0151105&btnlogin=登　　录");
        Request request = new Request.Builder()
                .url("http://www.sknow.com.cn/login.php?type=student")
                .method("POST", body)
//                .addHeader("Origin", "http://www.sknow.com.cn")
                .addHeader("Referer", "http://www.sknow.com.cn/login.php?schoolno=11232")
                .addHeader("User-Agent", user_agent)
                .addHeader("Cookie", "PHPSESSID=pse63n9pk5cvduhqgrb1l5mqg2; userKey=74287596b3b471e10a8ebc05927b7bf4")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        Response response   = client.newCall(request).execute();
        String   set_cookie = response.header("set-cookie");
        System.out.println(set_cookie);
        String   string  = response.body().string();
        Document doc     = Jsoup.parse(string);
        String   name    = doc.select("tr.table_wbg td:containsOwn(姓名)").first().nextElementSibling().text();
        String   sex     = doc.select("tr.table_wbg td:containsOwn(性别)").first().nextElementSibling().text();
        Element  ytxs    = null;
        Element  runLong = null;
        if ("男".equals(sex)) {
            ytxs = doc.select("tr.table_wbg td:containsOwn(引体向上)").first().nextElementSibling();
            runLong = doc.select("tr.table_wbg td:containsOwn(1000米跑)").first().nextElementSibling();

        } else {
            ytxs = doc.select("tr.table_wbg td:containsOwn(1分钟仰卧起坐)").first().nextElementSibling();
            runLong = doc.select("tr.table_wbg td:containsOwn(800米跑)").first().nextElementSibling();

        }
        String    height    = doc.select("tr.table_wbg td:containsOwn(身高)").first().nextElementSibling().text();
        String    weight    = doc.select("tr.table_wbg td:containsOwn(体重)").first().nextElementSibling().text();
        Element   bmi       = doc.select("tr.table_wbg td:containsOwn(身体形态(BMI))").first().nextElementSibling();
        Element   ldty      = doc.select("tr.table_wbg td:containsOwn(立定跳远)").first().nextElementSibling();
        Element   fhl       = doc.select("tr.table_wbg td:containsOwn(身体机能(肺活量))").first().nextElementSibling();
        Element   run50     = doc.select("tr.table_wbg td:containsOwn(50米跑)").first().nextElementSibling();
        Element   xltqq     = doc.select("tr.table_wbg td:containsOwn(坐立体前屈)").first().nextElementSibling();
        Element   yy        = doc.select("tr.table_wbg td:containsOwn(左眼视力(无需测试))").first().nextElementSibling();
        Element   zy        = doc.select("tr.table_wbg td:containsOwn(右眼视力(无需测试))").first().nextElementSibling();
        ManEntity manEntity = new ManEntity();
        Other     other     = new Other();
        Info      info      = new Info();
        info.setName(name);
        info.setSex(sex);
        info.setHeight(height);
        info.setWeight(weight);
        other.setBmi(new CommonEntity(bmi));
        other.setLdty(new CommonEntity(ldty));
        other.setFhl(new CommonEntity(fhl));
        other.setYtxs(new CommonEntity(ytxs));
        other.setRun_short(new CommonEntity(run50));
        other.setRun_long(new CommonEntity(runLong));
        other.setXltqq(new CommonEntity(ldty));
        other.setYy(new CommonEntity(yy));
        other.setZy(new CommonEntity(zy));
        manEntity.setInfo(info);
        manEntity.setOther(other);
        return manEntity;
    }
}
