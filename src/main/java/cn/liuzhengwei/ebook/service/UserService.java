package cn.liuzhengwei.ebook.service;

import cn.liuzhengwei.ebook.domain.LoginState;
import cn.liuzhengwei.ebook.domain.User;

//连接数据库User表的的抽象类
public interface UserService {

    //新增一个用户
    void create(String account, String password, String name);
    void create(String account, String password, String name, Boolean allowed, Boolean isManager);

    //获取用户总量
    Integer getAllUsers();

    //删除所有用户
    void deleteAllUsers();

    //获取单个用户数据
    User getUser(String account) throws Exception;

    //判断用户状态
    LoginState getLoginState(String account, String password);

    //禁用用户
    void banUser(String account);

    //解禁用户
    void allowUser(String account);
}
