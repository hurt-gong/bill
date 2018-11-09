/**  
* <p>Title: ParamValidate.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2017</p>  
* <p>Company: baomihua</p>  
* @author hurt-gong
* @date 2018年10月15日  
* @version 1.0  
*/
package cn.yu2.baomihua.web.controller.openapi.validate;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

import cn.yu2.baomihua.constant.CompanyConstant;
import cn.yu2.baomihua.util.AESEncryptUtil;
import cn.yu2.baomihua.util.ParamUtil;
import cn.yu2.baomihua.web.JsonResult;

/**
 * <p>
 * Title: ParamValidate
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author hurt-gong
 * @date 2018年10月15日
 */
public class ParamValidate {

	/**
	 * pushdata参数的验证
	 * <p>
	 * Title: pushdataValidate
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param param
	 * @return
	 */
	public static JsonResult pushdataValidate(String param) {

		JsonResult result = null;

		String msgId = "";
		String channel = "";
		try {
			// 获取参数
			if (param == null || "".equals(param)) {
				result = retsuccess(1000, "参数传递为空", "");
				return result;
			}

			// 验证参数格式
			JSONObject json = null;
			try {
				json = JSONObject.parseObject(param);
			} catch (Exception e) {
				result = retsuccess(1001, "参数为非json格式", "");
				return result;
			}

			channel = json.getString("channel");
			if (StringUtils.isEmpty(channel)) {
				result = retsuccess(1002, "channel为空", "");
				return result;
			}
			String secretkey = json.getString("secretkey");
			if (StringUtils.isEmpty(secretkey)) {
				result = retsuccess(1002, "secretkey为空", "");
				return result;
			}
			String version = json.getString("version");
			if (StringUtils.isEmpty(version)) {
				result = retsuccess(1002, "version为空", "");
				return result;
			}
			msgId = json.getString("msgId");
			if (StringUtils.isEmpty(msgId)) {
				result = retsuccess(1002, "msgId为空", "");
				return result;
			}
			String timeStamp = json.getString("timeStamp");
			if (StringUtils.isEmpty(timeStamp)) {
				result = retsuccess(1002, "timeStamp为空", "");
				return result;
			}
			String signature = json.getString("signature");
			if (StringUtils.isEmpty(signature)) {
				result = retsuccess(1002, "signature为空", "");
				return result;
			}

			// try{
			// //JSONArray pushData = json.getJSONArray("paramData");
			// JSONObject paramData = json.getJSONObject("paramData");
			//
			// }catch(Exception e){
			// e.printStackTrace();
			// result = retsuccess(1003, "paramData为空或者格式錯誤", "");
			// return result;
			// }

			// 验证channel是否正确
			CompanyConstant t = new CompanyConstant();
			if (StringUtils.isEmpty(t.COMPANY.get(channel))) {
				result = retsuccess(1003, "channel错误", "");
				return result;
			}
			// 验证secretkey是否正确
			String companyKeys = t.COMPANY.get(channel);
			String key_tem = AESEncryptUtil.aesEncrypt(channel, AESEncryptUtil.getAESKey(companyKeys));
			if (!secretkey.equals(key_tem)) {
				result = retsuccess(1003, "secretkey错误", "");
				return result;
			}

			// 验证签名是否正确
			Map<String, Object> map = json.toJavaObject(Map.class);
			String signature_temp = ParamUtil.decryptParam(map);
			if (!signature.equals(signature_temp)) {
				result = retsuccess(1003, "signature错误", "");
				return result;
			}

			/**************** 验证结束 ************************/

		} catch (Exception e) {
			e.printStackTrace();
			result = retsuccess(1001, "参数为非json格式", "");
			return result;
		}

		return null;
	}

	/**
	 * 
	 * <p>
	 * Title: retsuccess
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param code
	 * @param msg
	 * @param data
	 * @return
	 */
	private static JsonResult retsuccess(int code, String msg, Object data) {
		JsonResult js = new JsonResult();
		js.setCode(code);
		js.setData(data);
		js.setMsg(msg);
		return js;
	}

