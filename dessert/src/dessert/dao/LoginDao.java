package dessert.dao;

import dessert.util.UserType;

/** 
 * @author 严顺宽 
 */
public interface LoginDao {
	public UserType login(String userName,String password);
}
