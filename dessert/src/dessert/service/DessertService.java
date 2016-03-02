package dessert.service;

import java.util.ArrayList;

import dessert.VO.DessertVO;
import dessert.entity.Dessert;

/**
 * @author 严顺宽
 */
public interface DessertService {
	public long add(Dessert dessert);

	public void delete(Dessert dessert);

	public void update(Dessert dessert);

	/**
	 * 根据名称、店名和日期判断是否存在，不允许商品重名
	 * 
	 * @param name
	 * @param date
	 * @param shopName
	 * @return
	 */
	public boolean checkExist(String name, String date, String shopName);

	public ArrayList<DessertVO> getDessertByName(ArrayList<String> names);

	/**
	 * 用在删除的地方，通过名称得到dessert的id
	 * 
	 * @param name
	 * @return
	 */
	public long getDessertIdByName(String name);

	public DessertVO getDessertById(long id);

	/**
	 * 得到全部商品
	 * 
	 * @return
	 */
	public ArrayList<DessertVO> getAllDesserts();
}
