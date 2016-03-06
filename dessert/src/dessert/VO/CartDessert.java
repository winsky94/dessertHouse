package dessert.VO;

import java.io.Serializable;

import dessert.util.Week;

/**
 * @author 严顺宽
 * @date 2016年3月6日 下午8:12:04
 */
public class CartDessert implements Serializable {
	private static final long serialVersionUID = 1L;
	public long id;
	private String name;// 商品名
	private double price;// 售价
	private int StockNum;// 库存量
	private int num;// 购买
	private String path;// 图片地址
	private String owingTo;// 是哪个商店的商品，店名
	private Week weekDay;// 周几的产品
	private String date;// 当天的日期

	public CartDessert(long id, String name, double price, int stockNum,
			int num, String path, String owingTo, Week weekDay, String date) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		StockNum = stockNum;
		this.num = num;
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
		return StockNum;
	}

	public void setStockNum(int stockNum) {
		StockNum = stockNum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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
