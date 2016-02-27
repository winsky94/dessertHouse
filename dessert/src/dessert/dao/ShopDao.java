package dessert.dao;

import java.util.ArrayList;

import dessert.entity.Shop;

/**
 * @author 严顺宽
 */
public interface ShopDao {
	public long add(Shop shop);

	public void delete(Shop shop);

	public void update(Shop shop);

	public ArrayList<Shop> getShops();

	/**
	 * 检查服务员所属店面是否存在
	 * 
	 * @param shopName
	 *            店面名称
	 * @return
	 */
	public boolean checkShop(String shopName);

	public Shop getShop(String shopName);
}
