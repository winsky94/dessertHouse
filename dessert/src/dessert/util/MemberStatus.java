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
	
	public static int getStatusInt(MemberStatus status){
		int result=0;
		switch (status) {
		case Init:
			result=0;
			break;
		case OK:
			result=1;
			break;
		case pause:
			result=2;
			break;
		case over:
			result=3;
			break;
		default:
			break;
		}
		return result;
	}
}
