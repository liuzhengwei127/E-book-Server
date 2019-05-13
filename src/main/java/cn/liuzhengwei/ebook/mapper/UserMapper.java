package cn.liuzhengwei.ebook.mapper;

import cn.liuzhengwei.ebook.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    User getUser(String account);
    User getLoginState(String account, String password);
    int changeUser(Boolean allowed, String account);
    void createUser(String account, String password, String name, Boolean allowed, Boolean isManager, String mail);
    List<User> getUserState();
    User getUserWithMail(String mail);
}
