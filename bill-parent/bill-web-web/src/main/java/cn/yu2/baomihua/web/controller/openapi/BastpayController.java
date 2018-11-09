package cn.yu2.baomihua.web.controller.openapi;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.yu2.baomihua.openapi.entity.bastpay.BastCompanyDetil;
import cn.yu2.baomihua.openapi.entity.bastpay.BastServerCompany;
import cn.yu2.baomihua.openapi.module.IBastpayModule;
import cn.yu2.baomihua.openapi.module.ICompanyModule;
import cn.yu2.baomihua.web.JsonResult;
import cn.yu2.baomihua.web.controller.BaseController;
import cn.yu2.baomihua.web.controller.openapi.task.MsgHistoryTask;
import cn.yu2.baomihua.web.controller.openapi.validate.ParamValidate;

@Controller
@RequestMapping("/bastpay/")
public class BastpayController extends BaseController {

	@Autowired
	private ICompanyModule companyModule;

	@Autowired
	private IBastpayModule bastpayModule;

	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public JsonResult search() {

		logger.info("search");
		String param = "";

		JsonResult result = null;

		String msgId = "";
		String channel = "";
		try {
			// 获取参数
			param = getParamByReader();
			result = ParamValidate.pushdataValidate(param);
			if (result != null) {
				return result;
			}

			JSONObject json = JSONObject.parseObject(param);
			channel = json.getString("channel");
			msgId = json.getString("msgId");

			/**************** 验证结束 ************************/

		} catch (Exception e) {
			e.printStackTrace();
			result = this.retsuccess(1001, "参数为非json格式", "");
			return result;
		}

		// 查询
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("msgId", msgId);
		resultMap.put("channel", channel);

		result = this.retsuccess(0, "success", resultMap);
		// 记录查询日志
		new MsgHistoryTask(channel, msgId, param, JSONObject.toJSON(resultMap).toString(), "", 0, companyModule).run();
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/addCompany", method = RequestMethod.POST)
	public JsonResult addCompany() {

		logger.info("search");
		String param = "";

		JsonResult result = null;

		String msgId = "";
		String channel = "";

		Map<String, Object> resultMap = null;
		try {
			// 获取参数
			param = getParamByReader();
			result = ParamValidate.addCompanyValidate(param);
			if (result != null) {
				return result;
			}

			JSONObject json = JSONObject.parseObject(param);
			channel = json.getString("channel");
			msgId = json.getString("msgId");

			BastServerCompany bastServerCompany = json.toJavaObject(BastServerCompany.class);
			resultMap = bastpayModule.addBastpayCompany(bastServerCompany);

			/**************** 验证结束 ************************/

		} catch (Exception e) {
			e.printStackTrace();
			result = this.retsuccess(1001, "参数为非json格式", "");
			return result;
		}

		// 查询
		resultMap.put("msgId", msgId);
		resultMap.put("channel", channel);

		result = this.retsuccess(0, "success", resultMap);
		// 记录查询日志
		new MsgHistoryTask(channel, msgId, param, JSONObject.toJSON(resultMap).toString(), "", 0, companyModule).run();
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/editCompany", method = RequestMethod.POST)
	public JsonResult editCompany() {

		logger.info("search");
		String param = "";

		JsonResult result = null;

		String msgId = "";
		String channel = "";
		try {
			// 获取参数
			param = getParamByReader();
			result = ParamValidate.pushdataValidate(param);
			if (result != null) {
				return result;
			}

			JSONObject json = JSONObject.parseObject(param);
			channel = json.getString("channel");
			msgId = json.getString("msgId");

			/**************** 验证结束 ************************/

		} catch (Exception e) {
			e.printStackTrace();
			result = this.retsuccess(1001, "参数为非json格式", "");
			return result;
		}

		// 查询
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("msgId", msgId);
		resultMap.put("channel", channel);

		result = this.retsuccess(0, "success", resultMap);
		// 记录查询日志
		new MsgHistoryTask(channel, msgId, param, JSONObject.toJSON(resultMap).toString(), "", 0, companyModule).run();
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/addDetailInfo", method = RequestMethod.POST)
	public JsonResult addDetailInfo() {

		logger.info("search");
		String param = "";

		JsonResult result = null;

		String msgId = "";
		String channel = "";
		try {
			// 获取参数
			param = getParamByReader();
			result = ParamValidate.addDetailInfoValidate(param);
			if (result != null) {
				return result;
			}

			JSONObject json = JSONObject.parseObject(param);
			BastCompanyDetil bastCompanyDetil = json.toJavaObject(BastCompanyDetil.class);
			bastpayModule.mergeBasePayDetaiInfo(bastCompanyDetil);
			channel = json.getString("channel");
			msgId = json.getString("msgId");

			/**************** 验证结束 ************************/

		} catch (Exception e) {
			e.printStackTrace();
			result = this.retsuccess(1001, "参数为非json格式", "");
			return result;
		}

		// 查询
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("msgId", msgId);
		resultMap.put("channel", channel);

		result = this.retsuccess(0, "success", resultMap);
		// 记录查询日志
		new MsgHistoryTask(channel, msgId, param, JSONObject.toJSON(resultMap).toString(), "", 0, companyModule).run();
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/editDetailInfo", method = RequestMethod.POST)
	public JsonResult editDetailInfo() {

		logger.info("search");
		String param = "";

		JsonResult result = null;

		String msgId = "";
		String channel = "";
		try {
			param = getParamByReader();
			result = ParamValidate.addDetailInfoValidate(param);
			if (result != null) {
				return result;
			}

			JSONObject json = JSONObject.parseObject(param);
			BastCompanyDetil bastCompanyDetil = json.toJavaObject(BastCompanyDetil.class);
			bastpayModule.mergeBasePayDetaiInfo(bastCompanyDetil);
			channel = json.getString("channel");
			msgId = json.getString("msgId");


			/**************** 验证结束 ************************/

		} catch (Exception e) {
			e.printStackTrace();
			result = this.retsuccess(1001, "参数为非json格式", "");
			return result;
		}

		// 查询
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("msgId", msgId);
		resultMap.put("channel", channel);

		result = this.retsuccess(0, "success", resultMap);
		// 记录查询日志
		new MsgHistoryTask(channel, msgId, param, JSONObject.toJSON(resultMap).toString(), "", 0, companyModule).run();
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/paycallback", method = RequestMethod.POST)
	public Map<String, Object> paycallback() {

		logger.info("paycallback");
		String param = "";

		Map<String, Object> result = new HashMap<String, Object>();

		String msgId = "";
		String channel = "";
		try {
			// 获取参数
			param = getParamByReader();

			logger.info("paycallback--------------" + param);
			result = ParamValidate.callBackValidate(param);

			if (result != null) {
				return result;
			}

			result = bastpayModule.bastPayCallBack(param);
			/**************** 验证结束 ************************/

		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> ret = new HashMap<String, Object>();
			ret.put("statusCode", 502);
			ret.put("outTradeNo", "");
			ret.put("tradeNo", "");
			result = ParamValidate.returnInfo(true, "API505", "参数错误", ret);
			return result;
		}

		// 记录查询日志
		new MsgHistoryTask(channel, msgId, param, JSONObject.toJSON(result).toString(), "", 0, companyModule).run();
		return result;
	}

}
