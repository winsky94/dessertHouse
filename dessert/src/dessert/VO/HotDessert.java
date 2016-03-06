package dessert.VO;

/**
 * 热销商品
 * 
 * @author 严顺宽
 * @date 2016年3月6日 下午3:47:32
 */
public class HotDessert {
	private String dessertId;
	private String dessertName;
	private String owingTo;
	private int totalNum;
	private double totalMoney;

	public HotDessert(String dessertId, String dessertName, String owingTo,
			int totalNum, double totalMoney) {
		super();
		this.dessertId = dessertId;
		this.dessertName = dessertName;
		this.owingTo = owingTo;
		this.totalNum = totalNum;
		this.totalMoney = totalMoney;
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

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

}
