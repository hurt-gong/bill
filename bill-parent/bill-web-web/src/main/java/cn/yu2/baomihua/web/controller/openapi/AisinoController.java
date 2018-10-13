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

import com.alibaba.fastjson.JSONObject;

import cn.yu2.baomihua.constant.CompanyConstant;
import cn.yu2.baomihua.openapi.module.ICompanyModule;
import cn.yu2.baomihua.util.AESEncryptUtil;
import cn.yu2.baomihua.util.ParamUtil;
import cn.yu2.baomihua.web.JsonResult;
import cn.yu2.baomihua.web.controller.BaseController;

@Controller
@RequestMapping("/openapi/")
public class AisinoController extends BaseController {

	@Autowired
	private ICompanyModule companyModule;

	@ResponseBody
	@RequestMapping(value = "/pushdata" , method = RequestMethod.POST)
	public JsonResult pushdata() {

		logger.info("pushdata");
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
			
			try{
			JSONObject data = json.getJSONObject("pushData");
			
			logger.info(data.toString());
			
			}catch(Exception e){
				e.printStackTrace();
				result = this.retsuccess(1003, "pushData为空或者格式錯誤", "");
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
			String signature_temp = ParamUtil.toAisinoParam(map);
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

		Map<String, Object> resultMap = new HashMap<String, Object>();
		//增值税开票信息

		String[] month = new String[] { "201610", "201611", "201612", "201701", "201702", "201703", "201704",
				"201705", "201706", "201707", "201708", "201709", "201710", "201711", "201712", "201801", "201802",
				"201803", "201804", "201805", "201806", "201807", "201808", "2017809" };
		List<Map<String, Object>> ticketInformationList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < month.length; i++) {
			Map<String, Object> ticketInformation_everyDate = new HashMap<String, Object>();
			ticketInformation_everyDate.put("ticketInformationDate", month[i]);
			// 开票销售额
			ticketInformation_everyDate.put("salesVolume", "120000.00");
			// 开票销售额同比
			ticketInformation_everyDate.put("salesYearOnYear", "17.9%");
			// 开票销售额环比
			ticketInformation_everyDate.put("salesLink", "17.9%");
			// 废票金额
			ticketInformation_everyDate.put("discardTicketSales", "0");
			// 废票金额
			ticketInformation_everyDate.put("percentDiscardTicketSales", "0.0%");
			// 开票数量
			ticketInformation_everyDate.put("countTicket", "100000");
			// 红废票数量
			ticketInformation_everyDate.put("countDiscardTicket", "0");
			// 红废票数量占比
			ticketInformation_everyDate.put("percentDiscardTicket", "0.0");
			ticketInformationList.add(ticketInformation_everyDate);
			//ticketInformation.put(month[i], ticketInformation_everyDate);
		}

		String ticketRecord = "12天";
		//下游客户概况
		List<Map<String, Object>> customerList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < month.length; i++) {
			Map<String, Object> customer_everyDate = new HashMap<String, Object>();
			customer_everyDate.put("customerDate", month[i]);
			//全部开票客户数（户）
			customer_everyDate.put("countCustomer", "10000");
			//主要客户（前5）销售额（万元）
			customer_everyDate.put("customerSalesVolume", "10000");
			//前5大客户销售额占比(%)
			customer_everyDate.put("percentCustomerSales", "10%");
			//前5大客户开票次数（次）
			customer_everyDate.put("countCustomerTicket", "10000");
			//前5大客户开票次数占比(%)
			customer_everyDate.put("percentCustomerTicket", "10%");
			customerList.add(customer_everyDate);
			//customer.put(month[i], customer_everyDate);
		}
		resultMap.put("ticketInformation", ticketInformationList);
		resultMap.put("ticketRecord", ticketRecord);
		resultMap.put("customer", customerList);
		resultMap.put("msgId", msgId);
		resultMap.put("channel", channel);

		result = this.retsuccess(0, "success", resultMap);

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
