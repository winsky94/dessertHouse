package dessert.controller.api;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.ShopVO;
import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.entity.Shop;
import dessert.service.ShopService;

/**
 * @author 严顺宽
 */
public class ShopJsonController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	public ShopService shopService;
	private String message;
	public ArrayList<ShopVO> shops = new ArrayList<ShopVO>();

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		shops = shopService.getShops();
		HttpSession session = session();
		session.setAttribute(Configure.SHOP_SESSION, shops);
		return Configure.SUCCESS;
	}

	public String addShop() {
		Map<String, String> params = getParams();

		Shop shop = new Shop();
		shop.setName(params.get("name"));
		shop.setOwner(params.get("owner"));
		shop.setTelephone(params.get("telephone"));
		shop.setAddress(params.get("address"));
		long res = shopService.add(shop);
		if (res == Configure.SHOP_EXIST) {
			message = Configure.SHOP_EXIST_MESSAGE;
		} else {
			message = Configure.SHOP_SUCCESS_ACTION;
		}
		return Configure.SUCCESS;

	}

	public String editShop() {
		Map<String, String> params = getParams();
		Shop shop = new Shop();
		shop.setId(Long.parseLong(params.get("shopId")));
		shop.setName(params.get("name"));
		shop.setOwner(params.get("owner"));
		shop.setTelephone(params.get("telephone"));
		shop.setAddress(params.get("address"));

		shopService.update(shop);
		message = Configure.SHOP_SUCCESS_ACTION;

		return Configure.SUCCESS;
	}

	public String deleteShop() {
		Map<String, String> params = getParams();
		Shop shop = new Shop();
		shop.setId(Long.parseLong(params.get("shopId")));

		shopService.delete(shop);
		message = Configure.SHOP_SUCCESS_ACTION;
		return Configure.SUCCESS;
	}

	public ArrayList<ShopVO> getShops() {
		return shops;
	}

	public void setShops(ArrayList<ShopVO> shops) {
		this.shops = shops;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
