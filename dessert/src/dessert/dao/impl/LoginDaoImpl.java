package dessert.dao.impl;

import java.util.HashMap;

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
	public HashMap<UserType, String> login(String userName, String password) {
		// TODO Auto-generated method stub
		HashMap<UserType, String> result = new HashMap<UserType, String>();

		HashMap<UserType, String> memberResult = memberDao.checkLogin(userName,
				password);
		if (!memberResult.containsKey(UserType.error)) {
			// 会员登录
			result = memberResult;
		} else {
			result = workerDao.checkLogin(userName, password);
		}
		return result;
	}
}
