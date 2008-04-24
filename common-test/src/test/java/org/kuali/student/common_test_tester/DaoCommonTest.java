package org.kuali.student.common_test_tester;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.kuali.student.common_test_tester.support.MyDao;
import org.kuali.student.common_test_tester.support.Value;
import org.kuali.student.poc.common.test.spring.AbstractTransactionalDaoTest;
import org.kuali.student.poc.common.test.spring.Dao;
import org.kuali.student.poc.common.test.spring.PersistenceFileLocation;

@PersistenceFileLocation("classpath:META-INF/test-persistence.xml")
public class DaoCommonTest extends AbstractTransactionalDaoTest {

	@Dao(value = "org.kuali.student.common_test_tester.support.MyDaoImpl", testDataFile = "classpath:META-INF/load-my-beans.xml")
	public MyDao myDao;

	public Value value1;
	
	@Test
	public void test1() {
		Value value = new Value("Cheerios");

		Long id = myDao.createValue(value);
		String result = myDao.findValue(id);
		assertEquals("Cheerios", result);

		result = myDao.findValue(new Long(123));
		
		assertNotNull(myDao.findValue("Value Number One"));
		assertNotNull(value1);
	}
}
