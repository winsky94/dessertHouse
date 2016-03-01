package dessert.service.impl;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;

import dessert.configure.Configure;
import dessert.dao.MemberDao;
import dessert.entity.Member;
import dessert.service.MemberService;
import dessert.util.DesUtils;

/**
 * @author 严顺宽
 */
public class MemberServiceImpl implements MemberService {
	@Autowired
	public MemberDao memberDao;

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
}
