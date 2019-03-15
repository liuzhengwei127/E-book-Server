package cn.liuzhengwei.ebook.web;

import cn.liuzhengwei.ebook.domain.User;
import cn.liuzhengwei.ebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    private UserService userservice;
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(@RequestBody User user){
        userservice.create(user.getAccount(),user.getPassword(),user.getName());
        return "success";
    }
}
