package cn.liuzhengwei.ebook.service;

//连接数据库User表的的抽象类
public interface UserService {

    /**
     * 新增一个用户
     * @param account
     * @param password
     * @param name
     */
    void create(String account, String password, String name);

    /**
     * 获取用户总量
     */
    Integer getAllUsers();

    /**
     * 删除所有用户
     */
    void deleteAllUsers();
}
