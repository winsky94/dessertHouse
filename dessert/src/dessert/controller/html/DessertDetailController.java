package dessert.controller.html;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.DessertVO;
import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.service.DessertService;

/**
 * @author 严顺宽
 */
public class DessertDetailController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	private DessertService dessertService;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		String sessionName = Configure.DESSERT_DETAIL_SESSION;
		session().removeAttribute(sessionName);

		String idStr = params.get("id");
		if (idStr == null) {
			return Configure.DESSERT_DETAIL_NOT_FOUND;
		} else {
			long id = Long.parseLong(idStr);
			DessertVO dessert = dessertService.getDessertById(id);
			if (dessert == null) {
				return Configure.DESSERT_DETAIL_NOT_FOUND;
			} else {
				session().setAttribute(sessionName, dessert);
				return Configure.DESSERT_DETAIL;
			}
		}

	}

}
