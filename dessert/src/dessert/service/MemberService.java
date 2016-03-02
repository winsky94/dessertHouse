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
}
