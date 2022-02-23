package com.qks.makerSpace;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
//@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
@SpringBootApplication
@MapperScan("con.qks.makerSpace")
public class MakerSpaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MakerSpaceApplication.class, args);
    }

}
