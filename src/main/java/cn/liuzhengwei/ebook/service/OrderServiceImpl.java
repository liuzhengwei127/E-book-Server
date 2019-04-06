package cn.liuzhengwei.ebook.service;

import cn.liuzhengwei.ebook.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 添加订单
    @Override
    public void addOrder(List<Order> orders) {
        int id;
        try {
            id = jdbcTemplate.queryForObject("select max(ID) from ORDERS", Integer.class) + 1;
        } catch(Exception e){
            id = 0;
        }

        for (int i=0;i<orders.size();i++) {
            int stock = jdbcTemplate.queryForObject("SELECT stock FROM books WHERE isbn='"+orders.get(i).getISBN()+"'", Integer.class);
            stock = stock - orders.get(i).getCount();
            if (stock >= 0) {
                jdbcTemplate.update("UPDATE books SET stock="+stock+" WHERE isbn='"+orders.get(i).getISBN()+"'");
                jdbcTemplate.update("insert into ORDERS(ID, ACCOUNT, ISBN, COUNT, DATE) values(?, ?, ?, ?, ?)",
                        id, orders.get(i).getAccount(), orders.get(i).getISBN(), orders.get(i).getCount(), orders.get(i).getDate());
            }
        }
    }

    // 删除订单
    @Override
    public void deleteOrder(Integer id) {
        jdbcTemplate.update("delete from ORDERS where ID="+id);
    }

    // 获取所有订单
    @Override
    public List<Order> getAllOrders() {
        RowMapper<Order> rowMapper = new BeanPropertyRowMapper<>(Order.class);
        List<Order> orders = jdbcTemplate.query("SELECT * " +
                "FROM orders NATURAL JOIN " +
                        "(SELECT name AS bookname,author,ISBN,price,url " +
                            "FROM books) AS bookss " +
                             "NATURAL JOIN " +
                        "(SELECT name AS username,account " +
                          "FROM users) AS users " +
                "ORDER BY id", rowMapper);
        return orders;
    }

    // 获取指定用户订单
    @Override
    public List<Order> getOrder(String account) {
        RowMapper<Order> rowMapper = new BeanPropertyRowMapper<>(Order.class);
        List<Order> orders = jdbcTemplate.query("SELECT * " +
                                                        "FROM orders NATURAL JOIN " +
                                                            "(SELECT name AS bookname,author,ISBN,price,url " +
                                                                "FROM books) AS bookss " +
                                                                    "NATURAL JOIN " +
                                                            "(SELECT name AS username,account " +
                                                                "FROM users) AS users " +
                                                                    "WHERE account = '"+account+"' " +
                                                        "ORDER BY id", rowMapper);
        return orders;
    }
}
