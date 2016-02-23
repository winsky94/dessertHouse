package dessert.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.PlanVO;
import dessert.dao.PlanDao;
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
	public PlanVO getPlans(String shopName) {
		// TODO Auto-generated method stub
		Plan plan = planDao.getPlans(shopName);
		if (plan != null) {
			return ConvertVO.planToVO(plan);
		} else {
			return null;
		}
	}

	@Override
	public void submitPlans(Plan plan) {
		// TODO Auto-generated method stub
		planDao.submitPlans(plan);
	}

}
