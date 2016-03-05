package dessert.VO;

/**
 * @author 严顺宽
 * @date 2016年3月5日 下午8:40:35
 */
public class ConsumeStats {
	private String shopName;
	private int appointmentNum;
	private double appointmentMoney;
	private int saleNum;
	private double saleMoney;

	public ConsumeStats(String shopName, int appointmentNum,
			double appointmentMoney, int saleNum, double saleMoney) {
		super();
		this.shopName = shopName;
		this.appointmentNum = appointmentNum;
		this.appointmentMoney = appointmentMoney;
		this.saleNum = saleNum;
		this.saleMoney = saleMoney;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public int getAppointmentNum() {
		return appointmentNum;
	}

	public void setAppointmentNum(int appointmentNum) {
		this.appointmentNum = appointmentNum;
	}

	public double getAppointmentMoney() {
		return appointmentMoney;
	}

	public void setAppointmentMoney(double appointmentMoney) {
		this.appointmentMoney = appointmentMoney;
	}

	public int getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(int saleNum) {
		this.saleNum = saleNum;
	}

	public double getSaleMoney() {
		return saleMoney;
	}

	public void setSaleMoney(double saleMoney) {
		this.saleMoney = saleMoney;
	}
}
