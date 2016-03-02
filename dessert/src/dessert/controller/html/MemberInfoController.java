package dessert.controller.html;

import java.util.Map;

import dessert.configure.Configure;
import dessert.controller.BaseController;

/**
 * @author 严顺宽
 * @date 2016年3月2日 下午4:55:28
 */
public class MemberInfoController extends BaseController {
	private static final long serialVersionUID = 1L;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		
		return Configure.MEMBER_INFO;
	}

}
