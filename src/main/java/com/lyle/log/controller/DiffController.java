package com.lyle.log.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		u2.setComment("this is user2");
		u2.setDio("英俊潇洒");
		u2.setId(2);
		u2.setName("zhangsan");
		u2.setSalary(120.2f);
		CompareUtil.compareObj(u1, u2, model);
		return "diff";
	}
}
