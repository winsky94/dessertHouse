package dessert.service;

import dessert.util.UserType;

/**
 * @author 严顺宽
 */
public interface LoginService {
	public UserType login(String userName, String password);
}
