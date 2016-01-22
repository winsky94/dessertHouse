package dessert.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.entity.Member;
import dessert.service.MemberService;
import dessert.util.DesUtils;

/**
 * @author 严顺宽
 */
public class SignUpController extends BaseController {
	private static final long serialVersionUID = 1L;
	private String message;
	@Autowired
	public MemberService memberService;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		System.out.println("test");
		
		String userName = params.get("userName");
		DesUtils des = new DesUtils(Configure.KEY);// 自定义密钥
		String password = des.encrypt(params.get("password"));

		int age=0;
		try {
			age = Integer.parseInt(params.get("age"));
		} catch (Exception e) {
			// TODO: handle exception
			return Configure.AGE_ERROR;
		} finally {
		}
		
		String sex = params.get("sex");
		String birthday = params.get("birthday");
		String telephone = params.get("telephone");
		String cardId = params.get("cardId");

		Member member = new Member();
		member.setName(userName);
		member.setPassword(password);
		member.setAge(age);
		member.setSex(sex);
		member.setBirthday(birthday);
		member.setTelephone(telephone);
		member.setCardId(cardId);

		message = memberService.signUp(member);
		System.out.println(message);
		return Configure.SUCCESS;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
