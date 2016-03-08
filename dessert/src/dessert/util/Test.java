package dessert.util;

import java.util.ArrayList;

/** 
 * @author 严顺宽 
 * @date 2016年3月8日 下午1:37:50
 */
public class Test {
	public static void main(String[] args) {
		ArrayList<String> a1=new ArrayList<String>();
		a1.add("q");
		a1.add("f");
		ArrayList<String> a2=new ArrayList<String>();
		a2.add("2");
		a2.add("3");
		
		a1.addAll(a2);
		for(String s : a1){
			System.out.println(s);
		}
		
	}
}
