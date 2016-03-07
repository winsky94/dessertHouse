package dessert.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import dessert.configure.Configure;
import dessert.dao.WorkerDao;
import dessert.entity.Worker;
import dessert.util.CheckError;
import dessert.util.DesUtils;
import dessert.util.TimeUtil;
import dessert.util.UserType;

/**
 * @author 严顺宽
 */
public class WorkerDaoImpl extends BaseDaoImpl<Worker> implements WorkerDao {

	@Override
	public ArrayList<Worker> getWorkers(ArrayList<UserType> types) {
		// TODO Auto-generated method stub

		// 先拼接查询条件
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(Worker.class);
		// 逻辑或
		Disjunction dis = Restrictions.disjunction();

		if (types != null) {
			for (UserType type : types) {
				dis.add(Restrictions.eq("type", type));
			}
		}
		detachedCriteria.add(dis);

		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		@SuppressWarnings("unchecked")
		List<Worker> workers = criteria.list();
		return (ArrayList<Worker>) workers;
	}

	@Override
	public HashMap<UserType, String> checkLogin(String workerId, String password) {
		// TODO Auto-generated method stub
		HashMap<UserType, String> result = new HashMap<UserType, String>();
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Worker.class);
		criteria.add(Restrictions.eq("workerId", workerId));
		criteria.add(Restrictions.eq("password", password));
		@SuppressWarnings("unchecked")
		List<Worker> workers = criteria.list();
		if (workers == null || workers.size() == 0) {
			result.put(UserType.error, "");
		} else {
			// 检查可能的错误
			CheckError.checkListSize(workers);

			Worker worker = workers.get(0);
			String lastTime = worker.getLastLoadTime();

			// 更新最近登录时间
			String lastLoadTime = TimeUtil.getCurrentTime();
			worker.setLastLoadTime(lastLoadTime);
			update(worker);
			UserType type = worker.getType();
			result.put(type, lastTime);
		}
		return result;
	}

	@Override
	public String getMaxWorkerId() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<String> list = session.createQuery(
				"select max(substring(w.workerId,3,7)) from worker w").list();
		String res = list.get(0);
		if (res != null) {
			return res.substring(2);
		} else {
			return null;
		}
	}

	@Override
	public ArrayList<Worker> getWorkers() {
		// TODO Auto-generated method stub
		return (ArrayList<Worker>) getAll(Worker.class);
	}

	@Override
	public Worker getWorkerByWorkerId(String workerId) {
		// TODO Auto-generated method stub
		return getByColumn(Worker.class, "workerId", workerId);
	}

	@Override
	public String changePW(String workerId, String originalPW, String newPW) {
		// TODO Auto-generated method stub
		Worker worker = getWorkerByWorkerId(workerId);
		// 把原密码解密
		DesUtils des = new DesUtils(Configure.KEY);// 自定义密钥
		String password = des.decrypt(worker.getPassword());
		if (!password.equals(originalPW)) {
			return Configure.WRONG_ORIGINAL_PW;
		} else {
			// 加密新密码
			worker.setPassword(des.encrypt(newPW));
			return Configure.SUCCESS;
		}
	}

	@Override
	public Worker getWorkerById(long id) {
		// TODO Auto-generated method stub
		return getByColumn(Worker.class, "id", id);
	}

}
