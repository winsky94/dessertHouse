package dessert.controller.api;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.WorkerVO;
import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.entity.Worker;
import dessert.service.WorkerService;
import dessert.util.EnumUtil;
import dessert.util.UserType;

/**
 * @author 严顺宽
 */
public class WaitressJsonController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	public WorkerService workerService;
	private String message;
	private String workerId;
	public ArrayList<WorkerVO> workers = new ArrayList<WorkerVO>();

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		ArrayList<UserType> types = new ArrayList<UserType>();
		types.add(UserType.waitress);
		types.add(UserType.waitressManager);
		workers = workerService.getWorkers(types);
		HttpSession session = session();
		session.setAttribute(Configure.WAITRESS_SESSION, workers);
		return Configure.SUCCESS;
	}

	public String addWaitress() {
		Map<String, String> params = getParams();

		Worker worker = new Worker();
		worker.setWorkerId(params.get("workerId"));
		worker.setType(EnumUtil.getEnuByName(UserType.class, params.get("type")));
		if (worker.getType().equals(UserType.waitressManager)) {
			worker.setOwingTo(Configure.WAITRESS);// 总店服务员的特定所属店面
		} else {
			worker.setOwingTo(params.get("owingTo"));
		}

		workerService.add(worker);
		message = Configure.WAITRESS_SUCCESS_ACTION;

		return Configure.SUCCESS;

	}

	public String editWaitress() {
		Map<String, String> params = getParams();

		Worker worker = new Worker();
		worker.setWorkerId(params.get("workerId"));
		worker.setType(EnumUtil.getEnuByName(UserType.class, params.get("type")));
		worker.setOwingTo(params.get("owingTo"));

		workerService.update(worker);
		message = Configure.WAITRESS_SUCCESS_ACTION;

		return Configure.SUCCESS;
	}

	public String deleteWaitress() {
		Map<String, String> params = getParams();
		Worker worker = new Worker();
		worker.setId(Long.parseLong(params.get("id")));

		workerService.delete(worker);
		message = Configure.WAITRESS_SUCCESS_ACTION;

		return Configure.SUCCESS;
	}

	public String generateWaitressId() {
		Map<String, String> params = getParams();
		UserType type = EnumUtil.getEnuByName(UserType.class,
				params.get("type"));
		workerId = workerService.generateWorkerId(type);

		return Configure.SUCCESS;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ArrayList<WorkerVO> getWorkers() {
		return workers;
	}

	public void setWorkers(ArrayList<WorkerVO> workers) {
		this.workers = workers;
	}

	public String getWorkerId() {
		return workerId;
	}

	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}

}
