package cn.liuzhengwei.ebook;

import cn.liuzhengwei.ebook.domain.Book;
import cn.liuzhengwei.ebook.service.BookService;
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
	private BookService bookSerivce;

	@Test
	public void testGetBooks() {
		List<Book> books = bookSerivce.getBooks();
		Assert.assertEquals(3, books.size());
	}
}