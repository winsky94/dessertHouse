package dessert.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.service.PlanService;

/**
 * @author 严顺宽
 */
public class ManagerJsonController extends BaseController {
	private static final long serialVersionUID = 1L;
	private String message = "";
	@Autowired
	private PlanService planService;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	public String approvePlan() {
		Map<String, String> params = getParams();
		long planId = Long.parseLong(params.get("planId"));
		int result = Integer.parseInt(params.get("result"));
		int line = planService.approve(planId, result);
		if (line == 1) {
			message = "success";
		} else {
			message = "fail";
		}
		return Configure.SUCCESS;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
