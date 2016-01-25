package dessert.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.service.LoginService;
import dessert.util.UserType;

/**
 * @author 严顺宽
 */
public class LoginJsonController extends BaseController {
	private static final long serialVersionUID = 1L;
	private UserType userType;
	@Autowired
	public LoginService loginService;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	public String login() {
		Map<String, String> params = getParams();
		String userName = params.get("userName");
		String password = params.get("password");
		userType = loginService.login(userName, password);
		return Configure.SUCCESS;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

}
