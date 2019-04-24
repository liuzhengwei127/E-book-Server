package cn.liuzhengwei.ebook.security;

import cn.liuzhengwei.ebook.entity.Security;
import cn.liuzhengwei.ebook.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailService implements UserDetailsService {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("登录用户名:" + username);
        System.out.println("username?????");
        String password = passwordEncoder.encode("123456");

        logger.info("数据库密码:" + password);
        Security user = new Security();
        user.setEnabled(true);
        user.setPassword(password);
        user.setUsername(username);
        user.setRoles(AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return user;
    }
}
