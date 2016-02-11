package dessert.controller.html;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.WorkerVO;
import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.service.WorkerService;
import dessert.util.UserType;

/**
 * @author 严顺宽
 */
public class WaitressController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	public WorkerService workerService;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		ArrayList<WorkerVO> workers = new ArrayList<WorkerVO>();
		
		ArrayList<UserType> types=new ArrayList<UserType>();
		types.add(UserType.waitress);
		types.add(UserType.waitressManager);
		workers = workerService.getWorkers(types);
		session().setAttribute(Configure.WAITRESS_SESSION, workers);
		return Configure.ADMIN_WAITRESS;
	}

}
