package dessert.controller.html;

import java.util.Map;

import dessert.VO.Cart;
import dessert.configure.Configure;
import dessert.controller.BaseController;

/**
 * @author 严顺宽
 * @date 2016年3月6日 下午8:37:49
 */
public class CartController extends BaseController {
	private static final long serialVersionUID = 1L;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		String sessionName = Configure.Member_CART_SESSION;
		// 取出购物车
		Cart cart = (Cart) session().getAttribute(sessionName);
		if (cart == null) {
			return Configure.Member_EMPTY_CART;
		} else {
		}

		return Configure.Member_CART;
	}
}
