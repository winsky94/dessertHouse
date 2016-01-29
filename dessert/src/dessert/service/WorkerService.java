package dessert.service;

import dessert.entity.Worker;

/**
 * @author 严顺宽
 */
public interface WorkerService {
	public long add(Worker worker);

	public void delete(Worker worker);

	public void update(Worker worker);
}
