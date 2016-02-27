package dessert.service;

import dessert.VO.PlanVO;
import dessert.entity.Plan;

/**
 * @author 严顺宽
 */
public interface PlanService {
	public long add(Plan plan);

	public void delete(Plan plan);

	public void update(Plan plan);

	/**
	 * 得到该店最近一周的销售计划
	 * 
	 * @param shopName
	 *            店名
	 * @return
	 */
	public PlanVO getPlans(String shopName, boolean current);

	public void submitPlans(PlanVO plan);

	/**
	 * 总经理审批计划
	 * 
	 * @param planId
	 *            计划id
	 * @param result
	 *            审批结果，configure.PLAN_PASS和configure.PLAN_FAIL
	 * @return 受影响的行数
	 */
	public int approve(long planId, int result);

}
