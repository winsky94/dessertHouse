package dessert.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.DessertVO;
import dessert.VO.PlanVO;
import dessert.dao.PlanDao;
import dessert.entity.Dessert;
import dessert.entity.Plan;
import dessert.service.PlanService;
import dessert.util.ConvertVO;

/**
 * @author 严顺宽
 */
public class PlanServiceImpl implements PlanService {
	@Autowired
	public PlanDao planDao;

	@Override
	public long add(Plan plan) {
		// TODO Auto-generated method stub
		return planDao.add(plan);
	}

	@Override
	public void delete(Plan plan) {
		// TODO Auto-generated method stub
		planDao.delete(plan);
	}

	@Override
	public void update(Plan plan) {
		// TODO Auto-generated method stub
		planDao.update(plan);
	}

	@Override
	public PlanVO getPlans(String shopName, boolean current) {
		// TODO Auto-generated method stub
		PlanVO plan = planDao.getPlans(shopName, current);
		return plan;
	}

	@Override
	public void submitPlans(PlanVO plan) {
		// TODO Auto-generated method stub
		planDao.submitPlans(plan);
	}

	@Override
	public int approve(long planId, int result) {
		// TODO Auto-generated method stub
		return planDao.approve(planId, result);
	}

	@Override
	public ArrayList<DessertVO> getDayDessert(String date, String weekDay,
			String shopName) {
		// TODO Auto-generated method stub
		ArrayList<DessertVO> result = new ArrayList<DessertVO>();
		ArrayList<Dessert> desserts = new ArrayList<Dessert>();
		desserts = planDao.getDayDessert(date, weekDay, shopName);
		if (desserts != null) {
			for (Dessert dessert : desserts) {
				result.add(ConvertVO.dessertToVO(dessert));
			}
		}

		return result;
	}

}
