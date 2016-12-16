package cn.blinkmind.duck.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

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
