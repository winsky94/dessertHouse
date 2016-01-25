package dessert.dao;

import dessert.util.UserType;

/** 
 * @author 严顺宽 
 */
public interface WorkerDao {
	public UserType checkLogin(String workerId,String password);
}
