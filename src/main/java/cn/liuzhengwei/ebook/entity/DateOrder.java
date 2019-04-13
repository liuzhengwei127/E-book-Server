package cn.liuzhengwei.ebook.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class DateOrder {
    private Timestamp date;
    private Integer count;
    private Float amount;
}
