package dessert.dao.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.dao.LoginDao;
import dessert.dao.MemberDao;
import dessert.dao.WorkerDao;
import dessert.util.UserType;

/**
 * @author 严顺宽
 */
@Transactional
public class LoginDaoImpl implements LoginDao {
	@Autowired
	public WorkerDao workerDao;
	@Autowired
	public MemberDao memberDao;

	@Override
	public UserType login(String userName, String password) {
		// TODO Auto-generated method stub
		UserType result = UserType.error;

		if (memberDao.checkLogin(userName, password) != UserType.error) {
			// 检查是否是会员登录
			result = UserType.member;
		} else {
			// 检查是否是员工登录
			return workerDao.checkLogin(userName, password);
		}
		return result;
	}
}
