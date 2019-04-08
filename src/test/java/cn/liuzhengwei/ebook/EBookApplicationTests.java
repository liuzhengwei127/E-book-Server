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
		booktoadd.setAuthor("111");
		booktoadd.setISBN("111");
		booktoadd.setName("111");
		booktoadd.setOutline("111");
		booktoadd.setPress("111");
		booktoadd.setUrl("111");
		booktoadd.setYear("1111");
		booktoadd.setPages(111);
		booktoadd.setStock(123);
		booktoadd.setPrice(11f);

		bookMapper.addBook(booktoadd);
		book = bookMapper.getBook("111");
		Assert.assertEquals(123, book.getStock().intValue());
	}
}