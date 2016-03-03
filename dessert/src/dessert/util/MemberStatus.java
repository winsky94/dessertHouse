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
		case "Init":
			status = Init;
			break;
		case "OK":
			status = OK;
			break;
		case "pause":
			status = pause;
			break;
		case "over":
			status = over;
			break;
		default:
			break;
		}

		return status;
	}
}
