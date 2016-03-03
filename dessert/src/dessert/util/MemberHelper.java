package dessert.util;

import dessert.entity.Member;

/**
 * 会员等级的计算处理、每次消费的折扣和累积积分的计算
 * 
 * @author 严顺宽
 * @date 2016年3月2日 下午8:53:50
 */
public class MemberHelper {
	public static void main(String[] args) {
		System.out.println(getRank(300));
	}

	public static int getRank(double money) {
		int rank = 0;
		rank = (int) (rank + money / 200);
		return rank;
	}

	/**
	 * 计算会员此次消费的折扣，暂定算法：等级*消费额/50
	 * 
	 * @param member
	 * @param price
	 * @return
	 */
	public static double getDiscount(Member member, double money) {
		double discount = 0;
		int rank = member.getRank();
		discount = rank * money / 50;

		return discount;
	}

	/**
	 * 计算会员此次消费的累积积分，暂定算法：等级*消费额
	 * 
	 * @param member
	 * @param price
	 * @return
	 */
	public static int getPoint(Member member, double money) {
		int point = 0;
		int rank = member.getRank();
		point = (int) (rank * money);
		return point;
	}
}
