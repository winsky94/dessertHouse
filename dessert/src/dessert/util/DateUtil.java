package dessert.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author 严顺宽
 */
public class DateUtil {

	public static void main(String[] args) {
		// 定义输出日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EEE");

		Date currentDate = new Date();

		// 比如今天是2012-12-25
		List<Date> days = dateToWeek(currentDate);
		System.out.println("今天的日期: " + sdf.format(currentDate));
		for (Date date : days) {
			System.out.println(sdf.format(date));
		}
	}

	public static String getToday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EEE");
		Date currentDate = new Date();
		return sdf.format(currentDate);
	}

	/**
	 * 根据日期得到是周几
	 * 
	 * @param date
	 *            日期 ，格式形如xxxx-xx-xx
	 * @return
	 */
	public static Week getWeekByDate(String date) {
		Calendar calendar = Calendar.getInstance();// 获得一个日历
		String[] dates = date.split("-");
		int year = Integer.parseInt(dates[0]);
		int month = Integer.parseInt(dates[1]);
		int day = Integer.parseInt(dates[2]);
		calendar.set(year, month - 1, day);// 设置当前时间,月份是从0月开始计算
		int number = calendar.get(Calendar.DAY_OF_WEEK);// 星期表示1-7，是从星期日开始，
		return Week.getWeek(number);
	}

	/**
	 * 得到某天的后几天
	 * 
	 * @param date
	 *            日期
	 * @param gap
	 *            日期差
	 * @return 日期 ，格式形如xxxx-xx-xx
	 */
	public static String getDateAfter(Date date, int gap) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + gap);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(now.getTime());
	}

	/**
	 * 根据当前选中的星期几，返回日期
	 * 
	 * @param day
	 *            星期的英文
	 * @return 日期 ，格式形如xxxx-xx-xx
	 */
	public static String getDate(String day) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(date);
		Week todayWeek = getWeekByDate(today);
		int todayNum = Week.getNum(todayWeek);
		int chosenNum = getNumByDayEn(day);
		int gap = (6 - todayNum) + (chosenNum - 0) + 1;
		return getDateAfter(date, gap);
	}

	/**
	 * 根据英文得到周几对应的数字
	 * 
	 * @param day
	 *            星期的英文
	 * @return 0-6，周日为0
	 */
	public static int getNumByDayEn(String day) {
		switch (day) {
		case "Sunday":
			return 0;
		case "Monday":
			return 1;
		case "Tuesday":
			return 2;
		case "Wednesday":
			return 3;
		case "Thursday":
			return 4;
		case "Friday":
			return 5;
		case "Saturday":
			return 6;
		default:
			return -1;
		}
	}

	/**
	 * 根据日期获得所在周的日期 ，以周日为一周的第一天
	 * 
	 * @param mdate
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static List<Date> dateToWeek(Date mdate) {
		int b = mdate.getDay();
		Date fdate;
		List<Date> list = new ArrayList<Date>();
		Long fTime = mdate.getTime() - b * 24 * 3600000;
		for (int a = 0; a < 7; a++) {
			fdate = new Date();
			fdate.setTime(fTime + (a * 24 * 3600000));
			list.add(a, fdate);
		}
		return list;
	}
}