	public static JsonResult searchParamValidate(String param) {
		JsonResult result = null;

		String msgId = "";
		String channel = "";
		try {
			// 获取参数
			if (param == null || "".equals(param)) {
				result = retsuccess(1000, "参数传递为空", "");
				return result;
			}
			// 验证参数格式
			JSONObject json = null;
			try {
				json = JSONObject.parseObject(param);
			} catch (Exception e) {
				result = retsuccess(1001, "参数为非json格式", "");
				return result;
			}

			channel = json.getString("channel");
			if (StringUtils.isEmpty(channel)) {
				result = retsuccess(1002, "channel为空", "");
				return result;
			}
			String secretkey = json.getString("secretkey");
			if (StringUtils.isEmpty(secretkey)) {
				result = retsuccess(1002, "secretkey为空", "");
				return result;
			}
			String version = json.getString("version");
			if (StringUtils.isEmpty(version)) {
				result = retsuccess(1002, "version为空", "");
				return result;
			}
			msgId = json.getString("msgId");
			if (StringUtils.isEmpty(msgId)) {
				result = retsuccess(1002, "msgId为空", "");
				return result;
			}
			String timeStamp = json.getString("timeStamp");
			if (StringUtils.isEmpty(timeStamp)) {
				result = retsuccess(1002, "timeStamp为空", "");
				return result;
			}
			String signature = json.getString("signature");
			if (StringUtils.isEmpty(signature)) {
				result = retsuccess(1002, "signature为空", "");
				return result;
			}

			// 验证channel是否正确
			CompanyConstant t = new CompanyConstant();
			if (StringUtils.isEmpty(t.COMPANY.get(channel))) {
				result = retsuccess(1003, "channel错误", "");
				return result;
			}
			// 验证secretkey是否正确
			String companyKeys = t.COMPANY.get(channel);
			String key_tem = AESEncryptUtil.aesEncrypt(channel, AESEncryptUtil.getAESKey(companyKeys));
			if (!secretkey.equals(key_tem)) {
				result = retsuccess(1003, "secretkey错误", "");
				return result;
			}

			// 验证签名是否正确
			Map<String, Object> map = json.toJavaObject(Map.class);
			String signature_temp = ParamUtil.decryptParam(map);
			if (!signature.equals(signature_temp)) {
				result = retsuccess(1003, "signature错误", "");
				return result;
			}

			/**************** 验证结束 ************************/

		} catch (Exception e) {
			e.printStackTrace();
			result = retsuccess(1001, "参数为非json格式", "");
			return result;
		}

		return null;
	}

	/**
	 * pushdata参数的验证
	 * <p>
	 * Title: pushdataValidate
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param param
	 * @return
	 */
	public static JsonResult addCompanyValidate(String param) {

		JsonResult result = null;

		String msgId = "";
		String channel = "";
		try {
			// 获取参数
			if (param == null || "".equals(param)) {
				result = retsuccess(1000, "参数传递为空", "");
				return result;
			}

			// 验证参数格式
			JSONObject json = null;
			try {
				json = JSONObject.parseObject(param);
			} catch (Exception e) {
				result = retsuccess(1001, "参数为非json格式", "");
				return result;
			}

			channel = json.getString("channel");
			if (StringUtils.isEmpty(channel)) {
				result = retsuccess(1002, "channel为空", "");
				return result;
			}
			String secretkey = json.getString("secretkey");
			if (StringUtils.isEmpty(secretkey)) {
				result = retsuccess(1002, "secretkey为空", "");
				return result;
			}
			String version = json.getString("version");
			if (StringUtils.isEmpty(version)) {
				result = retsuccess(1002, "version为空", "");
				return result;
			}
			msgId = json.getString("msgId");
			if (StringUtils.isEmpty(msgId)) {
				result = retsuccess(1002, "msgId为空", "");
				return result;
			}
			String timeStamp = json.getString("timeStamp");
			if (StringUtils.isEmpty(timeStamp)) {
				result = retsuccess(1002, "timeStamp为空", "");
				return result;
			}
			String signature = json.getString("signature");
			if (StringUtils.isEmpty(signature)) {
				result = retsuccess(1002, "signature为空", "");
				return result;
			}

			// 验证channel是否正确
			CompanyConstant t = new CompanyConstant();
			if (StringUtils.isEmpty(t.COMPANY.get(channel))) {
				result = retsuccess(1003, "channel错误", "");
				return result;
			}
			// 验证secretkey是否正确
			String companyKeys = t.COMPANY.get(channel);
			String key_tem = AESEncryptUtil.aesEncrypt(channel, AESEncryptUtil.getAESKey(companyKeys));
			if (!secretkey.equals(key_tem)) {
				result = retsuccess(1003, "secretkey错误", "");
				return result;
			}

			// 验证签名是否正确
			Map<String, Object> map = json.toJavaObject(Map.class);
			String signature_temp = ParamUtil.decryptParam(map);
			if (!signature.equals(signature_temp)) {
				result = retsuccess(1003, "signature错误", "");
				return result;
			}

			/**************** 业务验证 *********************************/

			String productNo = json.getString("productNo");
			if (StringUtils.isEmpty(productNo)) {
				result = retsuccess(1002, "productNo为空", "");
				return result;
			}

			String addressee = json.getString("addressee");
			if (StringUtils.isEmpty(addressee)) {
				result = retsuccess(1002, "addressee为空", "");
				return result;
			}

			String address = json.getString("address");
			if (StringUtils.isEmpty(address)) {
				result = retsuccess(1002, "address为空", "");
				return result;
			}

			String phone = json.getString("phone");
			if (StringUtils.isEmpty(phone)) {
				result = retsuccess(1002, "phone为空", "");
				return result;
			}

			String companyName = json.getString("companyName");
			if (StringUtils.isEmpty(companyName)) {
				result = retsuccess(1002, "companyName为空", "");
				return result;
			}

			String serviceCharge = json.getString("serviceCharge");
			if (StringUtils.isEmpty(serviceCharge)) {
				result = retsuccess(1002, "serviceCharge为空", "");
				return result;
			}

			String effectDateFrom = json.getString("effectDateFrom");
			if (StringUtils.isEmpty(effectDateFrom)) {
				result = retsuccess(1002, "effectDateFrom为空", "");
				return result;
			}

			String effectDateTo = json.getString("effectDateTo");
			if (StringUtils.isEmpty(effectDateTo)) {
				result = retsuccess(1002, "effectDateTo为空", "");
				return result;
			}

			/**************** 验证结束 ************************/

		} catch (Exception e) {
			e.printStackTrace();
			result = retsuccess(1001, "参数为非json格式", "");
			return result;
		}

		return null;
	}

