package dessert.VO;

/**
 * 会员喜好，喜欢在哪个商店买什么商品最多
 * 
 * @author 严顺宽
 * @date 2016年3月6日 下午4:28:46
 */
public class Favorite {
	private String memberId;
	private String memberName;
	private String dessertId;
	private String dessertName;
	private String owingTo;
	private int num;
	private double money;

	public Favorite(String memberId, String memberName, String dessertId,
			String dessertName, String owingTo, int num, double money) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.dessertId = dessertId;
		this.dessertName = dessertName;
		this.owingTo = owingTo;
		this.num = num;
		this.money = money;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getDessertId() {
		return dessertId;
	}

	public void setDessertId(String dessertId) {
		this.dessertId = dessertId;
	}

	public String getDessertName() {
		return dessertName;
	}

	public void setDessertName(String dessertName) {
		this.dessertName = dessertName;
	}

	public String getOwingTo() {
		return owingTo;
	}

	public void setOwingTo(String owingTo) {
		this.owingTo = owingTo;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
}
