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
		bookMapper.deleteBook("123");
		Book book = bookMapper.getBook("123");
		Assert.assertEquals(null, book);

		Book booktoadd = new Book();
		booktoadd.setAuthor("333");
		booktoadd.setISBN("333");
		booktoadd.setName("333");
		booktoadd.setOutline("3333");
		booktoadd.setPress("333");
		booktoadd.setUrl("333");
		booktoadd.setYear("333");
		booktoadd.setPages(111);
		booktoadd.setStock(123);
		booktoadd.setPrice(11f);

		bookMapper.addBook(booktoadd);
		book = bookMapper.getBook("333");
		Assert.assertEquals(123, book.getStock().intValue());
		bookMapper.deleteBook("333");
	}
}