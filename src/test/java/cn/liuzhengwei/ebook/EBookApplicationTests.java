package cn.liuzhengwei.ebook;

import cn.liuzhengwei.ebook.entity.Book;
import cn.liuzhengwei.ebook.entity.LoginState;
import cn.liuzhengwei.ebook.entity.User;
import cn.liuzhengwei.ebook.entity.UserState;
import cn.liuzhengwei.ebook.mapper.BookMapper;
import cn.liuzhengwei.ebook.mapper.UserMapper;
import cn.liuzhengwei.ebook.service.UserService;
import cn.liuzhengwei.ebook.service.UserServiceImpl;
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
	private BookMapper bookMapper;

	@Test
	public void testGetLoginState() {
		Book book = bookMapper.getBook("999");
		Assert.assertEquals("999", book.getISBN());
	}
}