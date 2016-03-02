package dessert.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import dessert.util.MemberStatus;

/**
 * 会员
 * 
 * @author 严顺宽
 */
@Entity(name = "member")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	private String memberId;// 会员的七位识别码
	private String password;// 密码
	private String name;// 会员名称
	private String birthday;// 生日
	private int age;// 年龄
	private String sex;// 性别
	private String telephone;// 电话
	private int rank = 1;// 会员级别
	private String cardId;// 银行卡账号
	private MemberStatus status = MemberStatus.Init;// 会员卡状态
	private int point = 0;// 消费积分
	private String lastLoadTime;// 上次登录时间
	private double validMoney;// 会员卡上余额
	private String validDate;// 会员有效期

	public String getLastLoadTime() {
		return lastLoadTime;
	}

	public void setLastLoadTime(String lastLoadTime) {
		this.lastLoadTime = lastLoadTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public MemberStatus getStatus() {
		return status;
	}

	public void setStatus(MemberStatus status) {
		this.status = status;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public double getValidMoney() {
		return validMoney;
	}

	public void setValidMoney(double validMoney) {
		this.validMoney = validMoney;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

}
