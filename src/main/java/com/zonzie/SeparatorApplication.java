package com.zonzie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Created by zonzie on 2018/2/27.
 */
@SpringBootApplication
public class SeparatorApplication {
    public static void main(String...args) {
        SpringApplication.run(SeparatorApplication.class,args);
    }
}
