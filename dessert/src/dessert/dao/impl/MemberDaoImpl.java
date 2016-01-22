package dessert.dao.impl;

import java.util.List;

import org.hibernate.Session;

import dessert.dao.MemberDao;
import dessert.entity.Member;

/**
 * @author 严顺宽
 * @date 2016年1月18日 下午5:33:32
 */
public class MemberDaoImpl extends BaseDaoImpl<Member> implements MemberDao {

	@Override
	public String signUp(Member member) {
		// TODO Auto-generated method stub
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
				"select max(memberId) from member").list();
		if (list == null) {
			return null;
		} else {
			return list.get(0);
		}
	}

}
