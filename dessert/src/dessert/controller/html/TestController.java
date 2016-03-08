package dessert.controller.html;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.entity.Member;
import dessert.entity.RechargeRecord;
import dessert.entity.Shop;
import dessert.entity.Worker;
import dessert.service.DessertService;
import dessert.service.MemberService;
import dessert.service.PlanService;
import dessert.service.ShopService;
import dessert.service.TestService;
import dessert.service.WorkerService;
import dessert.util.DateUtil;
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
	@Autowired
	public DessertService dessertService;
	@Autowired
	private MemberService memberService;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub

		// Dessert dessert = dessertService.getDessertById(17);
		// System.out.println(dessert.getName());

		// initShops();

		// initWorker();

		List<RechargeRecord> records=memberService.getRechargeRecord("2016", "03");
		for(RechargeRecord rechargeRecord:records){
			System.out.println(rechargeRecord.getId());
		}
		
		// initMember();

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
		for (int i = 0; i < 14; i++) {
			Shop shop = new Shop();
			shop.setName("shop" + (i + 1));
			shop.setAddress("江苏省南京市鼓楼区汉口路22号");
			shop.setOwner("小马甲");
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

	public void initMember() {
		for (int i = 1; i < 100; i++) {
			String userName = "member" + i;
			String password = "12345a";
			int age = (int) (Math.random() * 100);
			double d = Math.random();
			String sex = "女";
			if (d >= 0.5) {
				sex = "男";
			}
			String birthday = (2016 - age) + "-" + (int) (Math.random() * 12)
					+ "-" + (int) (Math.random() * 30);
			String telephone = "18013878510";
			String cardId = "1234567890098765432";
			String prov = "江苏";
			String city = "南京";
			String dist = "鼓楼区";

			Member member = new Member();
			member.setName(userName);
			member.setPassword(password);
			member.setAge(age);
			member.setSex(sex);
			member.setBirthday(birthday);
			member.setTelephone(telephone);
			member.setCardId(cardId);
			member.setProv(prov);
			member.setCity(city);
			member.setDist(dist);
			String createAt = DateUtil.getToday();
			member.setCreateAt(createAt);

			String message = memberService.signUp(member);
			System.out.println(message);
		}

	}
}
