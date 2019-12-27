package com.zhd.ultimate.sociology;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.zhd.ultimate.sociology.mapping")
@SpringBootApplication
@Slf4j(topic = "common")
public class SociologyApplication {

    public static void main(String[] args) {
        log.info("启动开始");
        SpringApplication.run(SociologyApplication.class, args);
        log.info("启动结束");
    }

}
