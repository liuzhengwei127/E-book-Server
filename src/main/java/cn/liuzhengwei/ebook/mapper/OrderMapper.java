package cn.liuzhengwei.ebook.mapper;

import cn.liuzhengwei.ebook.entity.DateOrder;
import cn.liuzhengwei.ebook.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    List<Order> getAllOrders();
    List<Order> searchOrder(String filter);
    List<Order> getOrder(String account);
    int setStock(String ISBN, int stock);
    int maxID();
    int selectStock(String ISBN);
    void addOrder(Order order);
    List<DateOrder> dateFilter(String beginDate, String endDate, String account);
    List<DateOrder> dateDetailFilter(String beginDate, String endDate, String account);
}
