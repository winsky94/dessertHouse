package dessert.service;

import java.util.HashMap;

import dessert.util.UserType;

/**
 * @author 严顺宽
 */
public interface LoginService {
	public HashMap<UserType, String> login(String userName, String password);
}
