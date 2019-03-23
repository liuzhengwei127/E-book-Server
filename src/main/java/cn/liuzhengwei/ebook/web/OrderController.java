package cn.liuzhengwei.ebook.web;

import cn.liuzhengwei.ebook.domain.Order;
import cn.liuzhengwei.ebook.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(value="/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public List<List<Order>> getOrder(@RequestBody String account) {
        List<Order> orders_raw = orderService.getOrder(account);
        List<List<Order>> orders = new LinkedList<>();
        List<Order> list = new LinkedList<>();
        int id = orders_raw.get(0).getId();
        for (int i=0;i<orders_raw.size();i++){
            if (orders_raw.get(i).getId() != id){
                orders.add(list);
                list = new LinkedList<>();
                list.add(orders_raw.get(i));
            } else {
                list.add(orders_raw.get(i));
            }
        }

        return orders;
    }

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    @ResponseBody
    public List<List<Order>> getAllOrders() {
        List<Order> orders_raw = orderService.getAllOrders();
        List<List<Order>> orders = new LinkedList<>();
        List<Order> list = new LinkedList<>();
        int id = orders_raw.get(0).getId();
        for (int i=0;i<orders_raw.size();i++){
            if (orders_raw.get(i).getId() != id){
                orders.add(list);
                list = new LinkedList<>();
                list.add(orders_raw.get(i));
            } else {
                list.add(orders_raw.get(i));
            }
        }

        return orders;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public List<Order> addOrder(@RequestBody Order order) {
        orderService.addOrder(order);
        List<Order> result = orderService.getOrder(order.getAccount());

        return result;
    }
}
