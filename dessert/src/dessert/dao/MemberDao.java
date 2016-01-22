package dessert.dao;

import dessert.entity.Member;

/**
 * @author 严顺宽
 */
public interface MemberDao {
	public Member findByName(String name);

	public String signUp(Member member);

	public String getMaxMemberId();

	public void add(Member member);
}
