package cn.liuzhengwei.ebook.controller;

import cn.liuzhengwei.ebook.entity.LoginState;
import cn.liuzhengwei.ebook.entity.User;
import cn.liuzhengwei.ebook.entity.UserState;
import cn.liuzhengwei.ebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        User existing = userservice.getUser(user.getAccount());
        if (existing != null){
            existing = new User();
            return existing;
        } else {
            // 用户名已经存在
            userservice.create(user.getAccount(),user.getPassword(),user.getName());
            user = userservice.getUser(user.getAccount());
            return user;
        }
    }

    // 监听'/user/login',接受用户json参数 判断用户登录态并返回json
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public LoginState login(@RequestBody User user, HttpServletRequest request){
        LoginState loginState = userservice.getLoginState(user.getAccount(),user.getPassword());
        HttpSession session = request.getSession();
        session.setAttribute("loginState", loginState);
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

    // 监听'/user/states',获取所有用户的权限
    @RequestMapping(value = "/states", method = RequestMethod.GET)
    @ResponseBody
    public List<UserState> getUserStates() {
        List<UserState> userStates = userservice.getUserStates();
        return userStates;
    }

    // 监听'/user/init',判断用户是否最近登录
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    @ResponseBody
    public LoginState ifUserLogin(HttpSession session) {
        LoginState loginState;
        loginState = (LoginState)session.getAttribute("loginState");
        if (loginState == null)
            loginState = new LoginState();
        return loginState;
    }

    // 监听'/user/logout',从session中去除用户登录态
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public String logout(HttpSession session) {
        session.removeAttribute("loginState");
        return "登出成功";
    }
}
