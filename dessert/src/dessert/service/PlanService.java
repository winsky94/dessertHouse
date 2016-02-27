package dessert.service;

import java.util.ArrayList;

import dessert.VO.DessertVO;
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

	/**
	 * 得到某店某天的可销售商品
	 * 
	 * @param date
	 *            日期 xxxx-xx-xx
	 * @param weekDay
	 *            星期几，英文
	 * @param shopName
	 *            店名
	 * @return
	 */
	public ArrayList<DessertVO> getDayDessert(String date, String weekDay,
			String shopName);

}
