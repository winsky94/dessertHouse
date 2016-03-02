package dessert.util;

/**
 * @author 严顺宽
 */
public enum MemberStatus {
	Init, // 刚注册用户
	OK, // 正常
	pause, // 暂停会员资格
	over;// 停止会员资格

	public static MemberStatus getStatus(String type) {
		MemberStatus status = null;
		switch (type) {
		case "0":
			status = Init;
			break;
		case "1":
			status = OK;
			break;
		case "2":
			status = pause;
			break;
		case "3":
			status = over;
			break;
		default:
			break;
		}

		return status;
	}
}
