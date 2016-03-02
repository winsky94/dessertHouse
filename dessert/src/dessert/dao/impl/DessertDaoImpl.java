package dessert.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dessert.configure.Configure;
import dessert.dao.DessertDao;
import dessert.entity.Dessert;
import dessert.util.DateUtil;
import dessert.util.Week;

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
		// Session session = sessionFactory.getCurrentSession();
		// Criteria criteria = session.createCriteria(Dessert.class);
		// criteria.add(Restrictions.eq("name", name));
		// @SuppressWarnings("unchecked")
		// List<Dessert> desserts = criteria.list();
		// if (desserts == null || desserts.size() == 0) {
		// return null;
		// } else {
		// return desserts.get(0);
		// }
		return getByColumn(Dessert.class, "name", name);
	}

	public Dessert getDessertById(long id) {
		return getByColumn(Dessert.class, "id", id);
	}

	@Override
	public ArrayList<Dessert> getAllDesserts() {
		// TODO Auto-generated method stub
		ArrayList<Dessert> result = new ArrayList<Dessert>();

		List<Date> curDates = DateUtil.getCurrentWeek(new Date());
		Date sundayDate = curDates.get(0);
		Date lastDate = curDates.get(curDates.size() - 1);
		String validSunday = DateUtil.getFormattedDate(sundayDate);
		String lastDay = DateUtil.getFormattedDate(lastDate);
		String today = DateUtil.getToday();

		String hql = "select d.name, d.path, d.price, d.stockNum, d.id, d.owingTo, d.weekDay, d.date";
		hql += " from plan p, planDetail pd, dessert d";
		hql += " where p.checked=" + Configure.PLAN_PASS;
		hql += " and p.validSunday='" + validSunday + "'";
		hql += " and d.date<='" + lastDay + "'";
		hql += " and d.date>='" + today + "'";

		hql += " and p.id=pd.planId and pd.dessertName=d.name";
		List<?> lists = doHqlQuery(hql);
		if (lists != null) {
			for (int i = 0; i < lists.size(); i++) {
				Object[] obj = (Object[]) lists.get(i);

				String name = (String) obj[0];
				String path = (String) obj[1];
				double price = (double) obj[2];
				int stockNum = (int) obj[3];
				long id = (long) obj[4];
				String shopName = (String) obj[5];
				String weekDay = Week.toString((Week) obj[6]);
				String date = (String) obj[7];

				Dessert dessert = new Dessert();
				dessert.setId(id);
				dessert.setName(name);
				dessert.setOwingTo(shopName);
				dessert.setPath(path);
				dessert.setPrice(price);
				dessert.setStockNum(stockNum);
				dessert.setWeekDay(Week.getWeek(weekDay));
				dessert.setDate(date);
				result.add(dessert);
			}
		}

		return result;
	}
}
