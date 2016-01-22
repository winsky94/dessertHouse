package dessert.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.service.TestService;

public class TestJsonController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	public TestService testService;
	public int a;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		// System.out.println(testService.test());
		a = 1;
		return Configure.SUCCESS;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

}
