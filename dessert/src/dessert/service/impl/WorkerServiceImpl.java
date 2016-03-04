package dessert.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.WorkerVO;
import dessert.configure.Configure;
import dessert.dao.WorkerDao;
import dessert.entity.Worker;
import dessert.service.WorkerService;
import dessert.util.ConvertVO;
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
		// 设置默认密码并加密
		DesUtils des = new DesUtils(Configure.KEY);// 自定义密钥
		String password = des.encrypt(Configure.DEFAULT_PASSWORD);
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

	@Override
	public ArrayList<WorkerVO> getWorkers(ArrayList<UserType> types) {
		// TODO Auto-generated method stub
		ArrayList<WorkerVO> result = new ArrayList<WorkerVO>();
		ArrayList<Worker> workers = new ArrayList<Worker>();
		if (workers != null) {
			workers = workerDao.getWorkers(types);
			for (Worker worker : workers) {
				WorkerVO vo = ConvertVO.workerToVO(worker);
				result.add(vo);
			}
		}
		return result;
	}

	@Override
	public String generateWorkerId(UserType type) {
		// TODO Auto-generated method stub
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
			System.err.println("WorkerServiceImpl.generateWorkerId 出错啦");
			break;
		}

		workerId += generateWorkerIdNum();
		return workerId;
	}

	/**
	 * 生成固定位数数字编号的员工id，常量定义Configure.STR_FORMAT
	 * 
	 * @return
	 */
	private String generateWorkerIdNum() {
		String max = workerDao.getMaxWorkerId();
		if (max == null) {
			max = "0";
		}
		int maxId = Integer.parseInt(max);
		int id = maxId + 1;
		DecimalFormat df = new DecimalFormat(Configure.STR_FORMAT);
		return df.format(id);
	}

	@Override
	public String getWorkerShop(String workerId) {
		// TODO Auto-generated method stub
		String owingTo = null;
		Worker worker = workerDao.getWorkerByWorkerId(workerId);
		if (worker != null) {
			owingTo = worker.getOwingTo();
		}
		return owingTo;
	}

}
