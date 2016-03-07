package dessert.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.Cart;
import dessert.VO.CartDessert;
import dessert.VO.DessertVO;
import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.entity.ConsumeRecord;
import dessert.entity.Member;
import dessert.service.DessertService;
import dessert.service.MemberService;
import dessert.util.TimeUtil;
import dessert.util.Week;

/**
 * @author 严顺宽
 * @date 2016年3月6日 下午8:02:19
 */
public class CartJsonController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	private DessertService dessertService;
	@Autowired
	private MemberService memberService;

	private String message;
	private double discount;
	private int point;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	public String myCart() {
		Map<String, String> params = getParams();
		String sessionName = Configure.Member_CART_SESSION;
		// 取出购物车
		Cart cart = (Cart) session().getAttribute(sessionName);
		if (cart == null) {
			cart = new Cart();
		}

		String dessertIdStr = params.get("dessertId");

		String action = params.get("action");

		
		long dessertId =-1;
		
		if (!"clear".equals(action)) {
			if (dessertIdStr == null) {
				return Configure.ERROR;
			}
			dessertId = Long.parseLong(dessertIdStr);
		}

		if ("add".equals(action)) {
			String numStr = params.get("num");
			int num = Integer.parseInt(numStr);

			DessertVO dessert = dessertService.getDessertById(dessertId);

			long id = dessert.getId();
			String name = dessert.getName();
			double price = dessert.getPrice();
			int stockNum = dessert.getStockNum();
			String path = dessert.getPath();
			String owingTo = dessert.getOwingTo();
			Week weekDay = dessert.getWeekDay();
			String date = dessert.getDate();

			CartDessert cartDessert = new CartDessert(id, name, price,
					stockNum, num, path, owingTo, weekDay, date);

			cart.add(dessertId, cartDessert);
			session().setAttribute(sessionName, cart);
			message = Configure.SUCCESS;
		} else if ("del".equals(action)) {
			cart.delete(dessertId);
			session().setAttribute(sessionName, cart);
			message = Configure.SUCCESS;
		} else if ("buy".equals(action) || "appointment".equals(action)) {
			String numStr = params.get("num");
			int num = Integer.parseInt(numStr);
			String memberName = params.get("memberName");

			Member member = memberService.getMemberInfo(memberName);
			String memberId = member.getMemberId();

			DessertVO dessert = dessertService.getDessertById(dessertId);
			double price = dessert.getPrice();
			String payMethod = Configure.CARD;// 默认是卡支付

			double money = price * num;

			ConsumeRecord consumeRecord = new ConsumeRecord();
			consumeRecord.setDate(TimeUtil.getCurrentTime());
			consumeRecord.setDessertId(dessertId);
			consumeRecord.setMemberId(memberId);
			consumeRecord.setNum(num);
			consumeRecord.setMoney(money);
			consumeRecord.setAction(action);

			if (Configure.CASH.equals(payMethod)) {
				consumeRecord.setCash(true);
			} else {
				consumeRecord.setCash(false);
			}

			HashMap<String, Object> result = dessertService
					.consume(consumeRecord);
			message = (String) result.get("msg");
			discount = (double) result.get("discount");
			point = (int) result.get("point");

			if (Configure.SUCCESS.equals(message)) {
				cart.delete(dessertId);
				session().setAttribute(sessionName, cart);
			}
		} else if ("clear".equals(action)) {
			cart.clear();
			session().setAttribute(sessionName, cart);
			message = Configure.SUCCESS;
		}

		return Configure.SUCCESS;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

}
