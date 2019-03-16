package cn.liuzhengwei.ebook;

import cn.liuzhengwei.ebook.domain.LoginState;
import cn.liuzhengwei.ebook.service.Properties;
import cn.liuzhengwei.ebook.service.UserService;
import cn.liuzhengwei.ebook.web.HelloController;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EBookApplicationTests {

	/*private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void getHello() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Hello Fucking World")));
	}

	private static final Log log = LogFactory.getLog(EBookApplicationTests.class);

	@Autowired
	private Properties properties;


	@Test
	public void test1() throws Exception {
		Assert.assertEquals("liuzhengwei", properties.getName());
		Assert.assertEquals("E-book-Server", properties.getTitle());
		Assert.assertEquals("liuzhengwei is trying to write E-book-Server", properties.getDesc());

		log.info("随机数测试输出：");
		log.info("随机字符串 : " + properties.getValue());
		log.info("随机int : " + properties.getNumber());
		log.info("随机long : " + properties.getBignumber());
		log.info("随机10以下 : " + properties.getTest1());
		log.info("随机10-20 : " + properties.getTest2());

	}*/

	@Autowired
	private UserService userSerivce;
	private LoginState loginState;

	@Test
	public void testCreate() throws Exception {
		// 准备，清空user表
		userSerivce.deleteAllUsers();

		// 插入2个用户
		userSerivce.create("testing", "123", "测试者", true, false);
		userSerivce.create("manager", "123", "管理员", true, true);
		userSerivce.create("banned", "123", "禁用者", false, false);

		Assert.assertEquals(3, userSerivce.getAllUsers().intValue());
	}

	@Test
	public void testGetUserState() throws Exception {
		//查找用户testing
		loginState = userSerivce.getUserState("testing","123");
		Assert.assertEquals(true, loginState.getLogin());
		Assert.assertEquals(0,loginState.getCode().intValue());
		Assert.assertEquals("测试者", loginState.getName());

		//查找用户manager
		loginState = userSerivce.getUserState("manager","123");
		Assert.assertEquals(true, loginState.getLogin());
		Assert.assertEquals(1,loginState.getCode().intValue());
		Assert.assertEquals("管理员", loginState.getName());

		//查找用户banned
		loginState = userSerivce.getUserState("banned","123");
		Assert.assertEquals(false, loginState.getLogin());
		Assert.assertEquals(2,loginState.getCode().intValue());

		//密码错误
		loginState = userSerivce.getUserState("banned","12");
		Assert.assertEquals(false, loginState.getLogin());
		Assert.assertEquals(1,loginState.getCode().intValue());

		//用户不存在
		loginState = userSerivce.getUserState("none","123");
		Assert.assertEquals(false, loginState.getLogin());
		Assert.assertEquals(0,loginState.getCode().intValue());
	}
}