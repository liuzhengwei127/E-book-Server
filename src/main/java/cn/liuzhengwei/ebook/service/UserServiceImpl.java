package cn.liuzhengwei.ebook.service;

import cn.liuzhengwei.ebook.domain.LoginState;
import cn.liuzhengwei.ebook.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

//连接数据库User表的的具体实现类
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(String account, String password, String name) {
        jdbcTemplate.update("insert into USERS(ACCOUNT, PASSWORD, NAME, ALLOWED, ISMANAGER) values(?, ?, ?, ?, ?)", account, password, name, true, false);
    }

    @Override
    public void create(String account, String password, String name, Boolean allowed, Boolean isManager) {
        jdbcTemplate.update("insert into USERS(ACCOUNT, PASSWORD, NAME, ALLOWED, ISMANAGER) values(?, ?, ?, ?, ?)", account, password, name, allowed, isManager);
    }


    @Override
    public Integer getAllUsers() {
        return jdbcTemplate.queryForObject("select count(1) from USERS", Integer.class);
    }

    @Override
    public void deleteAllUsers() {
        jdbcTemplate.update("delete from USERS");
    }

    @Override
    public LoginState getUserState(String account, String password) {
        LoginState state = new LoginState();
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);

        List<User> users;
        users = jdbcTemplate.query("select * from USERS where account='"+account+"' and password='"+password+"'", rowMapper);

        if (users.size() <= 0){
            state.setLogin(false);
            users = jdbcTemplate.query("select * from USERS where account='"+account+"'", rowMapper);
            if (users.size() > 0) {
                state.setCode(1);
                state.setMessage("密码错误");
            } else {
                state.setCode(0);
                state.setMessage("用户不存在");
            }
        } else {
           if (users.size() > 1) {
               state.setLogin(false);
               state.setCode(3);
               state.setMessage("数据库数据错误");
           } else {
               User user = users.get(0);
               if (user.getAllowed()) {
                   if (user.getIsmanager()) {
                       state.setLogin(true);
                       state.setCode(1);
                       state.setName(user.getName());
                       state.setMessage("用户存在，为管理员");
                   } else {
                       state.setLogin(true);
                       state.setCode(0);
                       state.setName(user.getName());
                       state.setMessage("用户存在，为普通用户");
                   }
               } else {
                   state.setLogin(false);
                   state.setCode(2);
                   state.setMessage("用户被禁用");
               }
           }
        }

        return state;
    }
}