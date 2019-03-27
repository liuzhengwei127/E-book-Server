package cn.liuzhengwei.ebook.service;

import cn.liuzhengwei.ebook.domain.Order;

import java.util.List;

public interface OrderService {

    // 添加订单
    void addOrder(List<Order> order);

    // 删除订单
    void deleteOrder(Integer id);

    // 获取所有订单
    List<Order> getAllOrders();

    // 获取指定用户订单
    List<Order> getOrder(String account);
}
