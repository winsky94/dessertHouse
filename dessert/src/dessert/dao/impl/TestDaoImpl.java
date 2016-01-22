package dessert.dao.impl;

import dessert.dao.TestDao;
import dessert.entity.*;

public class TestDaoImpl extends BaseDaoImpl<Test> implements TestDao {

	@Override
	public String test() {
		// TODO Auto-generated method stub
		return "Hello!";
	}

}
