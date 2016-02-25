package dessert.service;

import java.util.ArrayList;

import dessert.entity.Dessert;

/**
 * @author 严顺宽
 */
public interface DessertService {
	public long add(Dessert dessert);

	public void delete(Dessert dessert);

	public void update(Dessert dessert);

	/**
	 * 根据名称判断是否存在，不允许商品重名
	 * 
	 * @param name
	 * @return
	 */
	public boolean checkExist(String name);

	public ArrayList<Dessert> getDessertByName(ArrayList<String> names);

	/**
	 * 用在删除的地方，通过名称得到dessert的id
	 * 
	 * @param name
	 * @return
	 */
	public long getDesertIdByName(String name);
}
