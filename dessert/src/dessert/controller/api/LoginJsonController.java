package dessert.controller.api;

import java.util.HashMap;
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
	private String lastLoadTime;
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
		HashMap<UserType, String> logInResult = loginService.login(userName,
				password);
		for (UserType key : logInResult.keySet()) {
			userType = key;
			lastLoadTime = logInResult.get(key);
		}
		return Configure.SUCCESS;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getLastLoadTime() {
		return lastLoadTime;
	}

	public void setLastLoadTime(String lastLoadTime) {
		this.lastLoadTime = lastLoadTime;
	}

}
