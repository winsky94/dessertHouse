package dessert.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dessert.VO.ConsumeStats;
import dessert.VO.ConsumeVO;
import dessert.entity.Member;
import dessert.entity.RechargeRecord;

/**
 * @author 严顺宽
 */
public interface MemberService {
	public String signUp(Member member);

	public Member getMemberInfo(String name);

	public Member getMemberByMemberId(String memberId);

	public String update(Member member);

	public void recharge(RechargeRecord record);

	/**
	 * 扫描全部会员，判断是否其会员资格是否已失效
	 */
	public void deactivate();

	public String checkMemberStatus(String memberName);

	/**
	 * 取消会员资格
	 * 
	 * @param memberId
	 *            会员编号
	 */
	public void cancelMember(String memberId);

	/**
	 * 获得会员的消费记录
	 * 
	 * @param memberId
	 * @return
	 */
	public ArrayList<ConsumeVO> getConsumeRecord(String memberId);

	/**
	 * 得到全部会员的消费记录
	 * 
	 * @return
	 */
	public ArrayList<ConsumeVO> getConsumeRecord();

	/**
	 * 得到会员的充值记录
	 * 
	 * @param memberId
	 * @return
	 */
	public List<RechargeRecord> getRechargeRecord(String memberId);

	/**
	 * 得到全部会员的充值记录
	 * 
	 * @return
	 */
	public List<RechargeRecord> getRechargeRecord();

	/**
	 * 得到全部会员的充值记录
	 * 
	 * @return
	 */
	public List<RechargeRecord> getRechargeRecord(String year, String month);

	/**
	 * 积分兑换
	 * 
	 * @param memberId
	 * @param point
	 * @return
	 */
	public String exchange(String memberId, int point);

	/**
	 * 统计注册会员年龄分布：20以下，20-30,30-40,40-50,50-60,60以上
	 * 
	 * @return
	 */
	public HashMap<String, Integer> getAgeData();

	/**
	 * 统计注册会员男女比
	 * 
	 * @return
	 */
	public HashMap<String, Integer> getSexData();

	/**
	 * 统计注册会员地域分布，省份为单位
	 * 
	 * @return
	 */
	public HashMap<String, Integer> getAddressData();

	public ArrayList<ConsumeVO> getConsumeRecord(String year, String month);

	/**
	 * 总经理按店面统计销售、预订情况
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public ArrayList<ConsumeStats> consumeStats(String year, String month);

	/**
	 * 统计注册会员年龄分布：20以下，20-30,30-40,40-50,50-60,60以上
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public HashMap<String, Integer> getAgeData(String year, String month);

	/**
	 * 统计注册会员男女比
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public HashMap<String, Integer> getSexData(String year, String month);

	/**
	 * 统计注册会员地域分布，省份为单位
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public HashMap<String, Integer> getAddressData(String year, String month);

	/**
	 * 统计当月会员注册、有效、暂停、取消等人数
	 * 
	 * @return
	 */
	public HashMap<String, Integer> getTotalInfo();

	/**
	 * 统计当月会员注册、有效、暂停、取消等人数
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public HashMap<String, Integer> getTotalInfo(String year, String month);
}
