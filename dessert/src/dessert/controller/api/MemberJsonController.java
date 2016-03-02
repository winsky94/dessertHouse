package dessert.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.configure.Configure;
import dessert.controller.BaseController;
import dessert.entity.Member;
import dessert.entity.RechargeRecord;
import dessert.service.MemberService;
import dessert.util.MemberStatus;
import dessert.util.TimeUtil;

/**
 * @author 严顺宽
 * @date 2016年3月1日 下午8:40:38
 */
public class MemberJsonController extends BaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	private MemberService memberService;
	private Member member;
	private String message;

	@Override
	public String process(Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	public String memberInfo() {
		Map<String, String> params = getParams();
		String name = params.get("name");
		if (name != null) {
			member = memberService.getMemberInfo(name);
		} else {
			member = null;
		}
		return Configure.SUCCESS;
	}

	public Member getMember() {
		return member;
	}

	public String update() {
		Map<String, String> params = getParams();

		String idStr = params.get("id");
		if (idStr == null) {
			message = "会员存储id为空";
			return Configure.SUCCESS;
		}
		long id = Long.parseLong(idStr);
		String userName = params.get("userName");
		String password = params.get("password");

		int age = Integer.parseInt(params.get("age"));
		String sex = params.get("sex");
		String birthday = params.get("birthday");
		String telephone = params.get("telephone");
		String cardId = params.get("cardId");
		String lastLoadTime = params.get("lastLoadTime");
		String memberId = params.get("memberId");
		int point = Integer.parseInt(params.get("point"));
		int rank = Integer.parseInt(params.get("rank"));
		MemberStatus status = MemberStatus.getStatus(params.get("status"));
		double validMoney = Double.parseDouble(params.get("validMoney"));
		String validDate = params.get("validDate");
		if ("-".equals(validDate)) {
			validDate = null;
		}

		Member member = new Member();
		member.setId(id);
		member.setName(userName);
		member.setPassword(password);
		member.setAge(age);
		member.setSex(sex);
		member.setBirthday(birthday);
		member.setTelephone(telephone);
		member.setCardId(cardId);
		member.setStatus(status);
		member.setRank(rank);
		member.setLastLoadTime(lastLoadTime);
		member.setValidDate(validDate);
		member.setValidMoney(validMoney);
		member.setMemberId(memberId);
		member.setPoint(point);

		message = memberService.update(member);

		return Configure.SUCCESS;
	}

	public String checkMemberStatus() {
		message = "";

		Map<String, String> params = getParams();
		String memberName = params.get("name");
		message = memberService.checkMemberStatus(memberName);

		return Configure.SUCCESS;
	}

	public String pay() {
		Map<String, String> params = getParams();

		String memberId = params.get("memberId");
		double money = Double.parseDouble(params.get("pay_money"));
		RechargeRecord record = new RechargeRecord();
		record.setMoney(money);
		record.setMemberId(memberId);
		record.setDate(TimeUtil.getCurrentTime());
		memberService.recharge(record);

		message = Configure.SUCCESS;

		return Configure.SUCCESS;
	}

	public String cancelMember() {
		Map<String, String> params = getParams();
		String memberId = params.get("memberId");
		memberService.cancelMember(memberId);
		
		message = Configure.SUCCESS;
		return Configure.SUCCESS;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}