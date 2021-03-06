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

	// ==================================注册===========================================
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
	// 修改密码
	public static final String WRONG_ORIGINAL_PW = "原密码错误，请重新输入";

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
	// 文件上传位置
	public static final String FOLDER = "E:\\本科课程\\6.大三二\\J2EE与中间件\\Lab\\dessert\\WebContent\\image\\desserts";
	// 服务员上传的商品名称已经存在
	public static final String Dessert_EXIST = "该名称的商品已存在";
	// 管理员管理服务员的session名
	public static final String PLAN_SESSION = "plan_session";
	public static final String ZD_WAITRESS = "ZD_waitress";
	// 某个店的全部商品计划session，map的value为商品名列表
	public static final String PLAN_SHOP_ALL = "plan";
	public static final String ZD_SHOP = "ZD_shop";
	// 记录店面下周计划的情况，hashmap，key 为 店名，value 为结果，形如"制定-待审";
	public static final String SHOP_PLAN_SESSION = "shop_plan_session";
	public static final int PLAN_READY = -1;// 计划待审
	public static final int PLAN_PASS = 1;// 计划审核通过
	public static final int PLAN_FAIL = 0;// 计划审核不过
	public static final String PLAN_ID = "planId";// 展示的plan在数据库中的id
	// 查看会员消费记录
	public static final String WAITRESS_CONSUME_SESSION = "waitress_consume_session";// session
	public static final String WAITRESS_CONSUME = "waitress_consume";// 页面跳转
	// 查看会员充值记录
	public static final String WAITRESS_RECHARGE_SESSION = "waitress_recharge_session";// session
	public static final String WAITRESS_RECHARGE = "waitress_recharge";// 页面跳转

	// ==================================经理===========================================
	public static final String MANAGER_SHOP_LIST = "manager shop list";
	// 会员消费记录
	public static final String MANAGER_CONSUME_SESSION = "manager_consume_session";// session
	public static final String MANAGER_CONSUME = "manager_consume";// 页面跳转
	// 会员充值记录
	public static final String MANAGER_RECHARGE_SESSION = "manager_recharge_session";// session
	public static final String MANAGER_RECHARGE = "manager_recharge";// 页面跳转
	// 店面业绩
	public static final String MANAGER_SALE_SESSION = "manager_sale_session";// session
	public static final String MANAGER_SALE = "manager_sale";// 页面跳转
	// 热卖产品
	public static final String MANAGER_HOT_DESSERT = "manager_hot_dessert";// 页面跳转

	// ==================================会员===========================================
	public static final String DESSERT_DETAIL = "dessert_detail";
	public static final String DESSERT_DETAIL_NOT_FOUND = "dessert_detail_not_found";
	// 商品详情界面的商品session
	public static final String DESSERT_DETAIL_SESSION = "dessert_detail_session";
	// 会员查看店面列表
	public static final String MEMBER_SHOP = "member_shop";
	// 会员资格管理
	public static final String MEMBER_INFO = "member_info";
	public static final String MEMBER_INIT_MES = "您的会员资格尚未激活，请尽快充值>=200元以激活";
	public static final String MEMBER_OK_MES = "状态正常";
	public static final String MEMBER_PAUSE_MES = "您的会员资格已暂停，请尽快充值以恢复";
	public static final String MEMBER_OVER_MES = "状态停止";
	public static final String POINT_NOT_ENOUGH = "卡积分不足，兑换失败";
	// 会员消费行为
	public static final String APPOINTMENT = "appointment";// 预约
	public static final String BUY = "buy";// 购买
	public static final String APPOINTMENT_CN = "预约";// 预约
	public static final String BUY_CN = "购买";// 购买
	// 会员付费行为
	public static final String CASH = "cash";// 现金付费
	public static final String CARD = "card";// 会员账户余额
	public static final String CASH_CN = "现金";// 现金付费
	public static final String CARD_CN = "刷卡";//
	public static final String CARD_NOT_ENOUGH = "账户余额不足，请至相应店面使用现金支付~~";// 会员账户余额
	public static final String STOCK_NOT_ENOUGH = "库存不足，下次早点来哦~~";// 会员账户余额
	// 会员消费记录
	public static final String Member_CONSUME_SESSION = "member_consume_session";// session
	public static final String Member_CONSUME = "member_consume";// 页面跳转
	// 会员充值记录
	public static final String Member_RECHARGE_SESSION = "member_recharge_session";// session
	public static final String Member_RECHARGE = "member_recharge";// 页面跳转
	// 购物车
	public static final String Member_CART_SESSION = "member_cart_session";// session
	public static final String Member_CART = "member_cart";// 页面跳转
	public static final String Member_EMPTY_CART = "member_empty_cart";// 页面跳转
	// 浏览商品
	public static final String All_DESSERT_SESSION = "all_dessert_session";
	public static final String All_DESSERT = "all_dessert";
	public static final String SHOP_DESSERT_SESSION = "shop_dessert_session";
	public static final String SHOP_DESSERT = "shop_dessert";

	
	//总店服务员修改计划
	public static final String UPDATE_ADD_SESSION = "update_add_session";
}
