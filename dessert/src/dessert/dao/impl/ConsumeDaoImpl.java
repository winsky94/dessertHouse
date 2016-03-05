package dessert.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import dessert.VO.ConsumeStats;
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

	@Override
	public List<ConsumeRecord> getConsumeRecord(String memberId) {
		// TODO Auto-generated method stub
		return getListByColumn(ConsumeRecord.class, "memberId", memberId);
	}

	@Override
	public List<ConsumeRecord> getConsumeRecord() {
		// TODO Auto-generated method stub
		return getAll(ConsumeRecord.class);
	}

	@Override
	public ConsumeRecord getConsumeRecord(long id) {
		// TODO Auto-generated method stub
		return getById(ConsumeRecord.class, id);
	}

	@Override
	public List<ConsumeRecord> getConsumeRecord(String year, String month) {
		// TODO Auto-generated method stub
		String sql = "select * from consumeRecord where Year(date)='" + year
				+ "' and Month(date)=" + month + ";";
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<ConsumeRecord> result = session.createSQLQuery(sql)
				.addEntity(ConsumeRecord.class).list();
		return result;
	}

	@Override
	public ArrayList<ConsumeStats> consumeStats(String year, String month) {
		// TODO Auto-generated method stub
		ArrayList<ConsumeStats> result = new ArrayList<ConsumeStats>();
		String sql = "select name,coalesce(appointment,0),coalesce(amoney,0),coalesce(buy,0),coalesce(bmoney,0) from shop left join (select owingTo as shopName, sum(if(action='appointment',num,0)) as appointment, sum(if(action='appointment',money,0)) as amoney, sum(if(action='buy',num,0)) as buy, sum(if(action='buy',money,0)) as bmoney from consumerecord c, dessert where dessertId=dessert.id and Year(c.date)='"
				+ year
				+ "' and Month(c.date)='"
				+ month
				+ "' group by owingTo) as t on name=shopName;";
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) doSqlQuery(sql);
		if (list != null) {
			for (Object[] object : list) {
				String shopName = object[0].toString();
				int appointmentNum = Integer.parseInt(object[1].toString());
				double appointmentMoney = Double.parseDouble(object[2]
						.toString());
				int saleNum = Integer.parseInt(object[3].toString());
				double saleMoney = Double.parseDouble(object[4].toString());

				ConsumeStats c = new ConsumeStats(shopName, appointmentNum,
						appointmentMoney, saleNum, saleMoney);
				result.add(c);
			}
		}
		return result;
	}

}
