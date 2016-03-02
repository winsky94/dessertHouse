package dessert.dao.impl;

import dessert.dao.RechargeDao;
import dessert.entity.RechargeRecord;

/**
 * @author 严顺宽
 * @date 2016年3月2日 下午8:38:38
 */
public class RechargeDaoImpl extends BaseDaoImpl<RechargeRecord> implements
		RechargeDao {

	@Override
	public void recharge(RechargeRecord record) {
		// TODO Auto-generated method stub
		add(record);
	}

}
