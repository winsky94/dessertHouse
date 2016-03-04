package dessert.controller.html;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.ConsumeVO;
import dessert.VO.WorkerVO;
import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.entity.RechargeRecord;
import dessert.service.MemberService;
import dessert.service.WorkerService;
import dessert.util.UserType;

/**
 * @author 严顺宽
 */
public class WaitressController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	private WorkerService workerService;
	@Autowired
	private MemberService memberService;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		ArrayList<WorkerVO> workers = new ArrayList<WorkerVO>();

		ArrayList<UserType> types = new ArrayList<UserType>();
		types.add(UserType.waitress);
		types.add(UserType.waitressManager);
		workers = workerService.getWorkers(types);
		session().setAttribute(Configure.WAITRESS_SESSION, workers);
		return Configure.ADMIN_WAITRESS;
	}

	public String memberConsume() {
		String sessionName = Configure.WAITRESS_CONSUME_SESSION;
		session().removeAttribute(sessionName);

		Map<String, String> params = getParams();
		String memberId = params.get("memberId");
		if (memberId != null) {
			ArrayList<ConsumeVO> records = memberService
					.getConsumeRecord(memberId);
			session().setAttribute(sessionName, records);
		} else {
			System.err.println("WaitressController 会员id为空，查不到消费记录");
		}

		return Configure.WAITRESS_CONSUME;
	}

	public String memberRecharge() {
		String sessionName = Configure.WAITRESS_RECHARGE_SESSION;
		session().removeAttribute(sessionName);

		Map<String, String> params = getParams();
		String memberId = params.get("memberId");
		if (memberId != null) {
			List<RechargeRecord> records = memberService
					.getRechargeRecord(memberId);
			session().setAttribute(sessionName, records);
		} else {
			System.err.println("WaitressController 会员id为空，查不到充值记录");
		}

		return Configure.WAITRESS_RECHARGE;
	}

}
