package dessert.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.VO.ConsumeStats;
import dessert.VO.ConsumeVO;
import dessert.VO.DessertVO;
import dessert.configure.Configure;
import dessert.dao.ConsumeDao;
import dessert.dao.MemberDao;
import dessert.dao.RechargeDao;
import dessert.entity.ConsumeRecord;
import dessert.entity.Member;
import dessert.entity.RechargeRecord;
import dessert.service.DessertService;
import dessert.service.MemberService;
import dessert.util.DateUtil;
import dessert.util.DesUtils;
import dessert.util.MemberHelper;
import dessert.util.MemberStatus;
import dessert.util.StatusHelper;

/**
 * @author 严顺宽
 */
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private RechargeDao rechargeDao;
	@Autowired
	private ConsumeDao consumeDao;
	@Autowired
	private DessertService dessertService;

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
		Member member = memberDao.getMemberInfo(name);
		if (member != null) {
			// 把密码解密后返回
			DesUtils des = new DesUtils(Configure.KEY);// 自定义密钥
			String password = des.decrypt(member.getPassword());
			member.setPassword(password);
		}
		return member;
	}

	@Override
	public String update(Member member) {
		// TODO Auto-generated method stub
		// 密码加密
		DesUtils des = new DesUtils(Configure.KEY);// 自定义密钥
		String password = des.encrypt(member.getPassword());
		member.setPassword(password);
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

		// 会员有效期
		String validDate = DateUtil.getDateAfter(new Date(), 365);// 暂且定义一年后就是365天
		member.setValidDate(validDate);

		// 会员记录停止日期
		String overDate = DateUtil.getDateAfter(new Date(), 365 * 2);// 暂且定义两年后就是365*2天
		member.setOverDate(overDate);

		// 会员等级
		int rank = MemberHelper.getRank(member,pay_money);
		member.setRank(rank);

		// 会员状态
		MemberStatus status = StatusHelper.updateStatus(member, pay_money);
		member.setStatus(status);

		// 保存会员信息
		memberDao.update(member);
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		memberDao.deactivate();

	}

	@Override
	public String checkMemberStatus(String memberName) {
		// TODO Auto-generated method stub
		Member member = memberDao.getMemberInfo(memberName);
		if (member != null) {
			MemberStatus status = member.getStatus();
			if (status.equals(MemberStatus.Init)) {
				return Configure.MEMBER_INIT_MES;
			} else if (status.equals(MemberStatus.OK)) {
				return Configure.MEMBER_OK_MES;
			} else if (status.equals(MemberStatus.pause)) {
				return Configure.MEMBER_PAUSE_MES;
			} else if (status.equals(MemberStatus.over)) {
				return Configure.MEMBER_OVER_MES;
			}
		}
		return null;
	}

	@Override
	public void cancelMember(String memberId) {
		// TODO Auto-generated method stub
		memberDao.cancelMember(memberId);
	}

	@Override
	public Member getMemberByMemberId(String memberId) {
		// TODO Auto-generated method stub
		Member member = memberDao.getMemberInfoById(memberId);
		if (member != null) {
			// 把密码解密后返回
			DesUtils des = new DesUtils(Configure.KEY);// 自定义密钥
			String password = des.decrypt(member.getPassword());
			member.setPassword(password);
		}
		return member;
	}

	@Override
	public ArrayList<ConsumeVO> getConsumeRecord(String memberId) {
		// TODO Auto-generated method stub
		ArrayList<ConsumeVO> result = new ArrayList<ConsumeVO>();
		List<ConsumeRecord> records = consumeDao.getConsumeRecord(memberId);
		result = recordToVO(records);
		return result;
	}

	@Override
	public ArrayList<ConsumeVO> getConsumeRecord() {
		// TODO Auto-generated method stub
		ArrayList<ConsumeVO> result = new ArrayList<ConsumeVO>();
		List<ConsumeRecord> records = consumeDao.getConsumeRecord();
		result = recordToVO(records);
		return result;
	}

	@Override
	public List<RechargeRecord> getRechargeRecord(String memberId) {
		// TODO Auto-generated method stub
		List<RechargeRecord> result = new ArrayList<RechargeRecord>();
		result = rechargeDao.getRechargeRecord(memberId);
		return result;
	}

	@Override
	public List<RechargeRecord> getRechargeRecord() {
		// TODO Auto-generated method stub
		List<RechargeRecord> result = new ArrayList<RechargeRecord>();
		result = rechargeDao.getRechargeRecord();
		return result;
	}

	@Override
	public List<RechargeRecord> getRechargeRecord(String year, String month) {
		// TODO Auto-generated method stub
		List<RechargeRecord> result = new ArrayList<RechargeRecord>();
		result = rechargeDao.getRechargeRecord(year, month);
		return result;
	}

	private ArrayList<ConsumeVO> recordToVO(List<ConsumeRecord> records) {
		ArrayList<ConsumeVO> result = new ArrayList<ConsumeVO>();
		if (records != null) {
			for (ConsumeRecord record : records) {
				long id = record.getId();
				String memberId = record.getMemberId();
				String action = record.getAction();
				if ("buy".equals(action)) {
					action = "购买";
				} else if ("appointment".equals(action)) {
					action = "预约";
				}
				String date = record.getDate();
				long dessertId = record.getDessertId();
				double money = record.getMoney();
				int num = record.getNum();
				boolean cash = record.getCash();
				String cashCN = "现金";
				if (!cash) {
					cashCN = "刷卡";
				}
				double discount = record.getDiscount();
				int point = record.getPoint();
				DessertVO dessert = dessertService.getDessertById(dessertId);
				if (dessert != null) {
					String dessertName = dessert.getName();
					ConsumeVO vo = new ConsumeVO(id, memberId, dessertName,
							num, date, money, action, cashCN, discount, point);
					result.add(vo);
				}
			}
		}
		return result;
	}

	@Override
	public String exchange(String memberId, int point) {
		// TODO Auto-generated method stub
		Member member = memberDao.getMemberInfoById(memberId);
		int originalPoint = member.getPoint();
		if (originalPoint < point) {
			return Configure.POINT_NOT_ENOUGH;
		} else {
			// 减会员积分数
			int valid = originalPoint - point;
			member.setPoint(valid);

			// 加会员账户余额
			double exMoney = MemberHelper.getExchangeMoney(point);
			double originalMoney = member.getValidMoney();
			double validMoney = exMoney + originalMoney;
			member.setValidMoney(validMoney);

			// 更新会员信息
			memberDao.update(member);

			return Configure.SUCCESS;
		}
	}

	@Override
	public HashMap<String, Integer> getAgeData() {
		// TODO Auto-generated method stub
		return memberDao.getAgeData();
	}

	@Override
	public HashMap<String, Integer> getSexData() {
		// TODO Auto-generated method stub
		return memberDao.getSexData();
	}

	@Override
	public HashMap<String, Integer> getAddressData() {
		// TODO Auto-generated method stub
		return memberDao.getAddressData();
	}

	@Override
	public ArrayList<ConsumeVO> getConsumeRecord(String year, String month) {
		// TODO Auto-generated method stub
		ArrayList<ConsumeVO> result = new ArrayList<ConsumeVO>();
		List<ConsumeRecord> records = consumeDao.getConsumeRecord(year, month);
		result = recordToVO(records);
		return result;
	}

	@Override
	public ArrayList<ConsumeStats> consumeStats(String year, String month) {
		// TODO Auto-generated method stub
		return consumeDao.consumeStats(year, month);
	}

	@Override
	public HashMap<String, Integer> getAgeData(String year, String month) {
		// TODO Auto-generated method stub
		return memberDao.getAgeData(year, month);
	}

	@Override
	public HashMap<String, Integer> getSexData(String year, String month) {
		// TODO Auto-generated method stub
		return memberDao.getSexData(year, month);
	}

	@Override
	public HashMap<String, Integer> getAddressData(String year, String month) {
		// TODO Auto-generated method stub
		return memberDao.getAddressData(year, month);
	}

	@Override
	public HashMap<String, Integer> getTotalInfo() {
		// TODO Auto-generated method stub
		return memberDao.getTotalInfo();
	}

	@Override
	public HashMap<String, Integer> getTotalInfo(String year, String month) {
		// TODO Auto-generated method stub
		return memberDao.getTotalInfo(year, month);
	}
}
