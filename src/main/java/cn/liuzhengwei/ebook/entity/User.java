package cn.liuzhengwei.ebook.entity;

import lombok.Data;

//User实体定义
@Data
public class User {
    private String account;
    private String password;
    private String name;
    private Boolean allowed;
    private Boolean ismanager;
}