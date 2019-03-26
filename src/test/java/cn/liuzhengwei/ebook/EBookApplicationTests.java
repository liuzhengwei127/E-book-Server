package cn.liuzhengwei.ebook;

import cn.liuzhengwei.ebook.domain.UserState;
import cn.liuzhengwei.ebook.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EBookApplicationTests {
	@Autowired
	private UserService userService;

	@Test
	public void testGetBook() {
		List<UserState> userStates = userService.getUserStates();
		Assert.assertEquals(3, userStates.size());
	}
}