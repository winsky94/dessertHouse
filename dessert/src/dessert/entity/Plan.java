package dessert.entity;

import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import dessert.util.Week;

/**
 * 销售计划
 * 
 * @author 严顺宽
 */
@Entity(name = "plan")
public class Plan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	private String shop;// 店名
	private String createAt;// 创建日期
	private HashMap<Week, ArrayList<Dessert>> plans = new HashMap<Week, ArrayList<Dessert>>();
	private boolean checked;//经理是否审批了

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

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
