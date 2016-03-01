package dessert.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dessert.dao.DessertDao;
import dessert.entity.Dessert;

/**
 * @author 严顺宽
 */
public class DessertDaoImpl extends BaseDaoImpl<Dessert> implements DessertDao {

	@Override
	public boolean checkExist(String name, String date, String shopName) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Dessert.class);
		criteria.add(Restrictions.eq("name", name));
		criteria.add(Restrictions.eq("date", date));
		criteria.add(Restrictions.eq("owingTo", shopName));
		@SuppressWarnings("unchecked")
		List<Dessert> desserts = criteria.list();
		if (desserts == null || desserts.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Dessert getDessertByName(String name) {
		// TODO Auto-generated method stub
//		Session session = sessionFactory.getCurrentSession();
//		Criteria criteria = session.createCriteria(Dessert.class);
//		criteria.add(Restrictions.eq("name", name));
//		@SuppressWarnings("unchecked")
//		List<Dessert> desserts = criteria.list();
//		if (desserts == null || desserts.size() == 0) {
//			return null;
//		} else {
//			return desserts.get(0);
//		}
		return getByColumn(Dessert.class, "name", name);
	}

	public Dessert getDessertById(long id){
		return getByColumn(Dessert.class, "id", id);
	}
}
