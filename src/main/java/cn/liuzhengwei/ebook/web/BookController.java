package cn.liuzhengwei.ebook.web;

import cn.liuzhengwei.ebook.domain.Book;
import cn.liuzhengwei.ebook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public List<Book> getBooks() {
        List<Book> books= bookService.getBooks();
        return books;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Book addBook(@RequestBody Book book) throws Exception{
        bookService.addBook(book);
        Book result = bookService.getBook(book.getISBN());
        return result;
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public Book modifyBook(@RequestBody Book book) {
        Book result = bookService.modifyBook(book);
        return result;
    }
}
