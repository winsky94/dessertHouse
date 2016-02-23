package dessert.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import dessert.util.Week;

/**
 * 甜点商品
 * 
 * @author 严顺宽
 */
@Entity(name = "dessert")
public class Dessert {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	private String name;// 商品名
	private double price;// 售价
	private int stockNum;// 库存量
	private String path;// 图片地址
	private String owingTo;// 是哪个商店的商品，店名
	private Week weekDay;// 周几的产品
	private String date;// 当天的日期

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
