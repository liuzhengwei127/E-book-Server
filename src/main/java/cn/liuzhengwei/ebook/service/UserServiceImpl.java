package cn.liuzhengwei.ebook.service;

import cn.liuzhengwei.ebook.entity.LoginState;
import cn.liuzhengwei.ebook.entity.User;
import cn.liuzhengwei.ebook.entity.UserState;
import cn.liuzhengwei.ebook.mapper.UserMapper;
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

    @Autowired
    UserMapper userMapper;

    //新增一个用户
    @Override
    public void create(String account, String password, String name) {
        jdbcTemplate.update("insert into USERS(ACCOUNT, PASSWORD, NAME, ALLOWED, ISMANAGER) values(?, ?, ?, ?, ?)", account, password, name, true, false);
    }
    @Override
    public void create(String account, String password, String name, Boolean allowed, Boolean isManager) {
        jdbcTemplate.update("insert into USERS(ACCOUNT, PASSWORD, NAME, ALLOWED, ISMANAGER) values(?, ?, ?, ?, ?)", account, password, name, allowed, isManager);
    }

    //获取用户总量
    @Override
    public Integer getAllUsers() {
        return jdbcTemplate.queryForObject("select count(1) from USERS", Integer.class);
    }

    //删除所有用户
    @Override
    public void deleteAllUsers() {
        jdbcTemplate.update("delete from USERS");
    }

    //获取单个用户数据
    @Override
    public User getUser(String account) {
        return userMapper.getUser(account);
    }

    //判断用户状态
    @Override
    public LoginState getLoginState(String account, String password) {
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
                       state.setAccount(user.getAccount());
                       state.setMessage("用户存在，为管理员");
                   } else {
                       state.setLogin(true);
                       state.setCode(0);
                       state.setName(user.getName());
                       state.setAccount(user.getAccount());
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

    //禁用用户
    @Override
    public void banUser(String account) {
        jdbcTemplate.update("update USERS set allowed=false where account='"+account+"'");
    }

    //解禁用户
    @Override
    public void allowUser(String account) {
        jdbcTemplate.update("update USERS set allowed=true where account='"+account+"'");
    }

    //获得所有用户状态
    public List<UserState> getUserStates() {
        List<UserState> userStates;
        RowMapper<UserState> rowMapper = new BeanPropertyRowMapper<>(UserState.class);
        userStates = jdbcTemplate.query("select ACCOUNT,NAME,ALLOWED from USERS", rowMapper);

        return userStates;
    }
}