package cn.liuzhengwei.ebook.service;

import cn.liuzhengwei.ebook.entity.Order;

import java.util.List;

public interface OrderService {

    // 添加订单
    void addOrder(List<Order> order);

    // 获取所有订单
    List<Order> getAllOrders();

    // 获取指定用户订单
    List<Order> getOrder(String account);

    // 搜索订单
    List<Order> searchOrder(String filter);
}
