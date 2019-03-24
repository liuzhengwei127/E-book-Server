package cn.liuzhengwei.ebook.service;

import cn.liuzhengwei.ebook.domain.Book;

import java.util.List;

public interface BookService {
    // 获取所有书籍
    List<Book> getBooks();

    // 搜索书籍
    List<Book> getBooks(String text);

    // 添加书籍
    void addBook(Book book);

    // 查找书籍
    Book getBook(String ISBN) throws Exception;

    // 删除书籍
    Book deleteBook(String ISBN);

    // 修改书籍
    Book modifyBook(Book book);
}
