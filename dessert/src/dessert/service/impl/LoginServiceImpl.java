package dessert.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.configure.Configure;
import dessert.dao.LoginDao;
import dessert.service.LoginService;
import dessert.util.DesUtils;
import dessert.util.UserType;

/**
 * @author 严顺宽
 */
public class LoginServiceImpl implements LoginService {
	@Autowired
	public LoginDao loginDao;

	@Override
	public UserType login(String userName, String password) {
		// TODO Auto-generated method stub
		DesUtils des = new DesUtils(Configure.KEY);// 自定义密钥
		String passwordEncrypt = des.encrypt(password);
		return loginDao.login(userName, passwordEncrypt);
	}

}
