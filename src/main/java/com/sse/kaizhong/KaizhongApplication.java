package com.sse.kaizhong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
@MapperScan("com.sse.kaizhong.mapper")
public class KaizhongApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaizhongApplication.class, args);
    }

}
