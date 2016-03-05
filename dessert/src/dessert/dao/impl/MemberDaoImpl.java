package dessert.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dessert.configure.Configure;
import dessert.dao.MemberDao;
import dessert.entity.Member;
import dessert.util.CheckError;
import dessert.util.MemberStatus;
import dessert.util.TimeUtil;
import dessert.util.UserType;

/**
 * @author 严顺宽
 */
public class MemberDaoImpl extends BaseDaoImpl<Member> implements MemberDao {

	@Override
	public String signUp(Member member) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Member.class);
		String name = member.getName();
		criteria.add(Restrictions.eq("name", name));
		@SuppressWarnings("unchecked")
		List<Member> members = criteria.list();
		if (members == null || members.size() == 0) {
			updateLastLoadTime(member);
			add(member);
			return Configure.SUCCESS;
		} else {
			return Configure.MEMBER_EXIST;
		}
	}

	@Override
	public Member findByName(String name) {
		// TODO Auto-generated method stub
		return getByColumn(Member.class, "name", name);
	}

	@Override
	public String getMaxMemberId() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<String> list = session.createQuery(
				"select max(m.memberId) from member m").list();
		return list.get(0);
	}

	@Override
	public HashMap<UserType, String> checkLogin(String userName, String password) {
		// TODO Auto-generated method stub
		HashMap<UserType, String> result = new HashMap<UserType, String>();

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Member.class);
		criteria.add(Restrictions.eq("name", userName));
		criteria.add(Restrictions.eq("password", password));
		criteria.add(Restrictions.or(
				Restrictions.eq("status", MemberStatus.Init),
				Restrictions.eq("status", MemberStatus.OK),
				Restrictions.eq("status", MemberStatus.pause)));
		@SuppressWarnings("unchecked")
		List<Member> members = criteria.list();
		if (members == null || members.size() == 0) {
			result.put(UserType.error, "");
		} else {
			// 检查可能的错误
			CheckError.checkListSize(members);
			Member member = members.get(0);
			String lastLoadTime = member.getLastLoadTime();
			updateLastLoadTime(member);
			update(member);

			result.put(UserType.member, lastLoadTime);
		}
		return result;
	}

	private void updateLastLoadTime(Member member) {
		// 更新最近登录时间
		String lastLoadTime = TimeUtil.getCurrentTime();
		member.setLastLoadTime(lastLoadTime);
	}

	@Override
	public Member getMemberInfo(String name) {
		// TODO Auto-generated method stub
		return getByColumn(Member.class, "name", name);
	}

	@Override
	public Member getMemberInfoById(String memberId) {
		// TODO Auto-generated method stub
		return getByColumn(Member.class, "memberId", memberId);
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		// 检查会员失效
		String sql = "update member";
		sql += " set status=" + MemberStatus.pause;
		sql += " , overDate=date_add(curdate(), interval 1 year )";// 停止日期是失效日期的一年后
		sql += " where validDate < curdate();";
		doSql(sql);

		// 检查会员记录停止——后面可能需要删除那些不需要的记录，省的麻烦——好像好不能删除，因为要统计停止情况
		sql = "update member";
		sql += " set status=" + MemberStatus.over;
		sql += " where overDate <　curdate();";
		doSql(sql);
	}

	@Override
	public void cancelMember(String memberId) {
		// TODO Auto-generated method stub
		Member member = getByColumn(Member.class, "memberId", memberId);
		member.setStatus(MemberStatus.over);
		update(member);
	}

	@Override
	public HashMap<String, Integer> getAgeData() {
		// TODO Auto-generated method stub
		HashMap<String, Integer> result = new HashMap<String, Integer>();

		String sql = "select sum(age<20) as '20以下', sum(age>=20 and age<30) as '20-30', sum(age>=30 and age<40) as '30-40', sum(age>=40 and age<50) as '40-50', sum(age>=50 and age<60) as '50-60', sum(age>=60) as '60以上' from member;";
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) doSqlQuery(sql);
		Object[] res = list.get(0);
		int n1 = Integer.parseInt(res[0].toString());
		int n2 = Integer.parseInt(res[1].toString());
		int n3 = Integer.parseInt(res[2].toString());
		int n4 = Integer.parseInt(res[3].toString());
		int n5 = Integer.parseInt(res[4].toString());
		int n6 = Integer.parseInt(res[5].toString());

		result.put("20以下", n1);
		result.put("20-30", n2);
		result.put("30-40", n3);
		result.put("40-50", n4);
		result.put("50-60", n5);
		result.put("60以上", n6);

		return result;
	}

	@Override
	public HashMap<String, Integer> getSexData() {
		// TODO Auto-generated method stub
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		String sql = "select sum(sex='男') as 男,sum(sex='女') as 女 from member;";
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) doSqlQuery(sql);
		Object[] res = list.get(0);
		int man = Integer.parseInt(res[0].toString());
		int woman = Integer.parseInt(res[1].toString());
		result.put("男", man);
		result.put("女", woman);

		return result;
	}

	@Override
	public HashMap<String, Integer> getAddressData() {
		// TODO Auto-generated method stub
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		String sql = "select prov,count(*) from member group by prov;";
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) doSqlQuery(sql);
		for (Object[] objects : list) {
			String prov = (String) objects[0];
			int num = Integer.parseInt(objects[1].toString());
			result.put(prov, num);
		}

		return result;
	}
}
