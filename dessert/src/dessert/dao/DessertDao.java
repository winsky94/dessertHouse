package dessert.dao;

import dessert.entity.Dessert;

/**
 * @author 严顺宽
 */
public interface DessertDao {
	public long add(Dessert dessert);

	public void delete(Dessert dessert);

	public void update(Dessert dessert);

	public boolean checkExist(String name,String date,String shopName);

	public Dessert getDessertByName(String name);
	
	public Dessert getDessertById(long id);
}
