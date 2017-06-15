package kr.ac.jejunu;

import kr.ac.jejunu.dao.IndexDao;
import kr.ac.jejunu.model.Enroll;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	IndexDao indexDao;

	@Test
	public void contextLoads() {
		Enroll test = indexDao.findOne(1);
		System.out.print(test.getContent());
		System.out.print(test.getTitle());
	}

}
