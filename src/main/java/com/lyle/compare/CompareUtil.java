package com.lyle.compare;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

import com.lyle.user.User;

public class CompareUtil {

	/**
	 * 比较两个对象属性值异同
	 * @param obj1 
	 * @param obj2
	 * 应用场景：比较前后两次修改的差别
	 */
	public static void compareObj(Object obj1,Object obj2,Model model){
		String bcolor = "<font size='4' color='green'>";
		String ecolor = "</font>";
		if(obj1 instanceof User && obj2 instanceof User){
			User u1 = (User)obj1;
			User u2 = (User)obj2;
			List<String[]> val = new ArrayList<String[]>();
			try {
				Class<?> clazz = obj1.getClass();
				Field[] fields = clazz.getDeclaredFields();
				for(Field field : fields){
					PropertyDescriptor pd = new PropertyDescriptor(field.getName(),clazz);
					Method getMethod = pd.getReadMethod();
					Object o1 = getMethod.invoke(u1);
					Object o2 = getMethod.invoke(u2);
					//
					String o1s = o1.toString();
					String o2s = o2.toString();
					if(!o1s.equals(o2s)){
						String[] diff = compareStr(o1s,o2s);
						diff[0] = getMethod.getName().substring(3) + ":" + diff[0];
						diff[1] = getMethod.getName().substring(3) + ":" + diff[1];
						val.add(diff);
					}else{
						//只显示有改动的项，没有改动的这里不显示处理
						String[] same = new String[2];
						same[0] = getMethod.getName().substring(3) + ":" + bcolor + o1s + ecolor;
						same[1] = getMethod.getName().substring(3) + ":" + bcolor + o2s + ecolor;
						val.add(same);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
			model.addAttribute("val", val);
		}
	}
	
	/**
	 * 
	 * @param tmp1 原始
	 * @param tmp2 改动之后
	 * @return
	 */
	public static String[] compareStr(String tmp1, String tmp2) {
		  String bcolor = "<font size='4' color='red'>";
		  String ecolor = "</font>";
		  char[] a = new char[tmp1.length()];
		  for (int i = 0; i < tmp1.length(); i++) {
		   a[i] = tmp1.charAt(i);
		  }
		  char[] b = new char[tmp2.length()];
		  for (int i = 0; i < tmp2.length(); i++) {
		   b[i] = tmp2.charAt(i);
		  }
		  StringBuffer ret1 = new StringBuffer();
		  StringBuffer ret2 = new StringBuffer();
		  if (tmp1.length() > tmp2.length()) {
		   for (int i = 0; i < a.length; i++) {
		    if (i < tmp2.length()) {
		     if (a[i] == b[i]) {
		      ret1.append(a[i]);
		      ret2.append(b[i]);
		     } else {
		      ret1.append(bcolor).append(a[i]).append(ecolor);
		      ret2.append(bcolor).append(b[i]).append(ecolor);
		     }
		    } else {
		     ret2.append(bcolor).append(a[i]).append(ecolor);
		    }
		   }
		  } else if (tmp1.length() <= tmp2.length()) {
		   for (int i = 0; i < b.length; i++) {
		    if (i < tmp1.length()) {
		     if (a[i] == b[i]) {
		      ret1.append(a[i]);
		      ret2.append(b[i]);
		     } else {
		      ret1.append(bcolor).append(a[i]).append(ecolor);
		      ret2.append(bcolor).append(b[i]).append(ecolor);
		     }
		    } else {
		     ret2.append(bcolor).append(b[i]).append(ecolor);
		    }
		   }
		  }
		  String[] ret = new String[2];
		  ret[0] = ret1.toString();
		  ret[1] = ret2.toString();
		  return ret;
		 }
}
