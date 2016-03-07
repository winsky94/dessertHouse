package dessert.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.ConsumeStats;
import dessert.VO.ConsumeVO;
import dessert.VO.Favorite;
import dessert.VO.HotDessert;
import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.entity.RechargeRecord;
import dessert.service.DessertService;
import dessert.service.MemberService;
import dessert.service.PlanService;

/**
 * @author 严顺宽
 */
public class ManagerJsonController extends BaseController {
	private static final long serialVersionUID = 1L;
	private String message = "";
	@Autowired
	private PlanService planService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private DessertService dessertService;
	private HashMap<String, Integer> ageInfo = new HashMap<String, Integer>();
	private HashMap<String, Integer> sexInfo = new HashMap<String, Integer>();
	private HashMap<String, Integer> addressInfo = new HashMap<String, Integer>();
	private ArrayList<ConsumeVO> data;
	private ArrayList<ConsumeStats> consumeResult;
	private HashMap<String, Integer> stastics = new HashMap<String, Integer>();
	private List<RechargeRecord> rechargeResult;
	private LinkedList<HotDessert> dessertResult;
	private ArrayList<Favorite> favorites;
	private LinkedList<HashMap<String, Integer>> statusInfo;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	public String approvePlan() {
		Map<String, String> params = getParams();
		long planId = Long.parseLong(params.get("planId"));
		int result = Integer.parseInt(params.get("result"));
		int line = planService.approve(planId, result);
		if (line == 1) {
			message = "success";
		} else {
			message = "fail";
		}
		return Configure.SUCCESS;
	}

	/**
	 * 统计会员的基本信息情况
	 * 
	 * @return
	 */
	public String baseData() {
		Map<String, String> params = getParams();
		String year = params.get("year");
		String month = params.get("month");
		if (year == null || month == null) {
			ageInfo = memberService.getAgeData();
			sexInfo = memberService.getSexData();
			addressInfo = memberService.getAddressData();
		} else {
			ageInfo = memberService.getAgeData(year, month);
			sexInfo = memberService.getSexData(year, month);
			addressInfo = memberService.getAddressData(year, month);
		}

		return Configure.SUCCESS;
	}

	public String status(){
		Map<String, String> params = getParams();
		String year = params.get("year");
		String month = params.get("month");
		statusInfo=memberService.getMemberStatus(year,month);
		
		return Configure.SUCCESS;
	}
	
	/**
	 * 统计会员总人数
	 * 
	 * @return
	 */
	public String totalInfo() {
		Map<String, String> params = getParams();
		String year = params.get("year");
		String month = params.get("month");
		if (year == null || month == null) {
			stastics = memberService.getTotalInfo();
		} else {
			stastics = memberService.getTotalInfo(year, month);
		}

		return Configure.SUCCESS;
	}

	public String consumeData() {
		data = null;
		Map<String, String> params = getParams();
		String year = params.get("year");
		String month = params.get("month");
		if (year == null || month == null) {
			data = memberService.getConsumeRecord();
		} else {
			data = memberService.getConsumeRecord(year, month);
		}
		return Configure.SUCCESS;
	}

	public String rechargeData() {
		data = null;
		Map<String, String> params = getParams();
		String year = params.get("year");
		String month = params.get("month");
		if (year == null || month == null) {
			rechargeResult = memberService.getRechargeRecord();
		} else {
			rechargeResult = memberService.getRechargeRecord(year, month);
		}
		return Configure.SUCCESS;
	}

	public String consumeStats() {
		Map<String, String> params = getParams();
		consumeResult = new ArrayList<ConsumeStats>();

		String year = params.get("year");
		String month = params.get("month");
		consumeResult = memberService.consumeStats(year, month);
		return Configure.SUCCESS;
	}

	public String hotDessert() {
		Map<String, String> params = getParams();
		dessertResult = new LinkedList<HotDessert>();

		String year = params.get("year");
		String month = params.get("month");
		
		dessertResult = dessertService.hotDessert(year, month);

		return Configure.SUCCESS;
	}

	public String memberFavorite() {
		Map<String, String> params = getParams();
		favorites = new ArrayList<Favorite>();

		String year = params.get("year");
		String month = params.get("month");
		favorites = dessertService.memberFavorite(year, month);

		return Configure.SUCCESS;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HashMap<String, Integer> getAgeInfo() {
		return ageInfo;
	}

	public void setAgeInfo(HashMap<String, Integer> ageInfo) {
		this.ageInfo = ageInfo;
	}

	public HashMap<String, Integer> getSexInfo() {
		return sexInfo;
	}

	public void setSexInfo(HashMap<String, Integer> sexInfo) {
		this.sexInfo = sexInfo;
	}

	public HashMap<String, Integer> getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(HashMap<String, Integer> addressInfo) {
		this.addressInfo = addressInfo;
	}

	public ArrayList<ConsumeVO> getData() {
		return data;
	}

	public void setData(ArrayList<ConsumeVO> data) {
		this.data = data;
	}

	public ArrayList<ConsumeStats> getConsumeResult() {
		return consumeResult;
	}

	public void setConsumeResult(ArrayList<ConsumeStats> consumeResult) {
		this.consumeResult = consumeResult;
	}

	public HashMap<String, Integer> getStastics() {
		return stastics;
	}

	public void setStastics(HashMap<String, Integer> stastics) {
		this.stastics = stastics;
	}

	public List<RechargeRecord> getRechargeResult() {
		return rechargeResult;
	}

	public void setRechargeResult(List<RechargeRecord> rechargeResult) {
		this.rechargeResult = rechargeResult;
	}

	public LinkedList<HotDessert> getDessertResult() {
		return dessertResult;
	}

	public void setDessertResult(LinkedList<HotDessert> dessertResult) {
		this.dessertResult = dessertResult;
	}

	public ArrayList<Favorite> getFavorites() {
		return favorites;
	}

	public LinkedList<HashMap<String, Integer>> getStatusInfo() {
		return statusInfo;
	}

	public void setStatusInfo(LinkedList<HashMap<String, Integer>> statusInfo) {
		this.statusInfo = statusInfo;
	}

	public void setFavorites(ArrayList<Favorite> favorites) {
		this.favorites = favorites;
	}

}
