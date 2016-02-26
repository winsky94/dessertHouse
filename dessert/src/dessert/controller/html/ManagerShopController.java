package dessert.controller.html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.PlanVO;
import dessert.VO.ShopVO;
import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.service.PlanService;
import dessert.service.ShopService;

/**
 * @author 严顺宽
 */
public class ManagerShopController extends BaseController {
	private static final long serialVersionUID = 1L;

	@Autowired
	public ShopService shopService;
	@Autowired
	public PlanService planService;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		// 删除session，免得混淆其他店面的
		session().removeAttribute(Configure.PLAN_SHOP_ALL);
		session().removeAttribute(Configure.PLAN_SESSION);

		ArrayList<ShopVO> shops = new ArrayList<ShopVO>();
		shops = shopService.getShops();
		session().setAttribute(Configure.SHOP_SESSION, shops);
		HashMap<String, String> result = new HashMap<String, String>();// key值为店名，value形如：制定-待审
		boolean current = false;// 查看有没有制定下一周的计划
		if (shops != null) {
			for (ShopVO vo : shops) {
				String shopName = vo.getName();
				PlanVO planVO = planService.getPlans(shopName, current);
				String value = getStatus(planVO);
				result.put(shopName, value);
			}
		}
		session().setAttribute(Configure.SHOP_PLAN_SESSION, result);

		return Configure.MANAGER_SHOP_LIST;
	}

	/**
	 * 根据planVO的情况，拼接网页显示销售计划的制定情况
	 * 
	 * @param vo
	 * @return
	 */
	private String getStatus(PlanVO vo) {
		String result = "";

		if (vo == null) {
			result = "待定-待审";
		} else {
			result = "完成-";
			int checked = vo.getChecked();
			if (checked == Configure.PLAN_PASS) {
				result += "通过";
			} else if (checked == Configure.PLAN_FAIL) {
				result += "不过";
			} else if (checked == Configure.PLAN_READY) {
				result += "待审";
			}
		}
		return result;
	}

}
