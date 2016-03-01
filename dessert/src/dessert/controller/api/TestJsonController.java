package dessert.controller.api;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.DessertVO;
import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.service.PlanService;
import dessert.service.TestService;
import dessert.util.DateUtil;
import dessert.util.Week;

public class TestJsonController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	private TestService testService;
	@Autowired
	private PlanService planService;
	private Map<String, Week> weeks = new LinkedHashMap<String, Week>();
	public int a;
	private ArrayList<DessertVO> desserts = new ArrayList<DessertVO>();

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		// System.out.println(testService.test());
		a = 1;
		weeks = DateUtil.getNextSevenDay();
//		desserts=planService.getDayDessert("2016-02-28", "Sunday", "shop1");
		return Configure.SUCCESS;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public Map<String, Week> getWeeks() {
		return weeks;
	}

	public void setResult(Map<String, Week> weeks) {
		this.weeks = weeks;
	}

	public ArrayList<DessertVO> getDesserts() {
		return desserts;
	}

	public void setDesserts(ArrayList<DessertVO> desserts) {
		this.desserts = desserts;
	}

}
