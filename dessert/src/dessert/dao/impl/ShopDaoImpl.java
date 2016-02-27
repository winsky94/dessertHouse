package dessert.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dessert.configure.Configure;
import dessert.dao.ShopDao;
import dessert.entity.Shop;

/**
 * @author 严顺宽
 */
public class ShopDaoImpl extends BaseDaoImpl<Shop> implements ShopDao {

	public long add(Shop shop) {
		// 查重
		String name = shop.getName();
		boolean exist = checkShop(name);
		if (exist) {
			return Configure.SHOP_EXIST;
		} else {
			return super.add(shop);
		}
	}

	@Override
	public ArrayList<Shop> getShops() {
		// TODO Auto-generated method stub
		return (ArrayList<Shop>) getAll(Shop.class);
	}

	@Override
	public boolean checkShop(String shopName) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Shop.class);
		criteria.add(Restrictions.eq("name", shopName));
		@SuppressWarnings("unchecked")
		List<Shop> shops = criteria.list();
		if (shops == null || shops.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Shop getShop(String shopName) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Shop.class);
		criteria.add(Restrictions.eq("name", shopName));
		@SuppressWarnings("unchecked")
		List<Shop> shops = criteria.list();
		if (shops == null || shops.size() == 0) {
			return null;
		} else {
			return shops.get(0);
		}
	}
}
