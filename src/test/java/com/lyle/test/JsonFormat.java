package com.lyle.test;

import com.lyle.compare.CompareUtil;
import com.lyle.utils.JsonFormatUtil;

public class JsonFormat {

	public static void main(String[] args) {
		String json = "{\"success\":true,\"biz_no\":\"ZM201606301153313368800031065048\",\"ivs_detail\":[{\"code\":\"CERTNO_Missing\",\"description\":\"无法找到身份证号的有效记录\"},{\"code\":\"NAME_Match_Sharing_Good\",\"description\":\"姓名真实，匹配后的信息被一个用户使用\"},{\"code\":\"PHONE_Match_Recency_Good\",\"description\":\"常用的电话号码，近期较活跃\"}],\"ivs_score\":52,\"result_code\":\"IVS1001\"}";
		String result = JsonFormatUtil.formatJson(json);
		System.out.println(result);
		
		String json2 = "{\"success\":true,\"biz_no\":\"ZM201606301258212752100031078873\",\"ivs_detail\":[{\"code\":\"CERTNO_Missing\",\"description\":\"无法找到身份证号的有效记录\"},{\"code\":\"NAME_Mismatch\",\"description\":\"姓名与其他信息不匹配\"},{\"code\":\"PHONE_Mismatch\",\"description\":\"电话号码与其他信息不匹配\"}],\"ivs_score\":22,\"result_code\":\"IVS1001\"}";
		String result2 = JsonFormatUtil.formatJson(json2);
		CompareUtil.compareStr(result, result2);
	}
}
