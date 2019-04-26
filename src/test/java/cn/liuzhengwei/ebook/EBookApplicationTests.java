package cn.liuzhengwei.ebook;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class EBookApplicationTests {
    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("756670011@qq.com");
        message.setTo("756670011@qq.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");

        mailSender.send(message);
    }

}