package cn.liuzhengwei.ebook.entity;

import lombok.Data;

import java.util.List;

@Data
public class Orders {

    private List<Order> orderItems;
}
