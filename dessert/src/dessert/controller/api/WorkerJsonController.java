package dessert.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.service.WorkerService;

/**
 * @author 严顺宽
 */
public class WorkerJsonController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	private WorkerService workerService;
	private String message;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		String workerId=params.get("workerId");
		String originalPW=params.get("originalPW");
		String newPW=params.get("newPW");
		
		message=workerService.changePW(workerId, originalPW, newPW);
				
		return Configure.SUCCESS;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
