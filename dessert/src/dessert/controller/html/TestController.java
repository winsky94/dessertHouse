package dessert.controller.html;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.service.TestService;

public class TestController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	public TestService testService;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		return Configure.SUCCESS;
	}
}
