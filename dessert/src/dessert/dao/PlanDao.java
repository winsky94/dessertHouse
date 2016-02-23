package dessert.dao;

import dessert.entity.Plan;

/**
 * @author 严顺宽
 */
public interface PlanDao {
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
	public Plan getPlans(String shopName);

	public void submitPlans(Plan plan);

}
