package cn.liuzhengwei.ebook.service;

import cn.liuzhengwei.ebook.domain.Book;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;


import java.util.LinkedList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 获取所有书籍
    @Override
    public List<Book> getBooks() {
        RowMapper<Book> rowMapper = new BeanPropertyRowMapper<>(Book.class);
        List<Book> books;
        books = jdbcTemplate.query("select name,author,ISBN,outline,stock,price,url from BOOKS", rowMapper);
        return books;
    }

    // 搜索书籍
    public List<Book> searchBooks(String text){
        RowMapper<Book> rowMapper = new BeanPropertyRowMapper<>(Book.class);
        List<Book> books;
        books = jdbcTemplate.query("select * from BOOKS where name like '%"+text+"%'", rowMapper);
        return books;
    }

    // 添加书籍
    @Override
    public void addBook(Book book) {
        jdbcTemplate.update("insert into BOOKS(NAME, AUTHOR, ISBN, OUTLINE, STOCK, PRICE, url) values(?, ?, ?, ?, ?, ?, ?)",
                book.getName(), book.getAuthor(), book.getISBN(), book.getOutline(), book.getStock(), book.getPrice(), book.getUrl());
    }

    // 查找书籍
    @Override
    public Book getBook(String ISBN) throws Exception{
        Book book = jdbcTemplate.queryForObject("select * from BOOKS where ISBN = '"+ISBN+"'", Book.class);
        return book;
    }

    // 删除书籍
    @Override
    public Book deleteBook(String ISBN) {
        RowMapper<Book> rowMapper = new BeanPropertyRowMapper<>(Book.class);
        List<Book> books;
        books = jdbcTemplate.query("select * from BOOKS where ISBN='"+ISBN+"'", rowMapper);
        jdbcTemplate.update("delete from BOOKS where ISBN='"+ISBN+"'");

        return books.get(0);
    }

    // 修改书籍
    @Override
    public Book modifyBook(Book book) {
        RowMapper<Book> rowMapper = new BeanPropertyRowMapper<>(Book.class);
        List<Book> books;
        String url = book.getUrl() == null ? "" : ", URL='"+StringEscapeUtils.escapeJava(book.getUrl())+"' ";

        jdbcTemplate.update("update BOOKS set NAME='"+book.getName()+"', " +
                "AUTHOR='"+book.getAuthor()+"', " +
                "ISBN='"+book.getNewisbn()+"', " +
                "OUTLINE='"+book.getOutline()+"', " +
                "STOCK="+book.getStock()+", " +
                "PRICE="+book.getPrice()+
                url+" where ISBN='"+book.getISBN()+"' ");

        books = jdbcTemplate.query("select * from BOOKS where ISBN='"+book.getNewisbn()+"'", rowMapper);
        return books.get(0);
    }

    // 获得书籍详细信息
    @Override
    public Book getDetail(String ISBN){
        RowMapper<Book> rowMapper = new BeanPropertyRowMapper<>(Book.class);
        Book book = jdbcTemplate.queryForObject("SELECT * FROM books WHERE ISBN = '"+ISBN+"'", rowMapper);
        return book;
    }
}
