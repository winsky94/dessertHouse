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
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Shop.class);
		criteria.add(Restrictions.eq("name", name));
		@SuppressWarnings("unchecked")
		List<Shop> shops = criteria.list();
		if (shops == null || shops.size() == 0) {
			return super.add(shop);
		} else {
			return Configure.SHOP_EXIST;
		}
	}

	@Override
	public ArrayList<Shop> getShops() {
		// TODO Auto-generated method stub
		return (ArrayList<Shop>) getAll(Shop.class);
	}
}
