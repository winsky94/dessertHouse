package dessert.util;

import dessert.entity.Member;

/**
 * 管理会员的状态
 * 
 * @author 严顺宽
 * @date 2016年3月2日 下午9:00:52
 */
public class StatusHelper {
	public static MemberStatus updateStatus(Member member, double money) {
		MemberStatus status = member.getStatus();
		if (MemberStatus.Init.equals(status) && money >= 200) {
			status = MemberStatus.OK;
		}

		return status;
	}
}
