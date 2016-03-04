package dessert.dao;

import java.util.List;

import dessert.entity.ConsumeRecord;

/**
 * @author 严顺宽
 * @date 2016年3月3日 下午12:35:24
 */
public interface ConsumeDao {
	public long consume(ConsumeRecord record);

	public List<ConsumeRecord> getConsumeRecord(String memberId);

	public List<ConsumeRecord> getConsumeRecord();

	public ConsumeRecord getConsumeRecord(long id);

	public void delete(ConsumeRecord record);
}
