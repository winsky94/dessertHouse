package dessert.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.PlanVO;
import dessert.dao.PlanDao;
import dessert.dao.PlanDetailDao;
import dessert.entity.Plan;
import dessert.entity.PlanDetail;
import dessert.util.DateUtil;
import dessert.util.Week;

/**
 * @author 严顺宽
 */
public class PlanDaoImpl extends BaseDaoImpl<Plan> implements PlanDao {
	@Autowired
	private PlanDetailDao planDetailDao;

	@Override
	public PlanVO getPlans(String shopName, boolean current) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Plan.class);
		criteria.add(Restrictions.eq("shop", shopName));
		criteria.addOrder(Order.desc("createAt"));
		@SuppressWarnings("unchecked")
		List<Plan> plans = criteria.list();
		if (plans == null || plans.size() == 0) {
			return null;
		} else {
			Plan plan = plans.get(0);
			if (current) {
				// 查看当前周
				String validSunday = plan.getValidSunday();
				String today = DateUtil.getToday();
				boolean inSameWeek = DateUtil.isInSameWeek(validSunday, today);
				if (!inSameWeek) {
					// 最新的一个不是当前周的，查看次新的，这肯定是当前周的了
					if (plans.size() > 1) {
						plan = plans.get(1);
					} else {
						return null;
					}
				}
				// plan里面放的是当前周的内容
			} else {
				// 查看下周
				List<Date> list = DateUtil.getNextWeek(new Date());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String nextSunday = sdf.format(list.get(0));
				String validSunday = plan.getValidSunday();
				boolean inSameWeek = DateUtil.isInSameWeek(validSunday,
						nextSunday);
				if (!inSameWeek) {
					// 最新的计划不是下周的计划，证明下周还没有制定计划
					return null;
				}
			}

			long id = plan.getId();
			HashMap<Week, ArrayList<String>> planDetails = new HashMap<Week, ArrayList<String>>();
			// 根据id找到这个id的plan的详细内容
			List<PlanDetail> result = planDetailDao.getListByColumn(
					PlanDetail.class, "planId", id);
			if (result != null) {
				for (PlanDetail detail : result) {
					String dessertName = detail.getDessertName();
					String weekDay = detail.getWeekDay();
					Week week = Week.getWeek(weekDay);
					ArrayList<String> temp = planDetails.get(week);
					if (temp == null) {
						temp = new ArrayList<String>();
					}
					temp.add(dessertName);
					planDetails.put(week, temp);
				}
			}

			String shop = plan.getShop();
			String createAt = plan.getCreateAt();
			boolean checked = plan.getChecked();
			String validSunday = plan.getValidSunday();
			PlanVO vo = new PlanVO(id, shop, createAt, planDetails, checked,
					validSunday);
			return vo;
		}
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
		Date currentDate = new Date();
		List<Date> list = DateUtil.getNextWeek(currentDate);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sunday = sdf.format(list.get(0));
		plan.setValidSunday(sunday);
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
