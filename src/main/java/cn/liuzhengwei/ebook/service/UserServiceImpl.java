package cn.liuzhengwei.ebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(String account, String password, String name) {
        jdbcTemplate.update("insert into USERS(ACCOUNT, PASSWORD, NAME, ALLOWED, ISMANAGER) values(?, ?, ?, ?, ?)", account, password, name, true, false);
    }

    @Override
    public Integer getAllUsers() {
        return jdbcTemplate.queryForObject("select count(1) from USERS", Integer.class);
    }

    @Override
    public void deleteAllUsers() {
        jdbcTemplate.update("delete from USERS");
    }
}