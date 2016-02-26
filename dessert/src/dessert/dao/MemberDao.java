package dessert.dao;

import java.util.HashMap;

import dessert.entity.Member;
import dessert.util.UserType;

/**
 * @author 严顺宽
 */
public interface MemberDao {
	public Member findByName(String name);

	public String signUp(Member member);

	public String getMaxMemberId();

	public long add(Member member);

	public void update(Member member);

	public HashMap<UserType, String> checkLogin(String userName, String password);
}
