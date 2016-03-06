package dessert.dao;

import java.util.List;

import dessert.entity.RechargeRecord;

/**
 * 会员充值
 * 
 * @author 严顺宽
 * @date 2016年3月2日 下午8:37:39
 */
public interface RechargeDao {
	public void recharge(RechargeRecord record);

	public List<RechargeRecord> getRechargeRecord(String memberId);

	public List<RechargeRecord> getRechargeRecord(String year, String month);

	public List<RechargeRecord> getRechargeRecord();
}
