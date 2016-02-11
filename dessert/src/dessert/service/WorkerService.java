package dessert.service;

import java.util.ArrayList;

import dessert.VO.WorkerVO;
import dessert.entity.Worker;
import dessert.util.UserType;

/**
 * @author 严顺宽
 */
public interface WorkerService {
	public long add(Worker worker);

	public void delete(Worker worker);

	public void update(Worker worker);

	public ArrayList<WorkerVO> getWorkers(ArrayList<UserType> types);
}
