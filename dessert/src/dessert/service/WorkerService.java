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

	public String generateWorkerId(UserType type);

	/**
	 * 得到服务员的所属分店
	 * 
	 * @param workerId
	 * @return
	 */
	public String getWorkerShop(String workerId);

	/**
	 * 修改密码
	 * 
	 * @param workerId
	 * @param originalPW
	 * @param newPW
	 * @return
	 */
	public String changePW(String workerId, String originalPW, String newPW);

	public Worker getWorkerById(long id);
}
