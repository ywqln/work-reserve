package com.qln.workreserve;

import com.qln.workreserve.controller.AddHJPTableController;
import com.qln.workreserve.tree.controller.DicController;
import com.qln.workreserve.tree.controller.DictionaryController;
import com.qln.workreserve.tree.controller.YwqController;
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
	@Autowired
	private DictionaryController dictionaryController;
	@Autowired
	private YwqController ywqController;
	@Autowired
	private DicController dicController;

	@Test
	public void contextLoads() {
		dicController.workFlow();
	}
}
