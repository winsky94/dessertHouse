package dessert.controller.html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.DessertVO;
import dessert.VO.PlanVO;
import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.service.DessertService;
import dessert.service.PlanService;
import dessert.util.Week;

/**
 * @author 严顺宽
 */
public class PlanController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	public PlanService planService;
	@Autowired
	public DessertService dessertService;

	@SuppressWarnings("unchecked")
	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		String shopName = params.get("shopName");
		String day = params.get("day");
		String action = params.get("action");
		Map<Week, ArrayList<String>> plan = null;
		if ("create".equals(action)) {
			// 总店服务员创建计划
			plan = (Map<Week, ArrayList<String>>) session().getAttribute(
					Configure.PLAN_SHOP_ALL);
			session().removeAttribute(Configure.PLAN_ID);

		} else if ("view".equals(action) || "update".equals(action)
				|| "approve".equals(action) || "approveView".equals(action)) {
			// 因为这儿老是从数据库获取，所以加不进来！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
			PlanVO planVO = planService.getPlans(shopName, false);
			if (planVO == null) {
				plan = null;
			} else {
				HashMap<Week, ArrayList<String>> result = planVO.getPlans();
				// 这儿要处理一下，修改的时候，要加新增的商品
				if ("update".equals(action)) {
					HashMap<Week, ArrayList<String>> updateAdd = (HashMap<Week, ArrayList<String>>) session()
							.getAttribute(Configure.UPDATE_ADD_SESSION);
					if (updateAdd != null) {
						for (Week key : result.keySet()) {
							ArrayList<String> newAdd = updateAdd.get(key);
							if (newAdd != null) {
								ArrayList<String> updateResult = result
										.get(key);
								//因为newadd中保存的是增加后的值
								updateResult=newAdd;
								result.put(key, updateResult);
							}
						}
					}
				}

				long planId = planVO.getId();
				session().setAttribute(Configure.PLAN_ID, planId);
				session().setAttribute(Configure.PLAN_SHOP_ALL, result);
				plan = result;
			}
		}
		System.out.println("plancontroller....");
		if (plan != null) {
			session().removeAttribute(Configure.PLAN_SESSION);
			for (Week key : plan.keySet()) {
				if (Week.toString(key).equals(day)) {
					ArrayList<String> dessertNames = plan.get(key);
					ArrayList<DessertVO> desserts = dessertService
							.getDessertByName(dessertNames);

					if (desserts != null) {
						session()
								.setAttribute(Configure.PLAN_SESSION, desserts);
					}
					break;
				}
			}

		} else {
			// 假数据，实际不要处理
			plan = new HashMap<Week, ArrayList<String>>();
			session().removeAttribute(Configure.PLAN_ID);
			session().setAttribute(Configure.PLAN_SHOP_ALL, plan);
			// ArrayList<DessertVO> desserts = new ArrayList<DessertVO>();
			// Dessert dessert = new Dessert();
			// dessert.setId(1);
			// dessert.setName("测试名称");
			// dessert.setOwingTo("shop1");
			// dessert.setPath("image/desserts/shop1/2.jpg");
			// dessert.setPrice(10);
			// dessert.setStockNum(1);
			// dessert.setDate(DateUtil.getToday());
			// desserts.add(ConvertVO.dessertToVO(dessert));
			// session().setAttribute(Configure.PLAN_SESSION, desserts);
		}

		return Configure.ZD_WAITRESS;
	}
}
