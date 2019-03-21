package cn.liuzhengwei.ebook;

import cn.liuzhengwei.ebook.domain.Order;
import cn.liuzhengwei.ebook.service.OrderService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EBookApplicationTests {
	@Autowired
	private OrderService orderService;

	@Ignore
	@Test
	public void testAddOrder() {
		Order order = new Order();
		List<Order> gets;
		Timestamp date = new Timestamp(new Date().getTime());
		order.setAccount("testing");
		order.setCount(1);
		order.setISBN("123");
		order.setDate(date);
		orderService.addOrder(order);
		gets = orderService.getOrder("testing");
		Assert.assertEquals("123",gets.get(0).getISBN());
	}

	@Test
	public void testGetAllOrders() {
		List<Order> orders;
		orders = orderService.getAllOrders();
		Assert.assertEquals("testing",orders.get(0).getAccount());
	}

	@Test
	public void testGetOrder() {
		String account = "testing";
		List<Order> orders;
		orders = orderService.getOrder(account);
		Assert.assertEquals("123", orders.get(0).getISBN());
	}
}