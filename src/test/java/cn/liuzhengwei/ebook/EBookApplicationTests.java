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
	private BookService bookService;

	@Test
	public void testGetBook() {
		List<Book> books = bookService.searchBooks("生");
		Assert.assertEquals("浮生六记", books.get(0).getName());
	}
}