package dessert.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 服务员
 * 
 * @author 严顺宽
 */
@Entity(name = "waitress")
public class Waitress {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	private String waitressId; // 工号
	private String password; // 密码
	private String lastLoadTime;// 上次登录时间
	private String owingTo;// 归属哪个分店 Configure.WAITRESS表明是总店服务员

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getWaitressId() {
		return waitressId;
	}

	public void setWaitressId(String waitressId) {
		this.waitressId = waitressId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastLoadTime() {
		return lastLoadTime;
	}

	public void setLastLoadTime(String lastLoadTime) {
		this.lastLoadTime = lastLoadTime;
	}

	public String getOwingTo() {
		return owingTo;
	}

	public void setOwingTo(String owingTo) {
		this.owingTo = owingTo;
	}

}
