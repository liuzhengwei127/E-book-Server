package cn.liuzhengwei.ebook.entity;

import lombok.Data;

import java.sql.Timestamp;

// Order实体定义
@Data
public class Order {
    private Integer id;
    private String account;
    private String userName;
    private String bookName;
    private String author;
    private String ISBN;
    private String cover;
    private Float price;
    private Integer count;
    private Timestamp date;
}
