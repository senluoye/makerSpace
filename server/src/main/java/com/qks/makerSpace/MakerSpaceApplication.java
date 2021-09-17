package com.qks.makerSpace;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("con.qks.makerSpace")
public class MakerSpaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MakerSpaceApplication.class, args);
    }

}
