package dessert.VO;

import dessert.configure.Configure;
import dessert.util.UserType;

/**
 * @author 严顺宽
 */
public class WorkerVO {
	public long id;
	private String workerId; // 工号
	private String password; // 密码
	private String lastLoadTime;// 上次登录时间
	private UserType type;// 员工类型
	private String owingTo;// 归属哪个分店 Configure.WAITRESS表明是总店服务员，

	public WorkerVO(long id, String workerId, String password,
			String lastLoadTime, UserType type, String owingTo) {
		super();
		this.id = id;
		this.workerId = workerId;
		this.password = password;
		this.lastLoadTime = lastLoadTime;
		this.type = type;
		this.owingTo = owingTo;
	}

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

	/**
	 * 职位，转为中文到界面上显示
	 * 
	 * @return
	 */
	public String getType() {
		return UserType.getChinese(type);
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public String getOwingTo() {
		if (owingTo.equals(Configure.WAITRESS)) {
			return "-";
		} else {
			return owingTo;
		}
	}

	public void setOwingTo(String owingTo) {
		this.owingTo = owingTo;
	}
}
