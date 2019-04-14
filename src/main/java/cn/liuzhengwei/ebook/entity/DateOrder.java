package cn.liuzhengwei.ebook.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class DateOrder {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp date;
    private Integer count;
    private Float amount;
}
