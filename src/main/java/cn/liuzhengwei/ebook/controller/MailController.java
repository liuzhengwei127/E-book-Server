package cn.liuzhengwei.ebook.controller;

import cn.liuzhengwei.ebook.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
    @Autowired
    private MailService mailService;

    @GetMapping(value = "/mail")
    public String sendMail(@RequestParam("phoneNumber") String phoneNumber) {
        return mailService.sendMail(phoneNumber);
    }
}
