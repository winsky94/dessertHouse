package dessert.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dessert.dao.WorkerDao;
import dessert.entity.Worker;
import dessert.util.CheckError;
import dessert.util.TimeUtil;
import dessert.util.UserType;

/**
 * @author 严顺宽
 */
public class WorkerDaoImpl extends BaseDaoImpl<Worker> implements WorkerDao {

	@Override
	public UserType checkLogin(String workerId, String password) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Worker.class);
		criteria.add(Restrictions.eq("workerId", workerId));
		criteria.add(Restrictions.eq("password", password));
		@SuppressWarnings("unchecked")
		List<Worker> workers = criteria.list();
		if (workers == null || workers.size() == 0) {
			return UserType.error;
		} else {
			// 检查可能的错误
			CheckError.checkListSize(workers);

			Worker worker = workers.get(0);

			// 更新最近登录时间
			String lastLoadTime = TimeUtil.getCurrentTime();
			worker.setLastLoadTime(lastLoadTime);
			update(worker);
			UserType type = worker.getType();
			return type;
		}
	}
}
