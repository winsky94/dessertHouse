package dessert.dao.impl;

import java.util.ArrayList;

import dessert.dao.ShopDao;
import dessert.entity.Shop;

/**
 * @author 严顺宽
 */
public class ShopDaoImpl extends BaseDaoImpl<Shop> implements ShopDao {

	@Override
	public ArrayList<Shop> getShops() {
		// TODO Auto-generated method stub
		return (ArrayList<Shop>) getAll(Shop.class);
	}
}
