package dessert.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.PlanVO;
import dessert.dao.PlanDao;
import dessert.dao.PlanDetailDao;
import dessert.entity.Plan;
import dessert.entity.PlanDetail;
import dessert.util.Week;

/**
 * @author 严顺宽
 */
public class PlanDaoImpl extends BaseDaoImpl<Plan> implements PlanDao {
	@Autowired
	private PlanDetailDao planDetailDao;

	@Override
	public PlanVO getPlans(String shopName) {
		// TODO Auto-generated method stub
		// Session session = sessionFactory.getCurrentSession();
		// Criteria criteria = session.createCriteria(Plan.class);
		// criteria.add(Restrictions.eq("shop", shopName));
		// @SuppressWarnings("unchecked")
		// List<Plan> plan = criteria.list();
		// if (plan == null || plan.size() == 0) {
		// return null;
		// } else {
		// return plan.get(0);
		// }

		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void submitPlans(PlanVO vo) {
		// TODO Auto-generated method stub
		// 不知道有没有问题，主键-1会不会有影响

		// 保存plan
		Plan plan = new Plan();
		plan.setChecked(vo.getChecked());
		plan.setCreateAt(vo.getCreateAt());
		plan.setShop(vo.getShop());
		plan.setId(vo.getId());
		long planId = add(plan);

		// 保存plan的具体内容
		HashMap<Week, ArrayList<String>> planDetails = vo.getPlans();
		if (planDetails != null) {
			Iterator iter = planDetails.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				Week key = (Week) entry.getKey();
				String weekDay = Week.toString(key);

				ArrayList<String> val = (ArrayList<String>) entry.getValue();
				if (val != null) {
					for (String dessertName : val) {
						PlanDetail planDetail = new PlanDetail();
						planDetail.setPlanId(planId);
						planDetail.setWeekDay(weekDay);
						planDetail.setDessertName(dessertName);
						planDetailDao.add(planDetail);
					}
				}
			}
		}

		System.out.println("数据库保存");
	}

}
