package dessert.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.ShopVO;
import dessert.dao.ShopDao;
import dessert.entity.Shop;
import dessert.service.ShopService;
import dessert.util.ConvertVO;

/**
 * @author 严顺宽
 * @date 2016年1月20日 下午7:49:02
 */
public class ShopServiceImpl implements ShopService {
	@Autowired
	public ShopDao shopDao;

	@Override
	public long add(Shop shop) {
		// TODO Auto-generated method stub
		return shopDao.add(shop);

	}

	@Override
	public void delete(Shop shop) {
		// TODO Auto-generated method stub
		shopDao.delete(shop);
	}

	@Override
	public void update(Shop shop) {
		// TODO Auto-generated method stub
		shopDao.update(shop);
	}

	@Override
	public ArrayList<ShopVO> getShops() {
		// TODO Auto-generated method stub
		ArrayList<ShopVO> result = new ArrayList<ShopVO>();
		ArrayList<Shop> shops = new ArrayList<Shop>();
		if (shops != null) {
			shops = shopDao.getShops();
			for (Shop shop : shops) {
				ShopVO vo = ConvertVO.shopToVO(shop);
				result.add(vo);
			}
		}
		return result;
	}

}
