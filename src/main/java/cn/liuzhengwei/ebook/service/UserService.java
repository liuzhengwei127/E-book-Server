package cn.liuzhengwei.ebook.service;

import cn.liuzhengwei.ebook.domain.LoginState;

//连接数据库User表的的抽象类
public interface UserService {

    //新增一个用户
    void create(String account, String password, String name);
    void create(String account, String password, String name, Boolean allowed, Boolean isManager);

    //获取用户总量
    Integer getAllUsers();

    //删除所有用户
    void deleteAllUsers();

    //判断用户状态
    LoginState getUserState(String account, String password);
}
