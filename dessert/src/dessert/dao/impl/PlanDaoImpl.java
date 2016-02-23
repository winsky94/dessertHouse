package dessert.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dessert.dao.PlanDao;
import dessert.entity.Plan;

/**
 * @author 严顺宽
 */
public class PlanDaoImpl extends BaseDaoImpl<Plan> implements PlanDao {

	@Override
	public Plan getPlans(String shopName) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Plan.class);
		criteria.add(Restrictions.eq("shop", shopName));
		@SuppressWarnings("unchecked")
		List<Plan> plan = criteria.list();
		if (plan == null || plan.size() == 0) {
			return null;
		} else {
			return plan.get(0);
		}
	}

	@Override
	public void submitPlans(Plan plan) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		// 不知道有没有问题，主键-1会不会有影响
		session.saveOrUpdate(plan);
	}

}
