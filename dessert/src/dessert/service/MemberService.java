package dessert.service;

import java.util.ArrayList;

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
}
