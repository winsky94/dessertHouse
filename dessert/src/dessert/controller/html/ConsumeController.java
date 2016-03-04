package dessert.controller.html;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.ConsumeVO;
import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.service.MemberService;

/**
 * @author 严顺宽
 * @date 2016年3月4日 上午9:27:00
 */
public class ConsumeController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	private MemberService memberService;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		String sessionName = Configure.Member_CONSUME_SESSION;
		session().removeAttribute(sessionName);

		String memberId = params.get("memberId");
		if (memberId == null) {
			System.err.println("会员id为空，查不到消费记录");
		} else {
			ArrayList<ConsumeVO> records = memberService
					.getConsumeRecord(memberId);
			if (records == null) {
			} else {
				session().setAttribute(sessionName, records);
			}
		}
		return Configure.Member_CONSUME;
	}
}
