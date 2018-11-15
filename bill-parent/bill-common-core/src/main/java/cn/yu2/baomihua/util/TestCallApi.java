/**  
* <p>Title: TestCallApi.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2017</p>  
* <p>Company: baomihua</p>  
* @author hurt-gong
* @date 2018年10月12日  
* @version 1.0  
*/
package cn.yu2.baomihua.util;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * <p>
 * Title: TestCallApi
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author hurt-gong
 * @date 2018年10月12日
 */
public class TestCallApi {

	/**
	 * <p>
	 * Title: main
	 * </p>
	 * <p>
	 * Description: 暂时先用签名和秘钥的方式进行接口安全，现在的缺陷是篡改pushdata的数据，无法验证
	 * </p>
	 * 
	 * @param args
	 */
	public static void main1(String[] args) {
		// TODO Auto-generated method stub

		String url = "http://192.168.10.148:8080/qiantai/pay";

		JSONObject json = new JSONObject();

		try {
			json.put("channel", "11010101"); // 慧云给提供的渠道ID
			json.put("version", "1.0"); // 当前为1.0
			json.put("msgId", UUID.randomUUID().toString().replace("-", "")); // Id生成1个32位
			json.put("secretkey", AESEncryptUtil.aesEncrypt("11010101", AESEncryptUtil.getAESKey("bastpay"))); // AES(channel)aeskey双方协调
			json.put("timeStamp", System.currentTimeMillis() + ""); // 时间戳
			Map map = json.toJavaObject(Map.class);
			json.put("signature", ParamUtilForPay.getSign(map, "11010101")); // 签名
													// 想了一下不放在签名内，数据量太大的话太消耗性能。
			String ret = HttpUtil.post(url, json.toJSONString());
			System.out.println(ret);
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//String url = "http://127.0.0.1/bastpay/addDetailInfo";
		String url = "https://yzf.cloudlinkin.cn/bastpay/addCompany";

		JSONObject json = new JSONObject();
		JSONArray jarray = new JSONArray();

		try {
			json.put("channel", "11010101"); // 慧云给提供的渠道ID
			json.put("version", "1.0"); // 当前为1.0
			json.put("msgId", UUID.randomUUID().toString().replace("-", "")); // Id生成1个32位
			json.put("secretkey", AESEncryptUtil.aesEncrypt("11010101", AESEncryptUtil.getAESKey("bastpay"))); // AES(channel)aeskey双方协调
			json.put("timeStamp", System.currentTimeMillis() + ""); // 时间戳
			//json.put("paramData",  JSONArray.parseArray(JSON.toJSONString(getParam())));// 推送的数据，
			getParamForJSONObject(json);
			Map map = json.toJavaObject(Map.class);
			json.put("signature", ParamUtil.toParam(map)); // 签名
			String ret = HttpUtil.post(url, json.toJSONString());
			System.out.println(ret);
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static List<Map<String, Object>> getParam() {

		List<Map<String, Object>> paramData = new ArrayList<Map<String, Object>>();
		String[] month = new String[] { "201610", "201611", "201612", "201701", "201702", "201703", "201704", "201705",
				"201706", "201707", "201708", "201709", "201710", "201711", "201712", "201801", "201802", "201803",
				"201804", "201805", "201806", "201807", "201808", "201809" };
		for (int i = 0; i < month.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("openData", month[i]);
			map.put("province", "123");
			map.put("companyName", "HuiYun");
			map.put("companyCode", month[i] + "" + i);
			map.put("ticketsNumber", "1209120" + i);
			map.put("ticketsCode", "893248932" + i);
			map.put("buyCompany", "HuiYun" + i + "");
			map.put("buyCompanyAddress", "beijing,th");
			map.put("saleVolume", (int)((Math.random()*9+1)*10000));
			map.put("status", "1");
			map.put("remark", "");
			paramData.add(map);
		}

		return paramData;
	}
	
	
	public static JSONObject getParamForJSONObject(JSONObject json) {
		json.put("productNo", "productNo");
		json.put("addressee", "addressee");
		json.put("address", "address");
		json.put("province", "province");
		json.put("phone", "phone");
		json.put("companyName", "");
		json.put("serviceCharge", "1");
		json.put("expressFee", "expressFee");
		json.put("effectDateFrom", "effectDateFrom");
		json.put("effectDateTo", "effectDateTo");
		return json;
	}
	
	
	
	public static JSONObject getDetailParamForJSONObject(JSONObject json) {
		json.put("productNo", "12000000000001");
		json.put("merchantName", "merchantName1");
		json.put("taxNumber", "taxNumber2");
		json.put("areaCode", "areaCode3");
		json.put("registerType", "registerType111");
		json.put("merchantType", "merchantType");
		json.put("taxAuthorityCode", "taxAuthorityCode");
		json.put("enterpriseAddress", "enterpriseAddress");
		json.put("machineCode", "machineCode");
		json.put("invoicePhone", "invoicePhone33");
		json.put("email", "email");
		json.put("maxInvoiceNumber", "maxInvoiceNumber");
		json.put("recordNumber", "recordNumber");
		json.put("cardNumber", "cardNumber");
		json.put("legalPhone", "legalPhone");
		json.put("contantName", "contantName");
		json.put("sorfwareCode", "sorfwareCode");
		 
		return json;
	}

}
