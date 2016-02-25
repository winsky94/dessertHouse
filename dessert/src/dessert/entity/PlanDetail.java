package dessert.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 销售计划的详细记录
 * 
 * @author 严顺宽
 */
@Entity(name = "planDetail")
public class PlanDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	private String weekDay;//对应于哪一天的，星期几
	private String dessertName;// 对应dessert表中的id
	private long planId;// 对应存储是哪个plan
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}
	public String getDessertName() {
		return dessertName;
	}
	public void setDessertName(String dessertName) {
		this.dessertName = dessertName;
	}
	public long getPlanId() {
		return planId;
	}
	public void setPlanId(long planId) {
		this.planId = planId;
	}

}
