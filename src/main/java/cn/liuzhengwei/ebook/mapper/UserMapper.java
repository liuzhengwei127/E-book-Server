package cn.liuzhengwei.ebook.mapper;

import cn.liuzhengwei.ebook.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User getUser(String account);
}
