package dessert.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import dessert.util.UserType;

/**
 * 服务员
 * 
 * @author 严顺宽
 */
@Entity(name = "worker")
public class Worker {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	private String workerId; // 工号
	private String password; // 密码
	private String lastLoadTime;// 上次登录时间
	private UserType type;// 员工类型
	private String owingTo;// 归属哪个分店 Configure.WAITRESS表明是总店服务员，其余指向店面名称

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getWorkerId() {
		return workerId;
	}

	public void setWorkerId(String workerId) {
		this.workerId = workerId;
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

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public String getOwingTo() {
		return owingTo;
	}

	public void setOwingTo(String owingTo) {
		this.owingTo = owingTo;
	}
}
