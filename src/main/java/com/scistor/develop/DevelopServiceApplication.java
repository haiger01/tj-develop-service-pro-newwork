package com.scistor.develop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement                //事物注解
@EnableCaching
public class DevelopServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevelopServiceApplication.class, args);
    }

}
