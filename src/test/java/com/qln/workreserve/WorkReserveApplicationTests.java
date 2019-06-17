package com.qln.workreserve;

import com.qln.workreserve.controller.AddHJPTableController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkReserveApplicationTests {

	@Autowired
	private AddHJPTableController addHJPTableController;

	@Test
	public void contextLoads() {
		addHJPTableController.workFlow();
	}
}