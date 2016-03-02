package dessert.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.DessertVO;
import dessert.dao.DessertDao;
import dessert.entity.Dessert;
import dessert.service.DessertService;
import dessert.util.ConvertVO;

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
	public boolean checkExist(String name, String date, String shopName) {
		// TODO Auto-generated method stub
		return dessertDao.checkExist(name, date, shopName);
	}

	@Override
	public ArrayList<DessertVO> getDessertByName(ArrayList<String> names) {
		// TODO Auto-generated method stub
		ArrayList<DessertVO> result = new ArrayList<DessertVO>();
		for (String name : names) {
			Dessert dessert = dessertDao.getDessertByName(name);
			if (dessert != null) {
				result.add(ConvertVO.dessertToVO(dessert));
			} else {
				System.err.println("有个商品的名字不存在~，名字为：" + name);
			}

		}
		return result;
	}

	@Override
	public long getDessertIdByName(String name) {
		// TODO Auto-generated method stub
		Dessert dessert = dessertDao.getDessertByName(name);
		if (dessert != null) {
			long id = dessert.getId();
			return id;
		} else {
			return -1;
		}
	}

	@Override
	public DessertVO getDessertById(long id) {
		// TODO Auto-generated method stub
		Dessert dessert = dessertDao.getDessertById(id);
		if (dessert != null) {
			return ConvertVO.dessertToVO(dessert);
		}
		return null;
	}

	@Override
	public ArrayList<DessertVO> getAllDesserts() {
		// TODO Auto-generated method stub
		ArrayList<DessertVO> result = new ArrayList<DessertVO>();
		ArrayList<Dessert> desserts = new ArrayList<Dessert>();
		desserts = dessertDao.getAllDesserts();
		if (desserts != null) {
			for (Dessert dessert : desserts) {
				DessertVO vo = ConvertVO.dessertToVO(dessert);
				result.add(vo);
			}
		}
		return result;
	}

}