	/**
	 * pushdata参数的验证
	 * <p>
	 * Title: pushdataValidate
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param param
	 * @return
	 */
	public static Map<String, Object> callBackValidate(String param) {

		Map<String, Object> result = null;

		try {
			// 获取参数
			if (param == null || "".equals(param)) {
				Map<String, Object> ret = new HashMap<String, Object>();
				ret.put("statusCode", 502);
				ret.put("outTradeNo", "");
				ret.put("tradeNo", "");
				result = returnInfo(true, "API505", "参数错误", ret);
				return result;
			}

			// 验证参数格式
			JSONObject json = null;
			try {
				json = JSONObject.parseObject(param);
			} catch (Exception e) {
				Map<String, Object> ret = new HashMap<String, Object>();
				ret.put("statusCode", 502);
				ret.put("outTradeNo", "");
				ret.put("tradeNo", "");
				result = returnInfo(true, "API505", "参数错误", ret);
				return result;
			}

			/**************** 验证结束 ************************/

		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> ret = new HashMap<String, Object>();
			ret.put("statusCode", 502);
			ret.put("outTradeNo", "");
			ret.put("tradeNo", "");
			result = returnInfo(true, "API505", "参数错误", ret);
			return result;
		}

		return null;
	}

	/**
	 * 回调参数的拼接
	 * <p>
	 * Title: returnInfo
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param successinfo
	 * @param errorCode
	 * @param errorMsg
	 * @param result
	 * @return
	 */
	public static Map<String, Object> returnInfo(boolean successinfo, String errorCode, String errorMsg,
			Map<String, Object> result) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", successinfo);
		map.put("errorCode", errorCode);
		map.put("errorMsg", errorMsg);
		map.put("result", result);
		return map;
	}

	
	
	public static JsonResult addDetailInfoValidate(String param) {

		JsonResult result = null;

		String msgId = "";
		String channel = "";
		try {
			// 获取参数
			if (param == null || "".equals(param)) {
				result = retsuccess(1000, "参数传递为空", "");
				return result;
			}

			// 验证参数格式
			JSONObject json = null;
			try {
				json = JSONObject.parseObject(param);
			} catch (Exception e) {
				result = retsuccess(1001, "参数为非json格式", "");
				return result;
			}

			channel = json.getString("channel");
			if (StringUtils.isEmpty(channel)) {
				result = retsuccess(1002, "channel为空", "");
				return result;
			}
			String secretkey = json.getString("secretkey");
			if (StringUtils.isEmpty(secretkey)) {
				result = retsuccess(1002, "secretkey为空", "");
				return result;
			}
			String version = json.getString("version");
			if (StringUtils.isEmpty(version)) {
				result = retsuccess(1002, "version为空", "");
				return result;
			}
			msgId = json.getString("msgId");
			if (StringUtils.isEmpty(msgId)) {
				result = retsuccess(1002, "msgId为空", "");
				return result;
			}
			String timeStamp = json.getString("timeStamp");
			if (StringUtils.isEmpty(timeStamp)) {
				result = retsuccess(1002, "timeStamp为空", "");
				return result;
			}
			String signature = json.getString("signature");
			if (StringUtils.isEmpty(signature)) {
				result = retsuccess(1002, "signature为空", "");
				return result;
			}

			// 验证channel是否正确
			CompanyConstant t = new CompanyConstant();
			if (StringUtils.isEmpty(t.COMPANY.get(channel))) {
				result = retsuccess(1003, "channel错误", "");
				return result;
			}
			// 验证secretkey是否正确
			String companyKeys = t.COMPANY.get(channel);
			String key_tem = AESEncryptUtil.aesEncrypt(channel, AESEncryptUtil.getAESKey(companyKeys));
			if (!secretkey.equals(key_tem)) {
				result = retsuccess(1003, "secretkey错误", "");
				return result;
			}

			// 验证签名是否正确
			Map<String, Object> map = json.toJavaObject(Map.class);
			String signature_temp = ParamUtil.decryptParam(map);
			if (!signature.equals(signature_temp)) {
				result = retsuccess(1003, "signature错误", "");
				return result;
			}

			/**************** 业务验证 *********************************/

			String productNo = json.getString("productNo");
			if (StringUtils.isEmpty(productNo)) {
				result = retsuccess(1002, "productNo为空", "");
				return result;
			}

			String merchantName = json.getString("merchantName");
			if (StringUtils.isEmpty(merchantName)) {
				result = retsuccess(1002, "merchantName为空", "");
				return result;
			}

			String taxNumber = json.getString("taxNumber");
			if (StringUtils.isEmpty(taxNumber)) {
				result = retsuccess(1002, "taxNumber为空", "");
				return result;
			}

			String areaCode = json.getString("areaCode");
			if (StringUtils.isEmpty(areaCode)) {
				result = retsuccess(1002, "areaCode为空", "");
				return result;
			}

			String registerType = json.getString("registerType");
			if (StringUtils.isEmpty(registerType)) {
				result = retsuccess(1002, "registerType为空", "");
				return result;
			}

			String merchantType = json.getString("merchantType");
			if (StringUtils.isEmpty(merchantType)) {
				result = retsuccess(1002, "merchantType为空", "");
				return result;
			}

			String taxAuthorityCode = json.getString("taxAuthorityCode");
			if (StringUtils.isEmpty(taxAuthorityCode)) {
				result = retsuccess(1002, "taxAuthorityCode为空", "");
				return result;
			}

			String enterpriseAddress = json.getString("enterpriseAddress");
			if (StringUtils.isEmpty(enterpriseAddress)) {
				result = retsuccess(1002, "enterpriseAddress为空", "");
				return result;
			}
			
			
			
			String machineCode = json.getString("machineCode");
			if (StringUtils.isEmpty(machineCode)) {
				result = retsuccess(1002, "machineCode为空", "");
				return result;
			}

			String invoicePhone = json.getString("invoicePhone");
			if (StringUtils.isEmpty(invoicePhone)) {
				result = retsuccess(1002, "invoicePhone为空", "");
				return result;
			}

			String email = json.getString("email");
			if (StringUtils.isEmpty(email)) {
				result = retsuccess(1002, "email为空", "");
				return result;
			}

			String maxInvoiceNumber = json.getString("maxInvoiceNumber");
			if (StringUtils.isEmpty(maxInvoiceNumber)) {
				result = retsuccess(1002, "maxInvoiceNumber为空", "");
				return result;
			}

			String recordNumber = json.getString("recordNumber");
			if (StringUtils.isEmpty(recordNumber)) {
				result = retsuccess(1002, "recordNumber为空", "");
				return result;
			}

			String cardNumber = json.getString("cardNumber");
			if (StringUtils.isEmpty(cardNumber)) {
				result = retsuccess(1002, "cardNumber为空", "");
				return result;
			}

			String legalPhone = json.getString("legalPhone");
			if (StringUtils.isEmpty(legalPhone)) {
				result = retsuccess(1002, "legalPhone为空", "");
				return result;
			}

			String contantName = json.getString("contantName");
			if (StringUtils.isEmpty(contantName)) {
				result = retsuccess(1002, "contantName为空", "");
				return result;
			}
			String sorfwareCode = json.getString("sorfwareCode");
			if (StringUtils.isEmpty(sorfwareCode)) {
				result = retsuccess(1002, "sorfwareCode为空", "");
				return result;
			}

			/**************** 验证结束 ************************/

		} catch (Exception e) {
			e.printStackTrace();
			result = retsuccess(1001, "参数为非json格式", "");
			return result;
		}

		return null;
	}
}
