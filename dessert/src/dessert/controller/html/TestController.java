package dessert.controller.html;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.entity.Shop;
import dessert.service.ShopService;
import dessert.service.TestService;

public class TestController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	public TestService testService;

	@Autowired
	public ShopService shopService;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 26; i++) {
			Shop shop = new Shop();
			shop.setName("shop" + (i + 2));
			shop.setAddress("江苏省南京市鼓楼区汉口路22号");
			shop.setOwner("严顺宽");
			shop.setTelephone("18013878510");
			shopService.add(shop);
		}
		return Configure.SUCCESS;
	}
}
