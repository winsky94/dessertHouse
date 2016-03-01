package dessert.controller.html;

import java.util.Map;

import dessert.configure.Configure;
import dessert.controller.BaseController;

/**
 * @author 严顺宽
 */
public class DessertDetailController extends BaseController {
	private static final long serialVersionUID = 1L;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		System.out.println("DessertDetailController");
		return Configure.DESSERT_DETAIL;
	}

}
