package dessert.controller.html;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.entity.Shop;
import dessert.entity.Worker;
import dessert.service.PlanService;
import dessert.service.ShopService;
import dessert.service.TestService;
import dessert.service.WorkerService;
import dessert.util.UserType;

public class TestController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	public TestService testService;

	@Autowired
	public ShopService shopService;

	@Autowired
	public WorkerService workerService;

	@Autowired
	public PlanService planService;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub

		// initShops();

		// initWorker();

		// PlanVO planVO = planService.getPlans("shop2", false);
		// if (planVO == null) {
		// System.out.println("尚未制定计划");
		// } else {
		// HashMap<Week, ArrayList<String>> result = planVO.getPlans();
		// for (Week key : result.keySet()) {
		// ArrayList<String> t = result.get(key);
		// System.out.println(Week.toString(key));
		// if (t != null) {
		// for (String string : t) {
		// System.out.println(string);
		// }
		// }
		// System.out.println("=======================");
		// }
		// }
		return Configure.SUCCESS;
	}

	public void initShops() {
		for (int i = 0; i < 26; i++) {
			Shop shop = new Shop();
			shop.setName("shop" + (i + 1));
			shop.setAddress("江苏省南京市鼓楼区汉口路22号");
			shop.setOwner("严顺宽");
			shop.setTelephone("18013878510");
			shopService.add(shop);
		}
	}

	public void initWorker() {
		Worker worker = new Worker();
		worker.setOwingTo(Configure.ADMIN);
		worker.setPassword("12345a");
		worker.setType(UserType.admin);
		String workerId = workerService.generateWorkerId(UserType.admin);
		worker.setWorkerId(workerId);
		workerService.add(worker);

		worker = new Worker();
		worker.setOwingTo(Configure.MANAGER);
		worker.setPassword("12345a");
		worker.setType(UserType.manager);
		String workerId2 = workerService.generateWorkerId(UserType.manager);
		worker.setWorkerId(workerId2);
		workerService.add(worker);
	}

}
