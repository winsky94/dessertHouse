package dessert.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.dao.TestDao;
import dessert.service.TestService;

public class TestServiceImpl implements TestService {
	@Autowired
	public TestDao testDao;

	@Override
	public ArrayList<String> getTrainingApproach() {
		// TODO Auto-generated method stub
		// return testDao.getTrainingApproach();
		return null;
	}

}
