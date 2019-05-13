package cn.liuzhengwei.ebook.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

//Book实体定义
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {
    private String name;
    private String author;
    private String ISBN;
    private String newisbn;
    private String outline;
    private Integer stock;
    private Float price;
    private String cover;
    private String press;
    private String year;
    private Integer pages;
}
