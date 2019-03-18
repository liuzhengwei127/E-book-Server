package cn.liuzhengwei.ebook.service;

import cn.liuzhengwei.ebook.domain.Book;

import java.util.List;

public interface BookService {
    // 获取所有书籍
    List<Book> getBooks();
}
