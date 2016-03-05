package dessert.dao;

import java.util.ArrayList;
import java.util.HashMap;

import dessert.entity.Worker;
import dessert.util.UserType;

/**
 * @author 严顺宽
 */
public interface WorkerDao {
	public HashMap<UserType, String> checkLogin(String workerId, String password);

	public long add(Worker worker);

	public void delete(Worker worker);

	public void update(Worker worker);

	public ArrayList<Worker> getWorkers();

	public ArrayList<Worker> getWorkers(ArrayList<UserType> types);

	public String getMaxWorkerId();

	public Worker getWorkerByWorkerId(String workerId);

	public String changePW(String workerId, String originalPW, String newPW);
}
