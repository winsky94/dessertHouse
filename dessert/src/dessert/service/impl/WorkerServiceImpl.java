package dessert.service.impl;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.configure.Configure;
import dessert.dao.WorkerDao;
import dessert.entity.Worker;
import dessert.service.WorkerService;
import dessert.util.DesUtils;
import dessert.util.UserType;

/**
 * @author 严顺宽
 */
public class WorkerServiceImpl implements WorkerService {
	@Autowired
	public WorkerDao workerDao;

	@Override
	public long add(Worker worker) {
		// TODO Auto-generated method stub
		UserType type = worker.getType();
		String workerId = "";
		switch (type) {
		case admin:
			workerId += "GL";
			break;
		case waitress:
			workerId += "FW";
			break;
		case waitressManager:
			workerId += "ZD";
			break;
		case manager:
			workerId += "JL";
			break;
		default:
			System.err.println("WorkerServiceImpl.add() 出错啦");
			break;
		}

		workerId += generateWorkerId();
		worker.setWorkerId(workerId);

		// 密码加密
		DesUtils des = new DesUtils(Configure.KEY);// 自定义密钥
		String password = des.encrypt(worker.getPassword());
		worker.setPassword(password);
		return workerDao.add(worker);
	}

	@Override
	public void delete(Worker worker) {
		// TODO Auto-generated method stub
		workerDao.delete(worker);
	}

	@Override
	public void update(Worker worker) {
		// TODO Auto-generated method stub
		workerDao.update(worker);
	}

	/**
	 * 生成7位的员工id
	 * 
	 * @return
	 */
	private String generateWorkerId() {
		String max = workerDao.getMaxWorkerId();
		if (max == null) {
			max = "0";
		}
		int maxId = Integer.parseInt(max);
		int id = maxId + 1;
		DecimalFormat df = new DecimalFormat(Configure.STR_FORMAT);
		return df.format(id);
	}

}
