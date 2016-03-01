package dessert.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.entity.Member;
import dessert.service.MemberService;

/**
 * @author 严顺宽
 * @date 2016年3月1日 下午8:40:38
 */
public class MemberJsonController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	private MemberService memberService;
	private Member member;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	public String memberInfo() {
		Map<String, String> params = getParams();
		String name = params.get("name");
		if (name != null) {
			member = memberService.getMemberInfo(name);
		} else {
			member = null;
		}
		return Configure.SUCCESS;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}