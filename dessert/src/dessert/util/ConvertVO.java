package dessert.util;

import java.util.ArrayList;
import java.util.HashMap;

import dessert.VO.DessertVO;
import dessert.VO.PlanVO;
import dessert.VO.ShopVO;
import dessert.VO.WorkerVO;
import dessert.entity.Dessert;
import dessert.entity.Plan;
import dessert.entity.Shop;
import dessert.entity.Worker;

/**
 * @author 严顺宽
 */
public class ConvertVO {
	public static ShopVO shopToVO(Shop shop) {
		long id = shop.getId();
		String name = shop.getName();
		String address = shop.getAddress();
		String owner = shop.getOwner();
		String telephone = shop.getTelephone();
		ShopVO vo = new ShopVO(id, name, address, owner, telephone);
		return vo;
	}

	public static WorkerVO workerToVO(Worker worker) {
		// TODO Auto-generated method stub
		long id = worker.getId();
		String workerId = worker.getWorkerId(); // 工号
		String password = worker.getPassword(); // 密码
		String lastLoadTime = worker.getLastLoadTime();// 上次登录时间
		UserType type = worker.getType();// 员工类型
		String owingTo = worker.getOwingTo();// 归属哪个分店,Configure.WAITRESS表明是总店服务员
		WorkerVO vo = new WorkerVO(id, workerId, password, lastLoadTime, type,
				owingTo);
		return vo;
	}

	public static PlanVO planToVO(Plan plan) {
		long id = plan.getId();
		String shop = plan.getShop();
		String createAt = plan.getCreateAt();
		HashMap<Week, ArrayList<Dessert>> plans = plan.getPlans();
		PlanVO vo = new PlanVO(id, shop, createAt, plans);
		return vo;
	}

	public static DessertVO dessertToVO(Dessert dessert) {
		long id = dessert.getId();
		String name = dessert.getName();
		double price = dessert.getPrice();
		int stockNum = dessert.getStockNum();
		String path = dessert.getPath();
		String owingTo = dessert.getOwingTo();
		Week weekDay = dessert.getWeekDay();
		String date = dessert.getDate();
		DessertVO vo = new DessertVO(id, name, price, stockNum, path, owingTo,
				weekDay, date);
		return vo;
	}
}
