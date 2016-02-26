package dessert.VO;

import java.util.ArrayList;
import java.util.HashMap;

import dessert.util.Week;

/**
 * @author 严顺宽
 */
public class PlanVO {
	public long id;
	private String shop;// 店名
	private String createAt;// 创建日期
	private HashMap<Week, ArrayList<String>> plans = new HashMap<Week, ArrayList<String>>();
	private boolean checked;// 经理是否审批了
	private String validSunday;// 记录开始生效的那一周的周日

	public PlanVO() {

	}

	public PlanVO(long id, String shop, String createAt,
			HashMap<Week, ArrayList<String>> plans, boolean checked,
			String validSunday) {
		super();
		this.id = id;
		this.shop = shop;
		this.createAt = createAt;
		this.plans = plans;
		this.checked = checked;
	}

	public String getValidSunday() {
		return validSunday;
	}

	public void setValidSunday(String validSunday) {
		this.validSunday = validSunday;
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

	public HashMap<Week, ArrayList<String>> getPlans() {
		return plans;
	}

	public void setPlans(HashMap<Week, ArrayList<String>> plans) {
		this.plans = plans;
	}

	public void setPlanByDay(Week week, ArrayList<String> plan) {
		this.plans.put(week, plan);
	}

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
