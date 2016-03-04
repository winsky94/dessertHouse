package dessert.dao.impl;

import java.util.List;

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

	@Override
	public List<RechargeRecord> getRechargeRecord(String memberId) {
		// TODO Auto-generated method stub
		return getListByColumn(RechargeRecord.class, "memberId", memberId);
	}

	@Override
	public List<RechargeRecord> getRechargeRecord() {
		// TODO Auto-generated method stub
		return getAll(RechargeRecord.class);
	}

}
