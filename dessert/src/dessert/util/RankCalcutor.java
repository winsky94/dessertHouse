package dessert.util;

/**
 * 会员等级的计算处理
 * 
 * @author 严顺宽
 * @date 2016年3月2日 下午8:53:50
 */
public class RankCalcutor {
	public static void main(String[] args) {
		System.out.println(getRank(300));
	}

	public static int getRank(double money) {
		int rank = 0;
		rank = (int) (rank + money / 200);
		return rank;
	}
}
