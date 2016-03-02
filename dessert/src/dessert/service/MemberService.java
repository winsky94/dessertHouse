package dessert.service;

import dessert.entity.Member;
import dessert.entity.RechargeRecord;

/**
 * @author 严顺宽
 */
public interface MemberService {
	public String signUp(Member member);

	public Member getMemberInfo(String name);

	public String update(Member member);

	public void recharge(RechargeRecord record);

	/**
	 * 扫描全部会员，判断是否其会员资格是否已失效
	 */
	public void deactivate();

	public String checkMemberStatus(String memberName);
}
