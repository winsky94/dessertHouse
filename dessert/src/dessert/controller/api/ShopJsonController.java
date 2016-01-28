package dessert.controller.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		shop.setName(params.get("data[0][name]"));
		shop.setOwner(params.get("data[0][owner]"));
		shop.setTelephone(params.get("data[0][telephone]"));
		shop.setAddress(params.get("data[0][address]"));
		shopService.add(shop);
		shops = shopService.getShops();

		return Configure.SUCCESS;

	}

	public String editShop() {
		Map<String, String> params = getParams();
		Set<String> keyset = params.keySet();

		Iterator<String> iter = keyset.iterator();
		String str = iter.next();
		Pattern pattern = Pattern.compile("[^0-9]");
		Matcher matcher = pattern.matcher(str);
		String id_str = matcher.replaceAll("");
		long id = Integer.parseInt(id_str);

		Shop shop = new Shop();

		shop.setId(id);
		shop.setName(params.get("data[" + id + "][name]"));
		shop.setOwner(params.get("data[" + id + "][owner]"));
		shop.setTelephone(params.get("data[" + id + "][telephone]"));
		shop.setAddress(params.get("data[" + id + "][address]"));

		shopService.update(shop);
		shops = shopService.getShops();
		return Configure.SUCCESS;
	}

	public String deleteShop() {
		Map<String, String> params = getParams();
		Set<String> keyset = params.keySet();

		Iterator<String> iter = keyset.iterator();
		String str = iter.next();

		Pattern pattern = Pattern.compile("[^0-9]");
		Matcher matcher = pattern.matcher(str);
		String id = matcher.replaceAll("");

		Shop shop = new Shop();

		shop.setId(Integer.parseInt(id));

		shopService.delete(shop);
		shops = shopService.getShops();
		return Configure.SUCCESS;
	}

	public ArrayList<ShopVO> getShops() {
		return shops;
	}

	public void setShops(ArrayList<ShopVO> shops) {
		this.shops = shops;
	}

}
