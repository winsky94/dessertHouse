package dessert.controller.html;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.PlanVO;
import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.service.PlanService;

/**
 * @author 严顺宽
 */
public class PlanController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	public PlanService planService;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		String shopName = params.get("shopName");
		PlanVO planVO = planService.getPlans(shopName);
		session().setAttribute(Configure.PLAN_SESSION, planVO);
		return Configure.ZD_WAITRESS;
	}

}
