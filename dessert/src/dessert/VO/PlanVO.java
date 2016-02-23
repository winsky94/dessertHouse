package dessert.VO;

import java.util.ArrayList;
import java.util.HashMap;

import dessert.entity.Dessert;
import dessert.util.Week;

/**
 * @author 严顺宽
 */
public class PlanVO {
	public long id;
	private String shop;// 店名
	private String createAt;// 创建日期
	private HashMap<Week, ArrayList<Dessert>> plans = new HashMap<Week, ArrayList<Dessert>>();

	public PlanVO(long id, String shop, String createAt,
			HashMap<Week, ArrayList<Dessert>> plans) {
		super();
		this.id = id;
		this.shop = shop;
		this.createAt = createAt;
		this.plans = plans;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public HashMap<Week, ArrayList<Dessert>> getPlans() {
		return plans;
	}

	public void setPlans(HashMap<Week, ArrayList<Dessert>> plans) {
		this.plans = plans;
	}

	public void setPlanByDay(Week week, ArrayList<Dessert> plan) {
		this.plans.put(week, plan);
	}
}
