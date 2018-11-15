package cn.yu2.baomihua.web.controller.openapi;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.yu2.baomihua.openapi.module.IHxapiModule;
import cn.yu2.baomihua.util.MD5Util;
import cn.yu2.baomihua.web.JsonResult;
import cn.yu2.baomihua.web.controller.BaseController;
import cn.yu2.baomihua.web.controller.openapi.task.MsgHistoryTask;
import cn.yu2.baomihua.web.controller.openapi.validate.ParamValidate;

@Controller
@RequestMapping("/billapi/")
public class AisinoController extends BaseController {

	@Autowired
	private IHxapiModule hxapiModule;
	// 第三方平台系统测试虚拟税号
	public static String userName = "1101011010DSF00002";
	// 平台注册码（无对应字段，用于password校验和Hmac校验）
	public static String hxpassword = "29829998";
	// 第三方平台编码（RequestCode）：
	public static String requestCode = "DSF00002";
	// 测试税号（TaxpayerId）
	// public static String taxpayerId = "911403016666666666";
	// 纳税人授权码（AuthorizationCode）：
	public static String authorizationCode = "W1K02MHO69";

	public static String redirectUrl = "http://60.194.106.83:10000/login.htm";

	@RequestMapping(value = "/openbill")
	public String openbill(String taxpayerId,String sign) {
		logger.info("openbill-------------------->begin"+taxpayerId+"----"+sign);
		String param = "";
		JsonResult result = null;

		/**************** 验证结束 ************************/
		String token = "";
		try {
			if(taxpayerId == null || "".equals(taxpayerId)){
				return super.redirectTo("/error/404.html");
			}
			if(sign == null || "".equals(sign)){
				return super.redirectTo("/error/404.html");
			}
			
			String password_temp = MD5Util.getMD5(taxpayerId+"gxcloudlinkin");
			logger.info("openbill-------------------->begin"+password_temp);
			if(!sign.equals(password_temp)){
				return super.redirectTo("/error/404.html");
			}
			
			Map<String, Object> map = hxapiModule.getToken(userName, hxpassword, requestCode, taxpayerId,
					authorizationCode);
			if (map.get("token") == null || "".equals((String) map.get("token")) ) {
				return super.redirectTo("/error/404.html");
			}
			token = (String) map.get("token");

		} catch (Exception e) {
			e.printStackTrace();
			result = retsuccess(1003, "pushData为空或者格式錯誤", "");
		}
		
		if (token== null || "".equals(token) ) {
			return super.redirectTo("/error/404.html");
		}

		result = this.retsuccess(0, "success", "");
		// 记录查询日志
		return super.redirectTo(redirectUrl + "?token=" + token);
	}

}
