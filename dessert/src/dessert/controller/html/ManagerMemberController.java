package dessert.controller.html;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.ConsumeVO;
import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.entity.RechargeRecord;
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
		String sessionName = Configure.MANAGER_CONSUME_SESSION;
		session().removeAttribute(sessionName);

//		ArrayList<ConsumeVO> records = memberService.getConsumeRecord();
//		if (records == null) {
//		} else {
//			session().setAttribute(sessionName, records);
//		}
		return Configure.MANAGER_CONSUME;
	}

	public String manager_sale() {
		String sessionName = Configure.MANAGER_SALE_SESSION;
		session().removeAttribute(sessionName);

		ArrayList<ConsumeVO> records = memberService.getConsumeRecord();
		if (records == null) {
		} else {
			session().setAttribute(sessionName, records);
		}
		return Configure.MANAGER_SALE;
	}

	public String recharge() {
		String sessionName = Configure.MANAGER_RECHARGE_SESSION;
		session().removeAttribute(sessionName);

		List<RechargeRecord> records = memberService.getRechargeRecord();
		if (records == null) {
		} else {
			session().setAttribute(sessionName, records);
		}
		return Configure.MANAGER_RECHARGE;
	}
}
