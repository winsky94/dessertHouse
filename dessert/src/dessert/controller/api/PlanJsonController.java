package dessert.controller.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;

import dessert.VO.PlanVO;
import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.entity.Dessert;
import dessert.entity.Plan;
import dessert.service.DessertService;
import dessert.service.PlanService;
import dessert.util.DateUtil;
import dessert.util.JsonUtil;
import dessert.util.Week;

/**
 * @author 严顺宽
 */
public class PlanJsonController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	public PlanService planService;
	@Autowired
	public DessertService dessertService;
	private String message;
	private HashMap<String, ArrayList<Dessert>> plans;
	private String plansJson;

	/*
	 * 下面的uploadFiles有两点需要注意 1、必须是List数组
	 * 2、命名必须跟jsp页面中的uploadify的一个参数"fileObjName"一致，否则无法获取到上传的文件
	 */
	public List<File> uploadFiles;
	/*
	 * 下面的两个属性为使用Action上传文件所必须的，在前台jsp页面无需做任何操作
	 */
	public List<String> uploadFilesFileName;
	public List<String> uploadFilesContentType;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		message = "";
		plans = new HashMap<String, ArrayList<Dessert>>();

		String shopName = params.get("shopName");
		PlanVO planVO = planService.getPlans(shopName);
		HttpSession session = session();
		session.setAttribute(Configure.PLAN_SESSION, planVO);
		return Configure.SUCCESS;
	}

	public String checkExist() {
		message = "";
		plans = new HashMap<String, ArrayList<Dessert>>();

		Map<String, String> params = getParams();
		String name = params.get("name");
		boolean exist = dessertService.checkExist(name);
		if (exist) {
			message = Configure.Dessert_EXIST;
		}
		return Configure.SUCCESS;
	}

	public String addPlan() {
		message = "";
		plans = new HashMap<String, ArrayList<Dessert>>();

		Map<String, String> params = getParams();
		String owingTo = params.get("owingTo");
		String name = params.get("name");
		double price = Double.parseDouble(params.get("price"));
		int stockNum = Integer.parseInt(params.get("stockNum"));
		String day = params.get("day");
		String date = DateUtil.getDate(day);
		Week weekDay = Week.getWeek(DateUtil.getNumByDayEn(day));

		// 复制文件
		if (uploadFiles != null) {
			String folder = Configure.FOLDER;
			for (int i = 0; i < uploadFiles.size(); i++) {
				try {
					StreamUtils.copy(
							new FileInputStream(uploadFiles.get(i)),
							new FileOutputStream(new File(folder
									+ File.separator
									+ uploadFilesFileName.get(i))));
					FileUtils.copyFileToDirectory(uploadFiles.get(i), new File(
							folder));
					String path = folder + "/" + uploadFilesFileName.get(i);
					Dessert dessert = new Dessert();
					dessert.setName(name);
					dessert.setOwingTo(owingTo);
					dessert.setPath(path);
					dessert.setPrice(price);
					dessert.setStockNum(stockNum);
					dessert.setDate(date);
					dessert.setWeekDay(weekDay);
					dessertService.add(dessert);

					// 在session中添加
					@SuppressWarnings("unchecked")
					Map<Week, ArrayList<String>> planWithDessertName = (Map<Week, ArrayList<String>>) request()
							.getSession().getAttribute(Configure.PLAN);
					ArrayList<String> dayPlan = planWithDessertName
							.get(weekDay);
					dayPlan.add(dessert.getName());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return Configure.SUCCESS;

	}

	public String editPlan() {
		message = "";
		plans = new HashMap<String, ArrayList<Dessert>>();

		Map<String, String> params = getParams();
		long dessertId = Long.parseLong(params.get("dessertId"));
		String owingTo = params.get("owingTo");
		String name = params.get("name");
		double price = Double.parseDouble(params.get("price"));
		int stockNum = Integer.parseInt(params.get("stockNum"));

		// 复制文件
		if (uploadFiles != null) {
			String folder = Configure.FOLDER;
			for (int i = 0; i < uploadFiles.size(); i++) {
				try {
					StreamUtils.copy(
							new FileInputStream(uploadFiles.get(i)),
							new FileOutputStream(new File(folder
									+ File.separator
									+ uploadFilesFileName.get(i))));
					FileUtils.copyFileToDirectory(uploadFiles.get(i), new File(
							folder));
					String path = folder + "/" + uploadFilesFileName.get(i);
					Dessert dessert = new Dessert();
					dessert.setId(dessertId);
					dessert.setName(name);
					dessert.setOwingTo(owingTo);
					dessert.setPath(path);
					dessert.setPrice(price);
					dessert.setStockNum(stockNum);
					dessertService.update(dessert);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return Configure.SUCCESS;
	}

	public String deletePlan() {
		message = "";
		plans = new HashMap<String, ArrayList<Dessert>>();

		// Map<String, String> params = getParams();
		// long dessertId=Long.parseLong(params.get("dessertId"));
		// Dessert dessert=new Dessert();
		// dessert.setId(dessertId);
		// dessertService.delete(dessert);
		// message="success";
		System.out.println("delete");

		return Configure.SUCCESS;
	}

	public String getPlanByShopName() {
		message = "";
		plans = new HashMap<String, ArrayList<Dessert>>();

		Map<String, String> params = getParams();
		
		@SuppressWarnings("unchecked")
		Map<Week, ArrayList<String>> plan = (Map<Week, ArrayList<String>>) request()
				.getSession(false).getAttribute(Configure.PLAN);
		if (plan != null) {
			for (Week key : plan.keySet()) {
				ArrayList<String> dessertNames = plan.get(key);
				ArrayList<Dessert> desserts = dessertService
						.getDessertByName(dessertNames);
				plans.put(Week.toString(key), desserts);
			}
		}
		ArrayList<Dessert> desserts = new ArrayList<Dessert>();
		Dessert dessert=new Dessert();
		dessert.setId(1);
		dessert.setName("测试名称");
		dessert.setOwingTo("shop1");
		dessert.setPath("image/desserts/shop1/2.jpg");
		dessert.setPrice(10);
		dessert.setStockNum(1);
		dessert.setDate(DateUtil.getToday());
		desserts.add(dessert);
		plans.put(Week.toString(Week.Sunday), desserts);

		plansJson = JsonUtil.toJson(plans);
//		plansJson="{\"comments\":[{\"content\":\"很不错嘛\",\"id\":1,\"nickname\":\"纳尼\"},{\"content\":\"哟西哟西\",\"id\":2,\"nickname\":\"小强\"}]}";

		return Configure.SUCCESS;
	}

	public String submitPlan() {
		message = "";
		plans = new HashMap<String, ArrayList<Dessert>>();
		@SuppressWarnings("unchecked")
		Map<Week, ArrayList<String>> plan = (Map<Week, ArrayList<String>>) request()
				.getSession(false).getAttribute(Configure.PLAN);
		if (plan != null) {
			for (Week key : plan.keySet()) {
				ArrayList<String> dessertNames = plan.get(key);
				ArrayList<Dessert> desserts = dessertService
						.getDessertByName(dessertNames);
				plans.put(Week.toString(key), desserts);
			}
		}
		Map<String, String> params = getParams();
		String shopName = params.get("shopName");
		long planId = Long.parseLong(params.get("planId"));

		Plan plan2 = new Plan();
		plan2.setId(planId);
		plan2.setShop(shopName);
//		plan2.setPlans(null);

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String createAt = sdf.format(date);
		plan2.setCreateAt(createAt);
		planService.submitPlans(plan2);
		message = Configure.SUCCESS;
		return Configure.SUCCESS;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HashMap<String, ArrayList<Dessert>> getPlans() {
		return plans;
	}

	public void setPlans(HashMap<String, ArrayList<Dessert>> plans) {
		this.plans = plans;
	}

	public String getPlansJson() {
		return plansJson;
	}

	public void setPlansJson(String plansJson) {
		this.plansJson = plansJson;
	}

}
