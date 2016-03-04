package dessert.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.entity.Member;
import dessert.service.MemberService;
import dessert.util.DateUtil;

/**
 * @author 严顺宽
 */
public class SignUpJsonController extends BaseController {
	private static final long serialVersionUID = 1L;
	private String message;
	@Autowired
	public MemberService memberService;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub

		String userName = params.get("userName");
		String password = params.get("password");

		int age = Integer.parseInt(params.get("age"));
		String sex = params.get("sex");
		String birthday = params.get("birthday");
		String telephone = params.get("telephone");
		String cardId = params.get("cardId");
		String prov = params.get("prov");
		String city = params.get("city");
		String dist = params.get("dist");
		

		Member member = new Member();
		member.setName(userName);
		member.setPassword(password);
		member.setAge(age);
		member.setSex(sex);
		member.setBirthday(birthday);
		member.setTelephone(telephone);
		member.setCardId(cardId);
		member.setProv(prov);
		member.setCity(city);
		member.setDist(dist);
		String createAt=DateUtil.getToday();
		member.setCreateAt(createAt);

		message = memberService.signUp(member);
		
		return Configure.SUCCESS;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
