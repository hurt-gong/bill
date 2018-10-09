package cn.yu2.baomihua.core.common.util;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.yu2.baomihua.manage.dto.ResponseDTO;
import cn.yu2.baomihua.manage.enums.ResponseHeadCodeEnum;

/**
 * 验证数据正确性
 * 
 * @author lizhixiao
 * @date 2016-06-14
 */


public class ValidateUtil {

	/**身份证校验*/
	public static ResponseDTO validateIDCard( String idCard ) {
		idCard = idCard.toUpperCase();
		try {
			return IDCardUtil.IDCardValidate(idCard);
		} catch ( ParseException e ) {
			ResponseDTO response = new ResponseDTO();
			response.setCode(ResponseHeadCodeEnum.ERROR.key());
			response.setMassage("身份证校验错误");
			return response;
		}
	}


	/**手机校验*/
	public static ResponseDTO validateMobil( String phoneNum ) {
		Pattern p = null;
		Matcher m = null;
		boolean flag = false;
		p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号  
		m = p.matcher(phoneNum);
		flag = m.matches();

		ResponseDTO response = new ResponseDTO();
		if ( flag == true ) {
			response.setCode(ResponseHeadCodeEnum.SUCCESS.key());
			response.setMassage("手机号校验成功");
		} else {
			response.setCode(ResponseHeadCodeEnum.ERROR.key());
			response.setMassage("手机号校验错误");
		}
		return response;
	}
}