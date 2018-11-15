package cn.yu2.baomihua.web.controller.openapi;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.yu2.baomihua.openapi.module.ICompanyModule;
import cn.yu2.baomihua.openapi.module.IQtPayModule;
import cn.yu2.baomihua.web.JsonResult;
import cn.yu2.baomihua.web.controller.BaseController;
import cn.yu2.baomihua.web.controller.openapi.task.MsgHistoryTask;
import cn.yu2.baomihua.web.controller.openapi.validate.ParamValidate;

@Controller
@RequestMapping("/qiantai")
public class QiantaiPayController extends BaseController {

	@Autowired
	private ICompanyModule companyModule;

	@Autowired
	private IQtPayModule qtPayModule;

	@ResponseBody
	@RequestMapping(value = "/paycallback", method = RequestMethod.POST)
	public JsonResult paycallback() {

		logger.info("search");
		String param = "";

		JsonResult result = null;

		String msgId = "";
		String channel = "";
		try {

			String appCode = this.request.getHeader("X-QF-APPCODE");
			String sign = this.request.getHeader("X-QF-SIGN");
			System.out.println(appCode + "------------------------appCode");
			System.out.println(sign + "------------------------sign");
			// 获取参数
			param = getParamByReader();
			JSONObject json = JSONObject.parseObject(param);
			Map<String, Object> repmap = json.toJavaObject(Map.class);

			Map<String, Object> respmap = qtPayModule.callBack(repmap, sign);

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

		logger.info("pay");
		String param = "";
		JsonResult result = null;

		try {
			String appCode = this.request.getHeader("X-QF-APPCODE");
			String sign = this.request.getHeader("X-QF-SIGN");
			System.out.println(appCode + "------------------------appCode");
			System.out.println(sign + "------------------------sign");
			// 获取参数
			param = getParamByReader();
			Map<String, Object> parmap = new HashMap<String, Object>();
			qtPayModule.proCreateOrder(parmap);
			result = ParamValidate.pushdataValidate(param);
			if (result != null) {
				return result;
			}

			JSONObject json = JSONObject.parseObject(param);

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
