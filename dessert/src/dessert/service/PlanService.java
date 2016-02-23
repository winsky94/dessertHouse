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
	public PlanVO getPlans(String shopName);

	public void submitPlans(Plan plan);

}
