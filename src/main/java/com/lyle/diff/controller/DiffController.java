package com.lyle.diff.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.lyle.compare.CompareUtil;
import com.lyle.user.User;

@Controller
public class DiffController {

	@RequestMapping(value="/diff",method=RequestMethod.GET)
	public String diff(Model model){
		User u1 = new User();
		u1.setComment("this is user1");
		u1.setDio("风流倜傥");
		u1.setId(1);
		u1.setName("zhangsan");
		u1.setSalary(120.2f);
		User u2 = new User();
		u2.setComment("this is user2 lyle test");
		u2.setDio("风流倜傥英俊潇洒气宇轩昂");
		u2.setId(2);
		u2.setName("zhangsan");
		u2.setSalary(120.2f);
		List<String[]>  val = CompareUtil.compareObj(u1, u2);
		model.addAttribute("val", val);
		//System.out.println(JSON.toJSONString(u2));
		return "diff_obj";
	}
	
	@RequestMapping(value="/diffs",method=RequestMethod.GET)
	@SuppressWarnings("unchecked")
	public String diffs(Model model){
		String json = "{\"comment\":\"this is json1  test\",\"dio\":\"风花倜傥愁眉苦脸\",\"id\":1,\"name\":\"lisan\",\"salary\":120.5}";
		Map<String,Object> m1 = JSON.parseObject(json, Map.class);
		String json2 = "{\"comment\":\"this is json2 test case\",\"dio\":\"风流倜傥英俊潇洒气宇轩昂\",\"id\":2,\"name\":\"zhangsan\",\"salary\":120.2}";
		Map<String,Object> m2 = JSON.parseObject(json2, Map.class);
		List<String[]> val = new ArrayList<String[]>();
		for(Map.Entry<String, Object> en :m1.entrySet()){
			String m1_key = en.getKey();
			Object m1_value = en.getValue();
			Object m2_value = m2.get(m1_key);
			String[] comResult = CompareUtil.compareStr(m1_value.toString(), m2_value.toString());
			comResult[0] = m1_key  + ":" + comResult[0];
			comResult[1] = m1_key  + ":" + comResult[1];
			val.add(comResult);
		}
		model.addAttribute("val", val);
		return "diff_str";
	}
}
