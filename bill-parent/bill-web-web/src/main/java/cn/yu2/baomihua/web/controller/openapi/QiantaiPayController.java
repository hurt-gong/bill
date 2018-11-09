package cn.yu2.baomihua.web.controller.openapi;

import java.io.BufferedReader;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.yu2.baomihua.constant.CompanyConstant;
import cn.yu2.baomihua.openapi.module.ICompanyModule;
import cn.yu2.baomihua.util.AESEncryptUtil;
import cn.yu2.baomihua.util.ParamUtil;
import cn.yu2.baomihua.web.JsonResult;
import cn.yu2.baomihua.web.controller.BaseController;
import cn.yu2.baomihua.web.controller.openapi.task.MsgHistoryTask;
import cn.yu2.baomihua.web.controller.openapi.validate.ParamValidate;

@Controller
@RequestMapping("/qiantai")
public class QiantaiPayController extends BaseController {

	@Autowired
	private ICompanyModule companyModule;

	@ResponseBody
	@RequestMapping(value = "/paycallback", method = RequestMethod.POST)
	public JsonResult paycallback() {

		logger.info("search");
		String param = "";

		JsonResult result = null;

		String msgId = "";
		String channel = "";
		try {
			// 获取参数
			param = getParamByReader();
			result = ParamValidate.pushdataValidate(param);
			if(result != null){
				return result;
			}
			
			JSONObject json =  JSONObject.parseObject(param);
			channel = json.getString("channel");
			msgId = json.getString("msgId");

			/**************** 验证结束 ************************/

		} catch (Exception e) {
			e.printStackTrace();
			result = this.retsuccess(1001, "参数为非json格式", "");
			return result;
		}

		// 查询
		Map<String, Object> resultMap = companyModule.search("");
		resultMap.put("msgId", msgId);
		resultMap.put("channel", channel);

		result = this.retsuccess(0, "success", resultMap);
		// 记录查询日志
		new MsgHistoryTask(channel, msgId, param, JSONObject.toJSON(resultMap).toString(), "", 0, companyModule).run();
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	public JsonResult pay() {

		logger.info("search");
		String param = "";

		JsonResult result = null;

		 
		try {
			
			String appCode = this.request.getHeader("X-QF-APPCODE");
			String sign = this.request.getHeader("X-QF-SIGN");
			
			System.out.println(appCode+"------------------------appCode");
			System.out.println(sign+"------------------------sign");
			// 获取参数
			param = getParamByReader();
			result = ParamValidate.pushdataValidate(param);
			if(result != null){
				return result;
			}
			
			JSONObject json =  JSONObject.parseObject(param);
		 

			/**************** 验证结束 ************************/

		} catch (Exception e) {
			e.printStackTrace();
			result = this.retsuccess(1001, "参数为非json格式", "");
			return result;
		}

		// 查询
	 
		return result;
	}

}
