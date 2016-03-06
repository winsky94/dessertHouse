package dessert.dao.impl;

import java.util.List;

import org.hibernate.Session;

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

	@Override
	public List<RechargeRecord> getRechargeRecord(String year, String month) {
		// TODO Auto-generated method stub
		String sql = "select * from rechargeRecord where Year(date)='" + year
				+ "' and Month(date)=" + month + ";";
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<RechargeRecord> result = session.createSQLQuery(sql)
				.addEntity(RechargeRecord.class).list();
		return result;
	}

}
