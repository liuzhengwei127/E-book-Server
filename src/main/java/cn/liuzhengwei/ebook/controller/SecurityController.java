package cn.liuzhengwei.ebook.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
public class SecurityController {

    @GetMapping(value="/noLogin")
    public String loginFail(){
        return "请先登录";
    }
}
