package com.thinkstu.config;

import okhttp3.*;
import org.springframework.context.annotation.*;

/**
 * @author : ThinkStu
 * @since : 2023/4/9, 11:16, 周日
 **/
@Configuration
public class OkhttpConfig {
    @Bean
    OkHttpClient getOkhttp() {
        return new OkHttpClient().newBuilder()
                .build();
    }
}
