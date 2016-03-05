package dessert.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.service.MemberService;
import dessert.service.PlanService;

/**
 * @author 严顺宽
 */
public class ManagerJsonController extends BaseController {
	private static final long serialVersionUID = 1L;
	private String message = "";
	@Autowired
	private PlanService planService;
	@Autowired
	private MemberService memberService;
	private HashMap<String, Integer> ageInfo = new HashMap<String, Integer>();
	private HashMap<String, Integer> sexInfo = new HashMap<String, Integer>();
	private HashMap<String, Integer> addressInfo = new HashMap<String, Integer>();

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

	public String baseData() {
		ageInfo = memberService.getAgeData();
		sexInfo = memberService.getSexData();
		addressInfo = memberService.getAddressData();

		return Configure.SUCCESS;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HashMap<String, Integer> getAgeInfo() {
		return ageInfo;
	}

	public void setAgeInfo(HashMap<String, Integer> ageInfo) {
		this.ageInfo = ageInfo;
	}

	public HashMap<String, Integer> getSexInfo() {
		return sexInfo;
	}

	public void setSexInfo(HashMap<String, Integer> sexInfo) {
		this.sexInfo = sexInfo;
	}

	public HashMap<String, Integer> getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(HashMap<String, Integer> addressInfo) {
		this.addressInfo = addressInfo;
	}

}
