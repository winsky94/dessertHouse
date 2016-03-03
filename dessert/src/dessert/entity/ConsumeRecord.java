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
	private String memberId;// 会员的七位识别码
	private long dessertId;// 消费的商品的编号
	private int num;// 购买数量
	private String date;// 消费日期
	private double money;// 消费金额
	private String action;// 行为：预约：appointment or 购买：buy
	private boolean cash;// 是否是现金支付，现金or从会员账户扣钱

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getDessertId() {
		return dessertId;
	}

	public void setDessertId(long dessertId) {
		this.dessertId = dessertId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberId() {
		return memberId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public boolean getCash() {
		return cash;
	}

	public void setCash(boolean cash) {
		this.cash = cash;
	}

}
