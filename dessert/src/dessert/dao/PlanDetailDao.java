package dessert.dao;

import dessert.entity.PlanDetail;

/**
 * @author 严顺宽
 */
public interface PlanDetailDao {
	public long add(PlanDetail planDetail);

	public void delete(PlanDetail planDetail);

	public void update(PlanDetail planDetail);

}
