package dessert.util;

import dessert.VO.ShopVO;
import dessert.VO.WorkerVO;
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
}
