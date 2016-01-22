package dessert.util;

import dessert.VO.ShopVO;
import dessert.entity.Shop;

/**
 * @author 严顺宽
 */
public class ConvertVO {
	public static ShopVO shopToVO(Shop shop) {
		long id = shop.getId();
		String shopId = shop.getShopId();
		String name = shop.getName();
		String address = shop.getAddress();
		String owner = shop.getOwner();
		String telephone = shop.getTelephone();
		ShopVO vo = new ShopVO(id, shopId, name, address, owner, telephone);
		return vo;
	}
}
