package dessert.controller.html;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.ShopVO;
import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.service.ShopService;

/**
 * @author 严顺宽
 */
public class ShopController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	public ShopService shopService;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		ArrayList<ShopVO> shops = new ArrayList<ShopVO>();
		shops = shopService.getShops();
		session().setAttribute(Configure.SHOP_SESSION, shops);
		return Configure.ADMIN_SHOP;
	}

}
