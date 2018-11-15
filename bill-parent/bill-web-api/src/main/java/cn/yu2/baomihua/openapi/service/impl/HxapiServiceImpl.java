package cn.yu2.baomihua.openapi.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.yu2.baomihua.core.service.BaseServiceImpl;
import cn.yu2.baomihua.openapi.entity.bastpay.BastServerCompany;
import cn.yu2.baomihua.openapi.mapper.BastCompanyDetilMapper;
import cn.yu2.baomihua.openapi.mapper.BastCompanyFeePayMapper;
import cn.yu2.baomihua.openapi.mapper.BastServerCompanyMapper;
import cn.yu2.baomihua.openapi.service.IHxapiService;
import cn.yu2.baomihua.util.HttpUtil;
import cn.yu2.baomihua.util.hx.CommonUtil;
import cn.yu2.baomihua.util.hx.Data;
import cn.yu2.baomihua.util.hx.DataDescription;
import cn.yu2.baomihua.util.hx.GlobalInfo;
import cn.yu2.baomihua.util.hx.HmacSHA256Util;
import cn.yu2.baomihua.util.hx.PassWordCheck;
import cn.yu2.baomihua.util.hx.Password;
import cn.yu2.baomihua.util.hx.ProtocolFactory;
import cn.yu2.baomihua.util.hx.REQUEST_GETSQXX;
import cn.yu2.baomihua.util.hx.ReturnStateInfo;
import cn.yu2.baomihua.util.hx.SaasRestEntity;
import cn.yu2.baomihua.util.hx.SaasShaReceive;

/**
 * 翼支付支付逻辑
 * <p>
 * Title: BastPayServiceImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author hurt-gong
 * @date 2018年11月8日
 */
@Service
public class HxapiServiceImpl extends BaseServiceImpl<BastServerCompanyMapper, BastServerCompany>
		implements IHxapiService {

	@Autowired
	private BastCompanyFeePayMapper bastCompanyFeePayMapper;

	@Autowired
	private BastCompanyDetilMapper bastCompanyDetilMapper;

	// 第三方平台系统测试虚拟税号
	public static String userName = "1101011010DSF00002";
	// 平台注册码（无对应字段，用于password校验和Hmac校验）
	public static String password = "29829998";
	// 第三方平台编码（RequestCode）：
	public static String requestCode = "DSF00002";
	// 测试税号（TaxpayerId）
	public static String taxpayerId = "911403016666666666";
	// 纳税人授权码（AuthorizationCode）：
	public static String authorizationCode = "W1K02MHO69";

	public static String url = "http://60.194.106.83:10013/51SAAS/interface/tokenMake";

	/**
	 * 获取航信单点的token
	 * <p>Title: getToken</p>  
	 * <p>Description: </p>  
	 * @param userName
	 * @param password
	 * @param requestCode
	 * @param taxpayerId
	 * @param authorizationCode
	 * @return
	 */
	public Map<String, Object> getToken(String userName, String password, String requestCode, String taxpayerId,
			String authorizationCode) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		
		String reqJson = makebean(userName, password, requestCode, taxpayerId, authorizationCode);
		System.out.println("reqJson++++" + reqJson);

		String result = HttpUtil.hxhttp(url, reqJson);

		JSONObject json = JSON.parseObject(result);
		System.out.println("result+++++++++++++" + result);
		String content = "";
		if (json != null) {
			String datagram = json.getString("datagram");
			if (datagram != null) {
				JSONObject datagramJson = JSON.parseObject(datagram);
				if (datagramJson != null) {
					String data = datagramJson.getString("Data");
					if (data != null) {
						JSONObject dataJson = JSON.parseObject(data);
						content = dataJson.getString("content");
					}
				}
			}
		}

		System.out.println("content+++++++++++++" + content);
		String token = "";
		if (content != null && !"".equals(content)) {
			String contentStr = ProtocolFactory.decode(content);
			System.out.println("content+++++++++++++" + contentStr);
			JSONObject contentJSon = JSON.parseObject(contentStr);
			if (contentJSon != null) {
				token = contentJSon.getString("TOKEN");
			}
		}

		retMap.put("token", token);
		System.out.println("token+++++++++++++" + token);

		return retMap;

	}

	public static String makebean(String userName, String password, String requestCode, String taxpayerId,
			String authorizationCode) {
		String reqJson = null;

		try {

			REQUEST_GETSQXX request_fpkjxx_ddztcx = new REQUEST_GETSQXX();
			request_fpkjxx_ddztcx.setNSRSBH(taxpayerId);// 911403012017071316,15001020605191413
			// requestEntity.setZCM("12345678");
			request_fpkjxx_ddztcx.setLX("52");

			SaasRestEntity saasRestEntity = new SaasRestEntity();
			GlobalInfo globalInfo = new GlobalInfo();
			globalInfo.setTerminalCode("0");
			globalInfo.setAppId("983721180711151537");
			globalInfo.setVersion("2.0");
			globalInfo.setInterfaceCode("ECJSON.SQXX.BC.E_INV");
			globalInfo.setUserName(userName);// 111E5VDX,51YKPTEST000001,51H5TEST0000001
			Password passwordcreate = PassWordCheck.passWordCreate(password);
			globalInfo.setPassWord(passwordcreate.getSjm() + passwordcreate.getPass());
			globalInfo.setTaxpayerId(taxpayerId);
			globalInfo.setAuthorizationCode(authorizationCode);
			globalInfo.setRequestCode(requestCode);// 51YKPTES,H5251YKP,51YKPTES
			globalInfo.setResponseCode("");
			globalInfo.setInterfaceMode("0");
			// globalInfo.setRequestCode("111E5VDX");
			globalInfo.setRequestTime("2018-11-12 15:25:00");
			globalInfo.setDataExchangeId(requestCode + "20181112" + "987654322");
			ReturnStateInfo returnStateInfo = new ReturnStateInfo();
			returnStateInfo.setReturnCode("0000");
			returnStateInfo.setReturnMessage("message");

			Data data = new Data();
			DataDescription dataDescription = new DataDescription();
			dataDescription.setZipCode("0");
			dataDescription.setEncryptCode("0");
			data.setDataDescription(dataDescription);
			String requestNr = CommonUtil.objectToGson(request_fpkjxx_ddztcx);

			data.setContent(new String(ProtocolFactory.encode(requestNr.getBytes()), "UTF-8"));

			saasRestEntity.setGlobalInfo(globalInfo);
			saasRestEntity.setReturnStateInfo(returnStateInfo);
			saasRestEntity.setData(data);

			reqJson = CommonUtil.objectToGson(saasRestEntity);
			SaasShaReceive shaReceive = new SaasShaReceive();
			shaReceive.setDatagram(reqJson);
			shaReceive.setSigntype("HMacSHA256");
			shaReceive.setSignature(HmacSHA256Util.HMACSHA256(reqJson.getBytes(), password.getBytes()));
			reqJson = CommonUtil.objectToGson(shaReceive);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return reqJson;
	}

}