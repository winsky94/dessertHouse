package dessert.dao;

import java.util.ArrayList;

import dessert.entity.Shop;

/**
 * @author 严顺宽
 */
public interface ShopDao {
	public void add(Shop shop);

	public void delete(Shop shop);

	public void update(Shop shop);

	public ArrayList<Shop> getShops();
}
