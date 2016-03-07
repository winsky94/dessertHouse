package dessert.controller.html;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.DessertVO;
import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.service.DessertService;
import dessert.service.PlanService;
import dessert.util.DateUtil;
import dessert.util.Week;

/** 
 * @author 严顺宽 
 * @date 2016年3月7日 下午7:32:19
 */
public class DessertController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	private DessertService dessertService;
	@Autowired
	public PlanService planService;
	
	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		String sessionName=Configure.All_DESSERT_SESSION;
		ArrayList<DessertVO> desserts  = dessertService.getAllDesserts();
		session().setAttribute(sessionName, desserts);
		return Configure.All_DESSERT;
	}
	
	public String shopDesessert(){
		String sessionName=Configure.SHOP_DESSERT_SESSION;
		 Map<String, String> params = getParams();
		 String weekDay = params.get("weekDay");
		 String date = params.get("date");
		 String shopName = params.get("shopName");
		 //默认是今天
		 if(weekDay==null){
			Week week= DateUtil.getWeekByDate(DateUtil.getToday());
			weekDay=Week.toString(week);
		 }
		 if(date==null){
			 date=DateUtil.getToday();
		 }
		 ArrayList<DessertVO> desserts = planService.getDayDessert(date, weekDay, shopName);
		 
		session().setAttribute(sessionName, desserts);
		
		return Configure.SHOP_DESSERT;
	}

}
