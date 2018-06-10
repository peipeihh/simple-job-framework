package com.pphh.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Please add description here.
 *
 * @author huangyinhuang
 * @date 6/5/2018
 */
@RestController
@SpringBootApplication
public class SimpleJobApp {

    public static void main(String[] args) {
        SpringApplication.run(SimpleJobApp.class, args);
    }

    @RequestMapping("hello")
    public String hello() {
        return "hello,world";
    }

}
