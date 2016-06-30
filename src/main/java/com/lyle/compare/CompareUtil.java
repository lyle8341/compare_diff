package com.lyle.compare;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.lyle.user.User;

public class CompareUtil {

	/**
	 * 比较两个User对象属性值异同(这里明确了是User对象，因为代码写死的)
	 * @param obj1 
	 * @param obj2
	 * 应用场景：比较前后两次修改的差别
	 */
	public static List<String[]> compareObj(Object obj1,Object obj2){
		String bcolor = "<font size='4' color='green'>";
		String ecolor = "</font>";
		List<String[]> val = new ArrayList<String[]>();
		if(obj1 instanceof User && obj2 instanceof User){
			User u1 = (User)obj1;
			User u2 = (User)obj2;
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
						//绿色显示相同内容
						String[] same = new String[2];
						same[0] = getMethod.getName().substring(3) + ":" + bcolor + o1s + ecolor;
						same[1] = getMethod.getName().substring(3) + ":" + bcolor + o2s + ecolor;
						val.add(same);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return val;
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
		  //计数器，目的是第一次访问的时候加头标签
		  int count = 0;
		  //tmp1长度大
		  if (tmp1.length() > tmp2.length()) {
		   for (int i = 0; i < a.length; i++) {
		    if (i < tmp2.length()) {
		     if (a[i] == b[i]) {
		      ret1.append(a[i]);
		      ret2.append(b[i]);
		     } else {
		    	 //合并相同字符，一起加font
		    	if(count ==0){
				   ret1.append(bcolor);
				   ret2.append(bcolor);
				   count++;
		    	}
		    	 ret1.append(a[i]);
		    	 ret2.append(b[i]);
		    	 if(i < tmp2.length()-1){
		    	 if(a[i+1] == b[i+1]){
		    		 ret1.append(ecolor);
		    		 ret2.append(ecolor);
		    		 count = 0;
		    	 }
		    	 }else{
		    		 ret1.append(ecolor);
		    		 ret2.append(ecolor);
		    		 count = 0;
		    	 }
		     }
		    } else {
		    	//合并不同字符，一起加一个font标签
		    	if(i == tmp2.length()){
		    		ret1.append(bcolor).append(a[i]);
		    	}else if(i == tmp1.length()-1){
		    		ret1.append(a[i]).append(ecolor);
		    	}else{
		    		ret1.append(a[i]);
		    	}
		    }
		   }
		  } else if (tmp1.length() <= tmp2.length()) {
		   for (int i = 0; i < b.length; i++) {
		    if (i < tmp1.length()) {
		     if (a[i] == b[i]) {
		      ret1.append(a[i]);
		      ret2.append(b[i]);
		     } else {
		    	 //合并不同字符，一起加font
		    	 if(count ==0){
					   ret1.append(bcolor);
					   ret2.append(bcolor);
					   count++;
			    	}
			    	 ret1.append(a[i]);
			    	 ret2.append(b[i]);
			    	 if(i < tmp1.length()-1){
			    		 if(a[i+1] == b[i+1]){
				    		 ret1.append(ecolor);
				    		 ret2.append(ecolor);
				    		 count = 0;
				    	 }
			    	 }else{
			    		 ret1.append(ecolor);
			    		 ret2.append(ecolor);
			    		 count = 0;
			    	 }
		     }
		    } else {
		    	if(i == tmp1.length()){
		    		ret2.append(bcolor).append(b[i]);
		    	}else if(i == tmp2.length()-1){
		    		ret2.append(b[i]).append(ecolor);
		    	}else{
		    		ret2.append(b[i]);
		    	}
		    }
		   }
		  }
		  String[] ret = new String[2];
		  ret[0] = ret1.toString();
		  ret[1] = ret2.toString();
//		  System.out.println(ret[0]);
//		  System.out.println(ret[1]);
		  return ret;
		 }
}
