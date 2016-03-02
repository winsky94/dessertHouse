package dessert.controller.api;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.DessertVO;
import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.service.DessertService;

/**
 * @author 严顺宽
 */
public class DessertJsonController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	private DessertService dessertService;
	private ArrayList<DessertVO> desserts = new ArrayList<DessertVO>();

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		desserts = dessertService.getAllDesserts();
		return Configure.SUCCESS;
	}

	public ArrayList<DessertVO> getDesserts() {
		return desserts;
	}

	public void setDesserts(ArrayList<DessertVO> desserts) {
		this.desserts = desserts;
	}

}
