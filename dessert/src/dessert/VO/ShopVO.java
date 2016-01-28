package dessert.VO;

/**
 * @author 严顺宽
 */
public class ShopVO {
	private long id;
	private String name;// 店面名称
	private String address;// 地址
	private String owner;// 拥有者
	private String telephone;// 联系电话

	public ShopVO(long id, String name, String address,
			String owner, String telephone) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.owner = owner;
		this.telephone = telephone;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
