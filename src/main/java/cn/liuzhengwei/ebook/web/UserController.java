package cn.liuzhengwei.ebook.web;

import cn.liuzhengwei.ebook.domain.LoginState;
import cn.liuzhengwei.ebook.domain.User;
import cn.liuzhengwei.ebook.domain.UserState;
import cn.liuzhengwei.ebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public User signUp(@RequestBody User user) {
        User test = userservice.getUser(user.getAccount());
        if (test.getAccount().length() > 0){
            test = new User();
            return test;
        } else {
            userservice.create(user.getAccount(),user.getPassword(),user.getName());
            return user;
        }
    }

    // 监听'/user/login',接受用户json参数 判断用户登录态并返回json
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public LoginState login(@RequestBody User user){
        LoginState loginState = userservice.getLoginState(user.getAccount(),user.getPassword());
        return loginState;
    }

    // 监听'/user/switch',接受用户json参数 改变用户禁用态
    @RequestMapping(value = "/change", method = RequestMethod.POST)
    @ResponseBody
    public User change(@RequestBody User user) {
        String account = user.getAccount();
        User userToChange = userservice.getUser(account);
        if (userToChange.getAllowed()) {
            userservice.banUser(account);
        } else {
            userservice.allowUser(account);
        }

        userToChange = userservice.getUser(account);
        return userToChange;
    }

    @RequestMapping(value = "/states", method = RequestMethod.GET)
    @ResponseBody
    public List<UserState> getUserStates() {
        List<UserState> userStates = userservice.getUserStates();
        return userStates;
    }
}
