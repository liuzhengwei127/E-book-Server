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
		Assert.assertEquals(4, books.size());
	}

	@Test
	public void testAddBook() throws Exception{
		Book book = new Book();
		book.setAuthor("韩寒");
		book.setName("他的国");
		book.setISBN("2222");
		book.setOutline("hehehe");
		book.setPrice(12.0);
		book.setStock(12);
		bookSerivce.addBook(book);
		book = bookSerivce.getBook("2222");
		Assert.assertEquals("2222",book.getISBN());
		bookSerivce.deleteBook("2222");
	}

	@Test
	public void testModifyBook() {
		Book book = new Book();
		book.setAuthor("韩寒");
		book.setName("他的国");
		book.setISBN("2222");
		book.setOutline("hehehe");
		book.setPrice(12.0);
		book.setStock(12);
		bookSerivce.addBook(book);
		Assert.assertEquals(12, book.getPrice().doubleValue(), 0.01);
		Assert.assertEquals("他的国", book.getName());
		book.setPrice(22.0);
		book.setName("测试");
		book = bookSerivce.modifyBook(book);
		Assert.assertEquals(22.0, book.getPrice().doubleValue(), 0.01);
		Assert.assertEquals("测试", book.getName());
		bookSerivce.deleteBook("2222");
	}
}