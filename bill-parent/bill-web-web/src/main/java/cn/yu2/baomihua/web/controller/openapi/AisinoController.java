package cn.yu2.baomihua.web.controller.openapi;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
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
@RequestMapping("/openapi/")
public class AisinoController extends BaseController {

	@Autowired
	private ICompanyModule companyModule;

	@ResponseBody
	@RequestMapping(value = "/pushdata", method = RequestMethod.POST)
	public JsonResult pushdata() {
		logger.info("pushdata-------------------->begin");
		String param = "";
		JsonResult result = null;
		String msgId = "";
		String channel = "";
		try {
			// 获取参数
			param = getParamByReader();
			logger.info(param);

			result = ParamValidate.pushdataValidate(param);
			if (result != null) {
				return result;
			}

			JSONObject json = JSONObject.parseObject(param);
			channel = json.getString("channel");
			msgId = json.getString("msgId");

			/**************** 验证结束 ************************/

			try {
				JSONArray pushData = json.getJSONArray("pushData");
				companyModule.savePushData(pushData, msgId);

			} catch (Exception e) {
				e.printStackTrace();
				result = retsuccess(1003, "pushData为空或者格式錯誤", "");
				return result;
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = this.retsuccess(1001, "解析数据有误", "");
			return result;
		}

		// 查询
		Map<String, Object> resultMap = new HashMap<String,Object>();
		resultMap.put("msgId", msgId);
		resultMap.put("channel", channel);

		result = this.retsuccess(0, "success", resultMap);
		// 记录查询日志
		new MsgHistoryTask(channel, msgId, param, JSONObject.toJSON(resultMap).toString(), "", 0, companyModule).run();
		return result;
	}

}
