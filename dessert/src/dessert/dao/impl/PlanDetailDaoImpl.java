package dessert.dao.impl;

import dessert.dao.PlanDetailDao;
import dessert.entity.PlanDetail;

/**
 * @author 严顺宽
 */
public class PlanDetailDaoImpl extends BaseDaoImpl<PlanDetail> implements
		PlanDetailDao {

	@Override
	public void deleteDetail(String dessertName, long planId, String weekDay) {
		// TODO Auto-generated method stub
		String sql = "delete from planDetail";
		sql += " where dessertName='" + dessertName + "'";
		sql += " and planId=" + planId + " and weekDay='" + weekDay + "'";
		doSql(sql);
	}

}
