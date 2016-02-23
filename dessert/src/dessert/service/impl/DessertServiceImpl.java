package dessert.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.dao.DessertDao;
import dessert.entity.Dessert;
import dessert.service.DessertService;

/**
 * @author 严顺宽
 */
public class DessertServiceImpl implements DessertService {
	@Autowired
	public DessertDao dessertDao;

	@Override
	public long add(Dessert dessert) {
		// TODO Auto-generated method stub
		return dessertDao.add(dessert);
	}

	@Override
	public void delete(Dessert dessert) {
		// TODO Auto-generated method stub
		dessertDao.delete(dessert);
	}

	@Override
	public void update(Dessert dessert) {
		// TODO Auto-generated method stub
		dessertDao.update(dessert);
	}

	@Override
	public boolean checkExist(String name) {
		// TODO Auto-generated method stub
		return dessertDao.checkExist(name);
	}

	@Override
	public ArrayList<Dessert> getDessertByName(ArrayList<String> names) {
		// TODO Auto-generated method stub
		ArrayList<Dessert> result = new ArrayList<Dessert>();
		for (String name : names) {
			Dessert dessert = dessertDao.getDessertByName(name);
			if (dessert != null) {
				result.add(dessert);
			} else {
				System.err.println("有个商品的名字不存在~");
			}

		}
		return result;
	}

}
