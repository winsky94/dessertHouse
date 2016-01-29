package dessert.dao;

import dessert.entity.Worker;
import dessert.util.UserType;

/** 
 * @author 严顺宽 
 */
public interface WorkerDao {
	public UserType checkLogin(String workerId,String password);
	
	public long add(Worker worker);
	
	public void delete(Worker worker);
	
	public void update(Worker worker);
	
	public String getMaxWorkerId();
}
