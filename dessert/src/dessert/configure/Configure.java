package dessert.configure;

public class Configure {
	/*
	 * **************************************** common value
	 * ****************************************
	 */
	// delete_flag
	public static final int DELETE_FLAG_FALSE = 0;
	public static final int DELETE_FLAG_TRUE = 1;
	// return
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";

	// 系统管理员跳转页面
	public static final String ADMIN_SHOP = "admin_shop";

	// 密码加密密钥
	public static final String KEY = "dessert";

	// 注册的各种错误
	public static final String AGE_ERROR = "您输入的年龄不是合法数字";
	public static final String NAME_ERROR = "用户名已被占用";

	// 规定会员号的位数
	public static final String STR_FORMAT = "0000000";

	// 总店服务员的身份，用0表示，其余分店服务员的指向对应店铺的shopId
	public static final String WAITRESS = "0";
	// 系统管理员身份，用户-1表示
	public static final String ADMIN = "-1";

	// 管理员总店的session名
	public static final String SHOP_SESSION = "shop_session";

}
