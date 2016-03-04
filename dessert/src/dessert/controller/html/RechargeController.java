package dessert.controller.html;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.entity.RechargeRecord;
import dessert.service.MemberService;

/**
 * @author 严顺宽
 * @date 2016年3月4日 下午8:35:09
 */
public class RechargeController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	private MemberService memberService;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		String sessionName = Configure.Member_RECHARGE_SESSION;
		session().removeAttribute(sessionName);

		String memberId = params.get("memberId");
		if (memberId == null) {
			System.err.println("会员id为空，查不到充值记录");
		} else {
			List<RechargeRecord> records = memberService
					.getRechargeRecord(memberId);
			if (records == null) {
			} else {
				session().setAttribute(sessionName, records);
			}
		}
		return Configure.Member_RECHARGE;
	}

}
