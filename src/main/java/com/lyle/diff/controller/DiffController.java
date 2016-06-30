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
		return "diff_obj";
	}
	
	@RequestMapping(value="/diffs",method=RequestMethod.GET)
	public String diffs(Model model){
		String json = "{\"success\":true,\"biz_no\":\"ZM201606301613172636100031189761\",\"ivs_detail\":[{\"code\":\"CERTNO_Missing\",\"description\":\"无法找到身份证号的有效记录\"},{\"code\":\"NAME_Match_Sharing_Good\",\"description\":\"姓名真实，匹配后的信息被一个用户使用\"},{\"code\":\"PHONE_Match_Recency_Good\",\"description\":\"常用的电话号码，近期较活跃\"}],\"ivs_score\":52,\"result_code\":\"IVS1001\"}";
		Map<String,Object> m1 = JSON.parseObject(json, Map.class);
		String json2 = "{\"success\":true,\"biz_no\":\"ZM201606301613357566500031149465\",\"ivs_detail\":[{\"code\":\"CERTNO_Missing\",\"description\":\"无法找到身份证号的有效记录\"},{\"code\":\"NAME_Mismatch\",\"description\":\"姓名与其他信息不匹配\"},{\"code\":\"PHONE_Mismatch\",\"description\":\"电话号码与其他信息不匹配\"}],\"ivs_score\":22,\"result_code\":\"IVS1001\"}";
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
