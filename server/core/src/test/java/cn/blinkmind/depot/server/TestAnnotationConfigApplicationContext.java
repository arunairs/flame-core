package cn.blinkmind.depot.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * @author jiaan.zhang@outlook.com
 * @date 9/19/16 5:27 PM
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
public class TestAnnotationConfigApplicationContext extends AbstractTestNGSpringContextTests
{
	@Autowired
	ApplicationContext applicationContext;

	@Test(groups = "s1")
	public void test()
	{
		//AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);
		//applicationContext.publishEvent(new MyEvent("fff"));
		//applicationContext.close();
	}

}
