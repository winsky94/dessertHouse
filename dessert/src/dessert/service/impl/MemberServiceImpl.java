package dessert.service.impl;

import java.text.DecimalFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.configure.Configure;
import dessert.dao.MemberDao;
import dessert.dao.RechargeDao;
import dessert.entity.Member;
import dessert.entity.RechargeRecord;
import dessert.service.MemberService;
import dessert.util.DateUtil;
import dessert.util.DesUtils;
import dessert.util.MemberStatus;
import dessert.util.RankCalcutor;
import dessert.util.StatusHelper;

/**
 * @author 严顺宽
 */
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private RechargeDao rechargeDao;

	@Override
	public String signUp(Member member) {
		// TODO Auto-generated method stub

		String message = "";

		// 检查注册条件
		String name = member.getName();
		int age = member.getAge();
		if (age <= 0 || age >= 100) {
			message = Configure.AGE_ERROR;
		} else if (memberDao.findByName(name) != null) {
			message = Configure.NAME_ERROR;
		} else {
			// 用户不存在，可以注册
			// 生成7位的会员号
			String memberId = generateMemberId();
			member.setMemberId(memberId);

			// 密码加密
			DesUtils des = new DesUtils(Configure.KEY);// 自定义密钥
			String password = des.encrypt(member.getPassword());
			member.setPassword(password);

			message = memberDao.signUp(member);
		}

		return message;
	}

	/**
	 * 生成7位的会员id
	 * 
	 * @return
	 */
	private String generateMemberId() {
		String max = memberDao.getMaxMemberId();
		if (max == null) {
			max = "0";
		}
		int maxId = Integer.parseInt(max);
		int id = maxId + 1;
		DecimalFormat df = new DecimalFormat(Configure.STR_FORMAT);
		return df.format(id);
	}

	@Override
	public Member getMemberInfo(String name) {
		// TODO Auto-generated method stub
		return memberDao.getMemberInfo(name);
	}

	@Override
	public String update(Member member) {
		// TODO Auto-generated method stub
		memberDao.update(member);
		return Configure.SUCCESS;
	}

	@Override
	public void recharge(RechargeRecord record) {
		// TODO Auto-generated method stub
		rechargeDao.recharge(record);
		// 修改会员信息——账户余额和状态
		updateMemberRight(record);

	}

	/**
	 * 根据充值记录，更改会员资格相关信息
	 * 
	 * @param record
	 */
	private void updateMemberRight(RechargeRecord record) {
		String memberId = record.getMemberId();
		Member member = memberDao.getMemberInfoById(memberId);

		// 账户余额
		double pay_money = record.getMoney();
		double originalMoney = member.getValidMoney();
		double validMoney = originalMoney + pay_money;
		member.setValidMoney(validMoney);
		String validDate = DateUtil.getDateAfter(new Date(), 365);// 暂且定义一年后就是365天
		member.setValidDate(validDate);

		// 会员等级
		int rank = RankCalcutor.getRank(pay_money);
		member.setRank(rank);

		// 会员状态
		MemberStatus status = StatusHelper.updateStatus(member, pay_money);
		member.setStatus(status);

		// 保存会员信息
		memberDao.update(member);
	}
}
