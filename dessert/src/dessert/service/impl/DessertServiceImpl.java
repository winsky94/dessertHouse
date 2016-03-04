package dessert.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.DessertVO;
import dessert.configure.Configure;
import dessert.dao.ConsumeDao;
import dessert.dao.DessertDao;
import dessert.entity.ConsumeRecord;
import dessert.entity.Dessert;
import dessert.entity.Member;
import dessert.service.DessertService;
import dessert.service.MemberService;
import dessert.util.ConvertVO;
import dessert.util.MemberHelper;

/**
 * @author 严顺宽
 */
public class DessertServiceImpl implements DessertService {
	@Autowired
	private DessertDao dessertDao;
	@Autowired
	private ConsumeDao consumeDao;
	@Autowired
	private MemberService memberService;

	@Override
	public long add(Dessert dessert) {
		// TODO Auto-generated method stub
		return dessertDao.add(dessert);
	}

	@Override
	public void delete(Dessert dessert) {
		// TODO Auto-generated method stub
		dessertDao.delete(dessert);
	}

	@Override
	public void update(Dessert dessert) {
		// TODO Auto-generated method stub
		dessertDao.update(dessert);
	}

	@Override
	public boolean checkExist(String name, String date, String shopName) {
		// TODO Auto-generated method stub
		return dessertDao.checkExist(name, date, shopName);
	}

	@Override
	public ArrayList<DessertVO> getDessertByName(ArrayList<String> names) {
		// TODO Auto-generated method stub
		ArrayList<DessertVO> result = new ArrayList<DessertVO>();
		for (String name : names) {
			Dessert dessert = dessertDao.getDessertByName(name);
			if (dessert != null) {
				result.add(ConvertVO.dessertToVO(dessert));
			} else {
				System.err.println("有个商品的名字不存在~，名字为：" + name);
			}
		}
		return result;
	}

	@Override
	public long getDessertIdByName(String name) {
		// TODO Auto-generated method stub
		Dessert dessert = dessertDao.getDessertByName(name);
		if (dessert != null) {
			long id = dessert.getId();
			return id;
		} else {
			return -1;
		}
	}

	@Override
	public DessertVO getDessertById(long id) {
		// TODO Auto-generated method stub
		Dessert dessert = dessertDao.getDessertById(id);
		if (dessert != null) {
			return ConvertVO.dessertToVO(dessert);
		}
		return null;
	}

	@Override
	public ArrayList<DessertVO> getAllDesserts() {
		// TODO Auto-generated method stub
		ArrayList<DessertVO> result = new ArrayList<DessertVO>();
		ArrayList<Dessert> desserts = new ArrayList<Dessert>();
		desserts = dessertDao.getAllDesserts();
		if (desserts != null) {
			for (Dessert dessert : desserts) {
				DessertVO vo = ConvertVO.dessertToVO(dessert);
				result.add(vo);
			}
		}
		return result;
	}

	@Override
	public HashMap<String, Object> consume(ConsumeRecord record) {
		// TODO Auto-generated method stub
		HashMap<String, Object> result = new HashMap<String, Object>();

		String memberId = record.getMemberId();
		Member member = memberService.getMemberByMemberId(memberId);
		double payMoney = record.getMoney();

		// 消费，计算优惠和积分
		double discount = MemberHelper.getDiscount(member, payMoney);
		int point = MemberHelper.getPoint(member, payMoney);
		result.put("discount", discount);
		result.put("point", point);
		record.setDiscount(discount);
		record.setPoint(point);

		int originalPoint = member.getPoint();
		int validPoint = originalPoint + point;
		member.setPoint(validPoint);

		boolean cash = record.getCash();
		if (!cash) {
			// 从会员余额中扣钱
			double originalMoney = member.getValidMoney();
			double validMoney = originalMoney - payMoney;
			if (validMoney < 0) {
				result.put("msg", Configure.CARD_NOT_ENOUGH);
			} else {
				// 更新账户余额
				member.setValidMoney(validMoney);
				memberService.update(member);

				// 商品库存
				String msg = updateStock(record);
				result.put("msg", msg);
			}
		} else {
			// 商品库存
			String msg = updateStock(record);
			result.put("msg", msg);
		}

		return result;
	}

	private String updateStock(ConsumeRecord record) {
		String action = record.getAction();
		if (Configure.BUY.equals(action)) {
			// 商品库存
			long dessertId = record.getDessertId();
			Dessert dessert = dessertDao.getDessertById(dessertId);
			int stockNum = dessert.getStockNum();
			int buyNum = record.getNum();
			int num = stockNum - buyNum;
			if (num < 0) {
				return Configure.STOCK_NOT_ENOUGH;
			} else {
				// 更新商品库存
				dessert.setStockNum(num);
				dessertDao.update(dessert);

				// 增加购买记录
				consumeDao.consume(record);
				return Configure.SUCCESS;
			}
		} else if (Configure.APPOINTMENT.equals(action)) {
			// 预约，不需要-库存
			// 增加预约记录
			consumeDao.consume(record);
			return Configure.SUCCESS;

		} else {
			return Configure.ERROR;
		}
	}
}
