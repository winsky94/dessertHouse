package dessert.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 会员卡充值记录
 * 
 * @author 严顺宽
 * @date 2016年1月18日 下午4:03:34
 */
@Entity(name = "rechargeRecord")
public class RechargeRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	private String memberId;// 会员的七位识别码
	private double money;// 充值钱数
	private String date;// 充值日期

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
