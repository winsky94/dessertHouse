package dessert.dao.impl;

import dessert.dao.ConsumeDao;
import dessert.entity.ConsumeRecord;

/**
 * @author 严顺宽
 * @date 2016年3月3日 下午12:36:14
 */
public class ConsumeDaoImpl extends BaseDaoImpl<ConsumeRecord> implements
		ConsumeDao {

	@Override
	public long consume(ConsumeRecord record) {
		// TODO Auto-generated method stub
		return super.add(record);
	}

}
