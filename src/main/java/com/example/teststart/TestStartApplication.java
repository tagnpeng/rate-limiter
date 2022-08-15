package com.example.teststart;

import org.simple.test.CustomizeUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class TestStartApplication implements CommandLineRunner {

    @Resource
    private CustomizeUtil customizeUtil;

    public static void main(String[] args) {
        SpringApplication.run(TestStartApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(customizeUtil.test("嗨嘿嗨"));
    }
}
