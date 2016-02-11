package dessert.controller.api;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.WorkerVO;
import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.service.WorkerService;
import dessert.util.UserType;

/**
 * @author 严顺宽
 */
public class WaitressJsonController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	public WorkerService workerService;
	private String message;
	public ArrayList<WorkerVO> workers = new ArrayList<WorkerVO>();

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
//		int typeInt = Integer.parseInt(params.get("userType"));
//		UserType type = UserType.values()[typeInt];
		ArrayList<UserType> types=new ArrayList<UserType>();
		types.add(UserType.waitress);
		types.add(UserType.waitressManager);
		workers = workerService.getWorkers(types);
		HttpSession session = session();
		session.setAttribute(Configure.WAITRESS_SESSION, workers);
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

}
