package cn.liuzhengwei.ebook.service;

import cn.liuzhengwei.ebook.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public List<Book> getBooks() {
        RowMapper<Book> rowMapper = new BeanPropertyRowMapper<>(Book.class);
        List<Book> books;
        books = jdbcTemplate.query("select * from BOOKS", rowMapper);
        return books;
    }
}
