package cn.liuzhengwei.ebook.service;

import cn.liuzhengwei.ebook.entity.Book;
import cn.liuzhengwei.ebook.mapper.BookMapper;
import org.apache.commons.text.StringEscapeUtils;
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
    private BookMapper bookMapper;

    // 获取所有书籍
    @Override
    public List<Book> getBooks() {
        List<Book> books = bookMapper.getBooks();
        return books;
    }

    @Override
    // 搜索书籍
    public List<Book> searchBooks(String text){
        String filter = "%"+text+"%";
        List<Book> books = bookMapper.searchBooks(filter);
        return books;
    }

    // 添加书籍
    @Override
    public void addBook(Book book) {
        jdbcTemplate.update("insert into BOOKS(NAME, AUTHOR, ISBN, OUTLINE, STOCK, PRICE, url, press, year, pages) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                book.getName(), book.getAuthor(), book.getISBN(), book.getOutline(), book.getStock(), book.getPrice(), book.getUrl(), book.getPress(), book.getYear(), book.getPages());
    }

    // 查找书籍
    @Override
    public Book getBook(String ISBN){
        Book book = bookMapper.getBook(ISBN);
        return book;
    }

    // 删除书籍
    @Override
    public int deleteBook(String ISBN) {
        int result = bookMapper.deleteBook(ISBN);
        return result;
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
                "PRESS='"+book.getPress()+"', "+
                "YEAR='"+book.getYear()+"', "+
                "PAGES="+book.getPages()+", "+
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
