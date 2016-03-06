package dessert.controller.html;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.service.MemberService;

/**
 * @author 严顺宽
 * @date 2016年3月4日 上午10:55:51
 */
public class ManagerMemberController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	private MemberService memberService;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	public String consume() {
		return Configure.MANAGER_CONSUME;
	}

	public String manager_sale() {
		return Configure.MANAGER_SALE;
	}

	public String recharge() {
		return Configure.MANAGER_RECHARGE;
	}

	public String hotDessert() {
		return Configure.MANAGER_HOT_DESSERT;
	}
}
