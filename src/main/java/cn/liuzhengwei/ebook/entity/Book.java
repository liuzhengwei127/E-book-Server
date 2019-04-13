package cn.liuzhengwei.ebook.entity;

import lombok.Data;

//Book实体定义
@Data
public class Book {
    private String name;
    private String author;
    private String ISBN;
    private String newisbn;
    private String outline;
    private Integer stock;
    private Float price;
    private String url;
    private String press;
    private String year;
    private Integer pages;
}
