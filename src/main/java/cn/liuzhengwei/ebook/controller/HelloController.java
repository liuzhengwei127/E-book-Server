package cn.liuzhengwei.ebook.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Restful api的开端 "Hello World"
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String index() {
        return "Hello Fucking World";
    }
}
