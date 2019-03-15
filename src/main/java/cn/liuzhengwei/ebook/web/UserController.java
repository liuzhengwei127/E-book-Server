package cn.liuzhengwei.ebook.web;

import cn.liuzhengwei.ebook.domain.User;
import cn.liuzhengwei.ebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//数据库中关于user表的Restful api
@RestController
@RequestMapping(value="/user")
public class UserController {

    // 创建连接数据库的接口实例
    @Autowired
    private UserService userservice;

    // 监听'/user/signup',接受json参数 并将用户信息写入数据库中
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public User signUp(@RequestBody User user){
        userservice.create(user.getAccount(),user.getPassword(),user.getName());
        return user;
    }
}
