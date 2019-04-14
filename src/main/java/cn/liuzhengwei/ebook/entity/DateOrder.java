package cn.liuzhengwei.ebook.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DateOrder {
    private int id;
    private String account;
    @JsonFormat(pattern = "yyyy-MM-dd") private Timestamp date;
    private Integer count;
    private String bookName;
    private Float amount;
}
