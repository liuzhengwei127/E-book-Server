package cn.liuzhengwei.ebook.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

//User实体定义
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private String account;
    private String password;
    private String name;
    private String code;
    private Boolean allowed;
    private Boolean isManager;
    private String mail;
}