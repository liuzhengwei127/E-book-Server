package cn.liuzhengwei.ebook.service;

import cn.liuzhengwei.ebook.domain.Order;

import java.util.List;

public interface OrderService {

    // 添加订单
    Order addOrder();

    // 删除订单
    Order deleteOrder();

    // 获取所有订单
    List<Order> getAllOrders();

    // 获取指定用户订单
    Order getOrder(String account);
}
