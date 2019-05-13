package cn.liuzhengwei.ebook.service;

import cn.liuzhengwei.ebook.entity.DateOrder;
import cn.liuzhengwei.ebook.entity.Order;
import cn.liuzhengwei.ebook.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private OrderMapper orderMapper;

    // 添加订单
    @Override
    public void addOrder(List<Order> orders) {
        try {
            int id;
            if (orders.size() > 0) {
                orderMapper.addOrder(orders.get(0));
                id = orderMapper.maxID();
                for (int i = 0; i < orders.size(); i++) {
                    int stock = orderMapper.selectStock(orders.get(i).getISBN());
                    stock = stock - orders.get(i).getCount();
                    if (stock >= 0) {
                        orders.get(i).setId(id);
                        orderMapper.addOrderItem(orders.get(i));
                        orderMapper.setStock(orders.get(i).getISBN(), stock);
                    }
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    // 获取所有订单
    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = orderMapper.getAllOrders();
        return orders;
    }

    // 获取指定用户订单
    @Override
    public List<Order> getOrder(String account) {
        List<Order> orders = orderMapper.getOrder(account);
        return orders;
    }

    // 搜索订单
    @Override
    public List<Order> searchOrder(String text) {
        String filter = "%"+text+"%";
        List<Order> orders= orderMapper.searchOrder(filter);
        return orders;
    }

    // 筛选指定日期内指定用户的订单
    @Override
    public List<Order> dateFilter(String beginDate, String endDate, String account) {
        List<Order> orders= orderMapper.dateFilter(beginDate,endDate,account);
        return orders;
    }

    // 筛选指定日期内指定用户的详细订单
    @Override
    public List<Order> dateDetailFilter(String beginDate, String endDate, String account) {
        List<Order> orders= orderMapper.dateDetailFilter(beginDate,endDate,account);
        return orders;
    }
}
