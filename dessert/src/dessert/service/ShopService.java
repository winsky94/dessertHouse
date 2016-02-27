package dessert.service;

import java.util.ArrayList;

import dessert.VO.ShopVO;
import dessert.entity.Shop;

/**
 * @author 严顺宽
 */
public interface ShopService {
	public long add(Shop shop);

	public void delete(Shop shop);

	public void update(Shop shop);

	public ArrayList<ShopVO> getShops();

	/**
	 * 检查服务员所属店面是否存在
	 * 
	 * @param shopName
	 *            店面名称
	 * @return
	 */
	public boolean checkShop(String shopName);
	
	public ShopVO getShop(String shopName);
}
