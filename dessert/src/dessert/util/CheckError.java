package dessert.util;

import java.util.List;

/**
 * 检查hibernate查询出的结果集数量，防止数量应为1，实际大于1
 * 
 * @author 严顺宽
 */
public class CheckError {
	public static void checkListSize(List<?> list) {
		if (list.size() > 1) {
			System.err.println("查询的结果集应该为1，但实际为" + list.size());
		}
	}
}
