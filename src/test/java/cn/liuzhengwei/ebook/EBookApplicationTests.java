package cn.liuzhengwei.ebook;

import cn.liuzhengwei.ebook.entity.Book;
import cn.liuzhengwei.ebook.entity.User;
import cn.liuzhengwei.ebook.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EBookApplicationTests {
	@Autowired
	private UserMapper userMapper;

	@Test
	public void testGetBook() {
		User user = userMapper.getUser("abc");
		Assert.assertEquals("999", user.getPassword());
	}
}