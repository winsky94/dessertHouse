package dessert.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import dessert.VO.DessertVO;
import dessert.VO.Favorite;
import dessert.VO.HotDessert;
import dessert.entity.ConsumeRecord;
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

	/**
	 * 会员消费：购买or预约商品
	 * 
	 * @param consumeRecord
	 * 
	 * @return 购买结果，msg:结果；discount：折扣；point：此次消费的累积积分
	 */
	public HashMap<String, Object> consume(ConsumeRecord record);

	/**
	 * 取消交易
	 * 
	 * @param id
	 */
	public void cancelConsume(long id);

	/**
	 * 逐月统计产品销量
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public LinkedList<HotDessert> hotDessert(String year, String month);

	/**
	 * 逐月统计会员喜好
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public ArrayList<Favorite> memberFavorite(String year, String month);
}
