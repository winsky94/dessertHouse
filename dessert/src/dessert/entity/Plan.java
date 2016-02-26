package dessert.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private int checked;// 经理是否审批了，-1：待审；0，不过；1，通过
	private String validSunday;// 记录开始生效的那一周的周日

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

	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}

	public String getValidSunday() {
		return validSunday;
	}

	public void setValidSunday(String validSunday) {
		this.validSunday = validSunday;
	}
}
