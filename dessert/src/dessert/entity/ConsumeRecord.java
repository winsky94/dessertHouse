package dessert.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 消费记录
 * 
 * @author 严顺宽
 * @date 2016年1月18日 下午3:59:42
 */
@Entity(name = "consumeRecord")
public class ConsumeRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	private String userId;// 会员的七位识别码
	private String date;// 消费日期
	private double money;// 消费金额
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}

}
