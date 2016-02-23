package dessert.VO;

import dessert.util.Week;

/**
 * @author 严顺宽
 */
public class DessertVO {
	public long id;
	private String name;// 商品名
	private double price;// 售价
	private int stockNum;// 库存量
	private String path;// 图片地址
	private String owingTo;// 是哪个商店的商品，店名
	private Week weekDay;// 周几的产品
	private String date;// 当天的日期

	

	public DessertVO(long id, String name, double price, int stockNum,
			String path, String owingTo, Week weekDay, String date) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.stockNum = stockNum;
		this.path = path;
		this.owingTo = owingTo;
		this.weekDay = weekDay;
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStockNum() {
		return stockNum;
	}

	public void setStockNum(int stockNum) {
		this.stockNum = stockNum;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getOwingTo() {
		return owingTo;
	}

	public void setOwingTo(String owingTo) {
		this.owingTo = owingTo;
	}

	public Week getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(Week weekDay) {
		this.weekDay = weekDay;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
