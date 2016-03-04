package dessert.VO;

/**
 * @author 严顺宽
 * @date 2016年3月4日 上午9:53:00
 */
public class ConsumeVO {
	public long id;
	private String memberId;// 会员的七位识别码
	private String dessertName;// 消费的商品的编号
	private int num;// 购买数量
	private String date;// 消费日期
	private double money;// 消费金额
	private String action;// 行为：预约：appointment or 购买：buy
	private boolean cash;// 是否是现金支付，现金or从会员账户扣钱
	private double discount;// 优惠
	private int point;// 累积积分

	public ConsumeVO(long id, String memberId, String dessertName, int num,
			String date, double money, String action, boolean cash,
			double discount, int point) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.dessertName = dessertName;
		this.num = num;
		this.date = date;
		this.money = money;
		this.action = action;
		this.cash = cash;
		this.discount = discount;
		this.point = point;
	}

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

	public String getDessertName() {
		return dessertName;
	}

	public void setDessertName(String dessertName) {
		this.dessertName = dessertName;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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
