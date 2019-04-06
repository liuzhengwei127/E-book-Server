package cn.liuzhengwei.ebook.entity;

import java.util.List;

public class OrderList {

    private List<List<Order>> orders;

    public List<List<Order>> getOrders() {
        return orders;
    }

    public void setOrders(List<List<Order>> orders) {
        this.orders = orders;
    }
}
