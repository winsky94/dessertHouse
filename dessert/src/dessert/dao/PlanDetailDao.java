package dessert.dao;

import java.util.List;

import dessert.entity.PlanDetail;

/**
 * @author 严顺宽
 */
public interface PlanDetailDao {
	public long add(PlanDetail planDetail);

	public void delete(PlanDetail planDetail);

	public void update(PlanDetail planDetail);

	public List<PlanDetail> getListByColumn(@SuppressWarnings("rawtypes") Class t, String column, Object value);

	public void deleteDetail(String dessertName, long planId, String weekDay);

}
