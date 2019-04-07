package cn.liuzhengwei.ebook.service;

import cn.liuzhengwei.ebook.entity.LoginState;
import cn.liuzhengwei.ebook.entity.User;
import cn.liuzhengwei.ebook.entity.UserState;

import java.util.List;

//连接数据库User表的的抽象类
public interface UserService {

    //新增一个用户
    void create(String account, String password, String name);
    void create(String account, String password, String name, Boolean allowed, Boolean isManager);

    //获取单个用户数据
    User getUser(String account);

    //判断用户状态
    LoginState getLoginState(String account, String password);

    //禁用用户
    int banUser(String account);

    //解禁用户
    int allowUser(String account);

    //获得所有用户状态
    List<UserState> getUserStates();
}
