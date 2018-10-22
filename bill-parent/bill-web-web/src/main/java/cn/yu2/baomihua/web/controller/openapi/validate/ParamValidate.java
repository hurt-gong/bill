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

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.yu2.baomihua.constant.CompanyConstant;
import cn.yu2.baomihua.util.AESEncryptUtil;
import cn.yu2.baomihua.util.ParamUtil;
import cn.yu2.baomihua.web.JsonResult;

/**  
* <p>Title: ParamValidate</p>  
* <p>Description: </p>  
* @author hurt-gong  
* @date 2018年10月15日  
*/
public class ParamValidate {
	
	/**
	 * pushdata参数的验证
	 * <p>Title: pushdataValidate</p>  
	 * <p>Description: </p>  
	 * @param param
	 * @return
	 */
	public static JsonResult pushdataValidate(String param){

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
			
			try{
			JSONArray pushData = json.getJSONArray("pushData");
			
			
			}catch(Exception e){
				e.printStackTrace();
				result = retsuccess(1003, "pushData为空或者格式錯誤", "");
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
			String signature_temp = ParamUtil.toAisinoParam(map);
			if (!signature.equals(signature_temp)) {
				result = retsuccess(1003, "signature错误", "");
				return result;
			}

			/**************** 验证结束 ************************/

		} catch (Exception e) {
			e.printStackTrace();
			result =  retsuccess(1001, "参数为非json格式", "");
			return result;
		}
		
		return null;
	}
	
	
	
	/**
	 * 
	 * <p>Title: retsuccess</p>  
	 * <p>Description: </p>  
	 * @param code
	 * @param msg
	 * @param data
	 * @return
	 */
	private static JsonResult retsuccess(int code,String msg,Object data){
		JsonResult js = new JsonResult();
		js.setCode(code);
		js.setData(data);
		js.setMsg(msg);
		return js;
	}
	 
	
	public static JsonResult searchParamValidate(String param){
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

}
