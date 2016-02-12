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

	// ==================================会员===========================================
	// 密码加密密钥
	public static final String KEY = "dessert";
	// 会员注册的各种错误
	public static final String AGE_ERROR = "您输入的年龄不是合法数字";
	public static final String NAME_ERROR = "用户名已被占用";
	// 会员注册用户名存在
	public static final String MEMBER_EXIST = "该名称已被占用";
	// 规定会员号的位数
	public static final String STR_FORMAT = "0000000";

	// ==================================员工===========================================
	// 总店服务员的身份，用0表示，其余分店服务员的指向对应店铺的店名
	public static final String WAITRESS = "waitressManager";
	// 系统管理员身份，指向店铺用-1表示
	public static final String ADMIN = "admin";
	// 总经理身份，指向店铺用-2表示
	public static final String MANAGER = "manager";

	// ==================================管理员==========================================
	// 管理员管理店面的session名
	public static final String SHOP_SESSION = "shop_session";
	// 管理员管理服务员的session名
	public static final String WAITRESS_SESSION = "waitress	_session";
	// 系统管理员跳转页面
	public static final String ADMIN_SHOP = "admin_shop";
	public static final String ADMIN_WAITRESS = "admin_waitress";

	// ==================================店面===========================================
	// 店名存在
	public static final long SHOP_EXIST = -1;
	public static final String SHOP_EXIST_MESSAGE = "该店名已存在";
	public static final String SHOP_SUCCESS_ACTION = "success";

	// ==================================服务员==========================================
	public static final String DEFAULT_PASSWORD = "12345a";
	public static final String WAITRESS_SUCCESS_ACTION = "success";
	public static final String WAITRESS_OWINGTO_EXIST = "所属店面存在";
	public static final String WAITRESS_OWINGTO_FAIL = "所属店面不存在";

}
