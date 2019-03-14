package cn.liuzhengwei.ebook;

import cn.liuzhengwei.ebook.service.Properties;
import cn.liuzhengwei.ebook.service.UserService;
import cn.liuzhengwei.ebook.web.HelloController;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
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

	@Test
	public void test() throws Exception {
		// 插入2个用户
		userSerivce.create("testing", "123", "测试者");
		userSerivce.create("lzw", "123", "柳寄书");

		// 查数据库，应该有2个用户
		Assert.assertEquals(2, userSerivce.getAllUsers().intValue());
	}
}