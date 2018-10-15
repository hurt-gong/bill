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

@Controller
@RequestMapping("/openapi/")
public class BastpayController extends BaseController {

	@Autowired
	private ICompanyModule companyModule;

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
			logger.info(param);
			if (param == null || "".equals(param)) {
				result = this.retsuccess(1000, "参数传递为空", "");
				return result;
			}
			// 验证参数格式
			JSONObject json = null;
			try {
				json = JSONObject.parseObject(param);
			} catch (Exception e) {
				result = this.retsuccess(1001, "参数为非json格式", "");
				return result;
			}

			channel = json.getString("channel");
			if (StringUtils.isEmpty(channel)) {
				result = this.retsuccess(1002, "channel为空", "");
				return result;
			}
			String secretkey = json.getString("secretkey");
			if (StringUtils.isEmpty(secretkey)) {
				result = this.retsuccess(1002, "secretkey为空", "");
				return result;
			}
			String version = json.getString("version");
			if (StringUtils.isEmpty(version)) {
				result = this.retsuccess(1002, "version为空", "");
				return result;
			}
			msgId = json.getString("msgId");
			if (StringUtils.isEmpty(msgId)) {
				result = this.retsuccess(1002, "msgId为空", "");
				return result;
			}
			String timeStamp = json.getString("timeStamp");
			if (StringUtils.isEmpty(timeStamp)) {
				result = this.retsuccess(1002, "timeStamp为空", "");
				return result;
			}
			String signature = json.getString("signature");
			if (StringUtils.isEmpty(signature)) {
				result = this.retsuccess(1002, "signature为空", "");
				return result;
			}

			// 验证channel是否正确
			CompanyConstant t = new CompanyConstant();
			if (StringUtils.isEmpty(t.COMPANY.get(channel))) {
				result = this.retsuccess(1003, "channel错误", "");
				return result;
			}
			// 验证secretkey是否正确
			String companyKeys = t.COMPANY.get(channel);
			String key_tem = AESEncryptUtil.aesEncrypt(channel, AESEncryptUtil.getAESKey(companyKeys));
			if (!secretkey.equals(key_tem)) {
				result = this.retsuccess(1003, "secretkey错误", "");
				return result;
			}

			// 验证签名是否正确
			Map<String, Object> map = json.toJavaObject(Map.class);
			String signature_temp = ParamUtil.decryptParam(map);
			if (!signature.equals(signature_temp)) {
				result = this.retsuccess(1003, "signature错误", "");
				return result;
			}

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
		new MsgHistoryTask(channel,msgId,param,JSONObject.toJSON(resultMap).toString(),"",0,companyModule).run();
		return result;
	}

	// 获取参数
	public String getParamByReader() {
		String registInfo = "";
		try {

			BufferedReader bufferReader = this.request.getReader();
			StringBuffer buffer = new StringBuffer();
			String line = " ";
			while ((line = bufferReader.readLine()) != null) {
				buffer.append(line);
			}
			registInfo = buffer.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return registInfo;
	}
}
