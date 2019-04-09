package cn.liuzhengwei.ebook.controller;

import cn.liuzhengwei.ebook.entity.Book;
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

    // 监听'/book/search' 接受一个参数 返回过滤后的书籍数据
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public List<Book> searchBooks(@RequestParam("filter") String filter) {
        List<Book> books= bookService.searchBooks(filter);
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
        int row = bookService.modifyBook(book);
        Book result;
        if (row>0){
            result = bookService.getBook(book.getISBN());
        } else {
            result = new Book();
        }
        return result;
    }

    // 监听'/book/detail' 返回相应书籍详细信息
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Book modifyBook(@RequestParam("ISBN") String ISBN) {
        Book result = bookService.getDetail(ISBN);
        return result;
    }

    // 监听'/book/delete' 数据库中删除相应书籍
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public String deleteBook(@RequestParam("ISBN") String ISBN) {
        int result = bookService.deleteBook(ISBN);
        if (result > 0) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }
}
