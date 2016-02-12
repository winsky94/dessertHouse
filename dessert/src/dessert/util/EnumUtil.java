package dessert.util;

/**
 * 得到枚举类
 * 
 * @author 严顺宽
 */
public class EnumUtil {

	/**
	 * 根据索引获取
	 * 
	 * @param <T>
	 * @param clazz
	 * @param ordinal
	 * @return
	 */
	public static <T extends Enum<T>> T getEnumByIndex(Class<T> clazz,
			int ordinal) {
		return (T) clazz.getEnumConstants()[ordinal];
	}

	/**
	 * 根据name获取
	 * 
	 * @param <T>
	 * @param enumType
	 * @param name
	 * @return
	 */
	public static <T extends Enum<T>> T getEnuByName(Class<T> enumType,
			String name) {
		return (T) Enum.valueOf(enumType, name);
	}

}
