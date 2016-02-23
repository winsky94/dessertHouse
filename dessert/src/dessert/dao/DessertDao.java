package dessert.dao;

import dessert.entity.Dessert;

/**
 * @author 严顺宽
 */
public interface DessertDao {
	public long add(Dessert dessert);

	public void delete(Dessert dessert);

	public void update(Dessert dessert);

	public boolean checkExist(String name);

	public Dessert getDessertByName(String name);
}
