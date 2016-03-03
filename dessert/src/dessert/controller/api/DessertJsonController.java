package dessert.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.DessertVO;
import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.entity.ConsumeRecord;
import dessert.entity.Member;
import dessert.service.DessertService;
import dessert.service.MemberService;
import dessert.util.TimeUtil;

/**
 * @author 严顺宽
 */
public class DessertJsonController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	private DessertService dessertService;
	@Autowired
	private MemberService memberService;
	private ArrayList<DessertVO> desserts = new ArrayList<DessertVO>();
	private String message;
	private double discount;
	private int point;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		desserts = dessertService.getAllDesserts();
		return Configure.SUCCESS;
	}

	public String consume() {
		message = "";
		discount = 0;
		point = 0;

		Map<String, String> params = getParams();

		try {
			long dessertId = Long.parseLong(params.get("dessertId"));
			String memberName = params.get("memberName");
			int num = Integer.parseInt(params.get("num"));
			double price = Double.parseDouble(params.get("price"));
			String action = params.get("action");
			String payMethod = params.get("payMethod");

			Member member = memberService.getMemberInfo(memberName);
			String memberId = member.getMemberId();
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

		} catch (Exception e) {
			// TODO: handle exception
			message = Configure.ERROR;
		} finally {
		}
		return Configure.SUCCESS;
	}

	public ArrayList<DessertVO> getDesserts() {
		return desserts;
	}

	public void setDesserts(ArrayList<DessertVO> desserts) {
		this.desserts = desserts;
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
