package cn.liuzhengwei.ebook.web;

import cn.liuzhengwei.ebook.domain.Book;
import cn.liuzhengwei.ebook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    // 创建连接数据库的接口实例
    @Autowired
    private BookService bookService;

    // 监听'/book/get' 返回所有书籍数据
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public List<Book> getBooks() {
        List<Book> books= bookService.getBooks();
        return books;
    }

    // 监听'/book/add' 添加书籍，写入数据库
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Book addBook(@RequestBody Book book) throws Exception{
        bookService.addBook(book);
        Book result = bookService.getBook(book.getISBN());
        return result;
    }

    // 监听'/book/modify' 修改相应书籍的相应数据
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public Book modifyBook(@RequestBody Book book) {
        Book result = bookService.modifyBook(book);
        return result;
    }
}
