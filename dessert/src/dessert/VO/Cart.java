package dessert.VO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * @author 严顺宽
 * @date 2016年3月6日 下午8:10:17
 */
public class Cart {
	private HashMap<Long, CartDessert> myCart = new HashMap<Long, CartDessert>();

	// 添加
	public void add(long dessertId, CartDessert cartDessert) {
		if (myCart.containsKey(dessertId)) {
			// 如果已经加入到购物车
			CartDessert mydessert = myCart.get(dessertId);
			int shoppingNum = cartDessert.getNum();
			mydessert.setNum(shoppingNum + mydessert.getNum());
		} else {
			myCart.put(dessertId, cartDessert);
		}

	}

	// 删除
	public void delete(long dessertId) {
		myCart.remove(dessertId);
	}

	// 清空购物车
	public void clear() {
		myCart.clear();
	}

	// 得到购物车内容
	public ArrayList<CartDessert> getMyCart() {
		ArrayList<CartDessert> myCart = new ArrayList<CartDessert>();
		Iterator<Entry<Long, CartDessert>> iter = this.myCart.entrySet()
				.iterator();
		while (iter.hasNext()) {
			Entry<Long, CartDessert> entry = (Entry<Long, CartDessert>) iter
					.next();
			CartDessert cartDessert = (CartDessert) entry.getValue();
			myCart.add(cartDessert);
		}
		return myCart;
	}

	/**
	 * 得到购物的总价格
	 * 
	 * @return
	 */
	public float getTotalPrice() {
		float total = 0;
		Iterator<Entry<Long, CartDessert>> iter = this.myCart.entrySet()
				.iterator();
		while (iter.hasNext()) {
			Entry<Long, CartDessert> entry = (Entry<Long, CartDessert>) iter
					.next();
			CartDessert cartDessert = (CartDessert) entry.getValue();
			int num = cartDessert.getNum();
			double price = cartDessert.getPrice();
			total += (num * price);
		}

		return total;
	}
}
