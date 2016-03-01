package dessert.service;

import dessert.entity.Member;

/**
 * @author 严顺宽
 */
public interface MemberService {
	public String signUp(Member member);
	
	public Member getMemberInfo(String name);
}
