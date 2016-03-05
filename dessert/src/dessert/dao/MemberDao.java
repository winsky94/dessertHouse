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

	public Member getMemberInfo(String name);

	public Member getMemberInfoById(String memberId);

	/**
	 * 设置过期的会员暂停其资格
	 */
	public void deactivate();

	/**
	 * 取消会员资格
	 * 
	 * @param memberId
	 */
	public void cancelMember(String memberId);

	public HashMap<String, Integer> getAgeData();

	public HashMap<String, Integer> getSexData();

	public HashMap<String, Integer> getAddressData();
}
