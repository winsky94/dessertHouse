package dessert.util;

/**
 * @author 严顺宽
 */
public enum UserType {
	error, // 出错了，身份不明
	member, // 会员
	waitress, // 分店服务员
	waitressManager, // 总店服务员
	admin, // 系统管理员
	manager; // 经理

	/**
	 * 根据枚举值，得到其对应的中文职位
	 * 
	 * @param type
	 * @return
	 */
	public static String getChinese(UserType type) {
		String result = "";
		switch (type) {
		case member:
			result = "会员";
			break;
		case waitress:
			result = "分店服务员";
			break;
		case waitressManager:
			result = "总店服务员";
			break;
		case admin:
			result = "系统管理员";
			break;
		case manager:
			result = "经理";
			break;
		default:
			break;
		}
		return result;
	}
}
