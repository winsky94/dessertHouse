package dessert.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dessert.dao.MemberDao;
import dessert.entity.Member;
import dessert.util.CheckError;
import dessert.util.TimeUtil;
import dessert.util.UserType;

/**
 * @author 严顺宽
 */
public class MemberDaoImpl extends BaseDaoImpl<Member> implements MemberDao {

	@Override
	public String signUp(Member member) {
		// TODO Auto-generated method stub
		add(member);
		return null;
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
	public UserType checkLogin(String userName, String password) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Member.class);
		criteria.add(Restrictions.eq("name", userName));
		criteria.add(Restrictions.eq("password", password));
		@SuppressWarnings("unchecked")
		List<Member> members = criteria.list();
		if (members == null || members.size() == 0) {
			return UserType.error;
		} else {
			// 检查可能的错误
			CheckError.checkListSize(members);
			// 更新最近登录时间
			Member member = members.get(0);
			String lastLoadTime = TimeUtil.getCurrentTime();
			member.setLastLoadTime(lastLoadTime);
			update(member);
			return UserType.member;
		}
	}
}
