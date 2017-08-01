package com.easy.springboot.chapter1_helloworld.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @RequestMapping("hello")
    String hello() {
        return "Hello World! " + new Date();
    }
}
