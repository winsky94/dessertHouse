package dessert.controller.api;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.PlanVO;
import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.entity.Dessert;
import dessert.service.DessertService;
import dessert.service.PlanService;
import dessert.util.DateUtil;
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

		// String shopName = params.get("shopName");
		// PlanVO planVO = planService.getPlans(shopName);
		// HttpSession session = session();
		// session.setAttribute(Configure.PLAN_SESSION, planVO);
		return Configure.SUCCESS;
	}

	public String checkExist() {
		message = "";

		Map<String, String> params = getParams();
		String name = params.get("name");
		String weekDay=params.get("weekDay");
		boolean exist = dessertService.checkExist(name,weekDay);
		if (exist) {
			message = Configure.Dessert_EXIST;
		}
		return Configure.SUCCESS;
	}

	public String addPlan() {
		message = "";
		Map<String, String> params = getParams();
		String action = params.get("action");
		if ("add".equals(action)) {
			add();
		} else if ("update".equals(action)) {
			edit();
		} else {
			System.err.println("上传中action出错");
		}

		return Configure.SUCCESS;

	}

	private void add() {
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
			// for (int i = 0; i < uploadFiles.size(); i++) {
			try {
				// StreamUtils.copy(
				// new FileInputStream(uploadFiles.get(i)),
				// new FileOutputStream(new File(folder
				// + File.separator
				// + uploadFilesFileName.get(i))));
				// ======================================================================
				FileUtils.copyFile(uploadFiles.get(0),
						new File(new File(folder) + File.separator
								+ uploadFilesFileName.get(0)));
				uploadFiles.get(0).delete();
				// ======================================================================
				String path = folder + "\\" + uploadFilesFileName.get(0);
				Dessert dessert = new Dessert();
				dessert.setName(name);
				dessert.setOwingTo(owingTo);
				dessert.setPath(path);
				dessert.setPrice(price);
				dessert.setStockNum(stockNum);
				dessert.setDate(date);
				dessert.setWeekDay(weekDay);
				dessertService.add(dessert);

				// 在session中添加，更新session
				@SuppressWarnings("unchecked")
				Map<Week, ArrayList<String>> planWithDessertName = (Map<Week, ArrayList<String>>) session()
						.getAttribute(Configure.PLAN_SHOP_ALL);
				if (planWithDessertName == null) {
					planWithDessertName = new HashMap<Week, ArrayList<String>>();

				}
				ArrayList<String> dayPlan = planWithDessertName.get(weekDay);
				if (dayPlan == null) {
					dayPlan = new ArrayList<String>();
				}
				dayPlan.add(dessert.getName());
				planWithDessertName.put(weekDay, dayPlan);
				// 更新session的内容
				session().setAttribute(Configure.PLAN_SHOP_ALL,
						planWithDessertName);
				System.out
						.println("PlanJsonController.addPlan()设置Configure.PLAN_SHOP_ALL，大小为："
								+ planWithDessertName.size());
			} catch (IOException e) {
				e.printStackTrace();
			}
			// }
		}
	}

	public String editPlan() {
		message = "";

		edit();

		return Configure.SUCCESS;
	}

	private void edit() {
		Map<String, String> params = getParams();
		long dessertId = Long.parseLong(params.get("dessertId"));
		String owingTo = params.get("owingTo");
		String originalPicName = params.get("originalPicName");
		String name = params.get("name");
		double price = Double.parseDouble(params.get("price"));
		int stockNum = Integer.parseInt(params.get("stockNum"));
		String day = params.get("day");
		String date = DateUtil.getDate(day);
		Week weekDay = Week.getWeek(DateUtil.getNumByDayEn(day));

		// 复制文件
		if (uploadFiles != null) {
			String folder = Configure.FOLDER;
			// for (int i = 0; i < uploadFiles.size(); i++) {
			try {
				// ======================================================================
				if (!uploadFilesFileName.get(0).equals(originalPicName)) {
					// 删除原图片
					String path = folder + "\\" + originalPicName;
					deleteFile(path);
					// 重新复制图片
					FileUtils.copyFile(uploadFiles.get(0),
							new File(new File(folder) + File.separator
									+ uploadFilesFileName.get(0)));
					uploadFiles.get(0).delete();
				}

				// ======================================================================
				String path = folder + "\\" + uploadFilesFileName.get(0);
				Dessert dessert = new Dessert();
				dessert.setId(dessertId);
				dessert.setName(name);
				dessert.setOwingTo(owingTo);
				dessert.setPath(path);
				dessert.setPrice(price);
				dessert.setStockNum(stockNum);
				dessert.setDate(date);
				dessert.setWeekDay(weekDay);
				dessertService.update(dessert);

			} catch (IOException e) {
				e.printStackTrace();
			}
			// }
		} else {
			String folder = Configure.FOLDER;

			// 图片是一样的，不要考虑图片的 问题
			String path = folder + "\\" + uploadFilesFileName.get(0);
			Dessert dessert = new Dessert();
			dessert.setId(dessertId);
			dessert.setName(name);
			dessert.setOwingTo(owingTo);
			dessert.setPath(path);
			dessert.setPrice(price);
			dessert.setStockNum(stockNum);
			dessert.setDate(date);
			dessert.setWeekDay(weekDay);
			dessertService.update(dessert);
		}
	}

	public String deletePlan() {
		message = "";

		Map<String, String> params = getParams();
		long dessertId = Long.parseLong(params.get("dessertId"));
		String picName = params.get("picName");
		Dessert dessert = new Dessert();
		dessert.setId(dessertId);

		// 删除session中对应的
		@SuppressWarnings("unchecked")
		Map<Week, ArrayList<String>> plan = (Map<Week, ArrayList<String>>) session()
				.getAttribute(Configure.PLAN_SHOP_ALL);
		if (plan != null) {
			Map<Week, ArrayList<String>> planAllResult = new HashMap<Week, ArrayList<String>>();

			session().removeAttribute(Configure.PLAN_SESSION);
			for (Week key : plan.keySet()) {
				ArrayList<String> dessertNames = plan.get(key);
				ArrayList<String> dessertNamesResult = new ArrayList<String>();
				if (dessertNames != null) {
					for (String name : dessertNames) {
						System.out.println("PlanJsonController.deletePlan() "
								+ name);
						long id = dessertService.getDesertIdByName(name);
						if (id != dessertId) {
							dessertNamesResult.add(name);
						}
					}
					planAllResult.put(key, dessertNamesResult);
				}
			}
			session().setAttribute(Configure.PLAN_SHOP_ALL, planAllResult);
		}

		dessertService.delete(dessert);
		// 删除原图片
		String folder = Configure.FOLDER;
		String path = folder + "\\" + picName;
		deleteFile(path);

		message = "success";
		System.out.println("delete");

		return Configure.SUCCESS;
	}

	public String submitPlan() {
		message = "";

		HashMap<Week, ArrayList<String>> planDetail = new HashMap<Week, ArrayList<String>>();

		@SuppressWarnings("unchecked")
		Map<Week, ArrayList<String>> plan = (Map<Week, ArrayList<String>>) session()
				.getAttribute(Configure.PLAN_SHOP_ALL);
		if (plan != null) {
			for (Week key : plan.keySet()) {
				ArrayList<String> dessertNames = plan.get(key);
				planDetail.put(key, dessertNames);
			}
		}
		Map<String, String> params = getParams();
		String shopName = params.get("shopName");
		long planId = Long.parseLong(params.get("planId"));

		PlanVO planVO = new PlanVO();
		planVO.setId(planId);
		planVO.setShop(shopName);
		planVO.setPlans(planDetail);
		// 新上传的plan没有被check
		planVO.setChecked(Configure.PLAN_READY);

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String createAt = sdf.format(date);
		planVO.setCreateAt(createAt);
		planService.submitPlans(planVO);

		// 删除session，免得混淆其他店面的
		session().removeAttribute(Configure.PLAN_SHOP_ALL);
		session().removeAttribute(Configure.PLAN_SESSION);

		System.out.println("提交计划");
		message = Configure.SUCCESS;
		return Configure.SUCCESS;
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
