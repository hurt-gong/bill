package cn.yu2.baomihua.openapi.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.yu2.baomihua.core.common.util.PropertiesUtil;
import cn.yu2.baomihua.core.service.BaseServiceImpl;
import cn.yu2.baomihua.openapi.entity.bastpay.BastServerCompany;
import cn.yu2.baomihua.openapi.mapper.BastServerCompanyMapper;
import cn.yu2.baomihua.openapi.service.IQtPayService;
import cn.yu2.baomihua.util.DateUtils;
import cn.yu2.baomihua.util.HttpsUtil;
import cn.yu2.baomihua.util.ParamUtilForPay;

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
public class QtPayServiceImpl extends BaseServiceImpl<BastServerCompanyMapper, BastServerCompany>
		implements IQtPayService {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private static String address = PropertiesUtil.getValueByKey("properties/qtpay.properties", "qtpay.t-address");
	private static String payment_url = PropertiesUtil.getValueByKey("properties/qtpay.properties",
			"qtpay.payment_url");

	public Map<String, Object> proCreateOrder(String goods_name) {
		return null;
	}

	/**
	 * 下订单i
	 * <p>
	 * Title: proCreateOrder
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 */
	public Map<String, Object> proCreateOrder(Map<String, Object> parmap) {

		Map<String, Object> map = new HashMap<String, Object>();
		logger.info("param===" + parmap.get("good_names"));
		logger.info("address===" + address);
		logger.info("payment_url===" + payment_url);

		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			// 微信的getOpenId；
			paramMap.put("sub_openid", "oo3Lss8mXSxhtpbPLmoYGC8ToV20");
			// 交易流水号
			paramMap.put("txdtm", DateUtils.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
			// 外部订单号，开发者定义订单号
			paramMap.put("out_trade_no", UUID.randomUUID().toString().replace("-", ""));
			// 支付类型支付宝扫码:800101； 支付宝反扫:800108；支付宝服务窗：800107；微信扫码:800201；
			// 微信刷卡:800208； 微信公众号支付:800207；
			paramMap.put("pay_type", "800207");
			// 请求交易时间；
			paramMap.put("txcurrcd", "CNY");
			//
			paramMap.put("txamt", parmap.get("good_names"));

			paramMap.put("goods_name", parmap.get("good_names"));
			// 请求交易时间；
			paramMap.put("mchid", PropertiesUtil.getValueByKey("properties/qtpay.properties", "qtpay.mchid"));
			//
			paramMap.put("limit_pa", "no_credit");
			//
			paramMap.put("udid", "18801055836111123");

			String sign = ParamUtilForPay.toParam(paramMap,
					PropertiesUtil.getValueByKey("properties/qtpay.properties", "qtpay.key"));
			// 这个是用户的appId
			String appCode = PropertiesUtil.getValueByKey("properties/qtpay.properties", "qtpay.code");
			// 拼接URL
			String url = address + payment_url;

			JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(paramMap));
			System.out.println("url:" + url);
			// 调用返回数据
			String ret = new String(HttpsUtil.httpsPostForPay(url, itemJSONObj.toString(), appCode, sign));
			// logger.info("url:"+url);
			System.out.println("ret" + ret);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;

	}

	public String param(String goods_name, String txamt) {
		String s = "";

		try {

		} catch (Exception e) {

		}

		return s;
	}

	/**
	 * 1、 获取微信oauth的code
	 * <p>
	 * Title: getWeixinOauthCode
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 */
	public Map<String, Object> getWeixinOauthCode() {
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {

			Map<String, Object> paramMap = new HashMap<String, Object>();
			// 开发者识别码，由钱方分配的app_code字符串参数信息
			paramMap.put("app_code", PropertiesUtil.getValueByKey("properties/qtpay.properties", "qtpay.code"));

			// 外部订单号，开发者定义订单号
			paramMap.put("mchid", PropertiesUtil.getValueByKey("properties/qtpay.properties", "qtpay.mchid"));

			// 交易流水号
			paramMap.put("redirect_uri",
					PropertiesUtil.getValueByKey("properties/common.properties", "bastpay.notifyUrl"));

			paramMap.put("sign", ParamUtilForPay.toParam(paramMap,
					PropertiesUtil.getValueByKey("properties/qtpay.properties", "qtpay.key")));

			String paramstr = getParam(paramMap);
			String url = address + PropertiesUtil.getValueByKey("properties/qtpay.properties", "qtpay.getWeixinOuth")
					+ paramstr;
			System.out.println("url:" + url);
			// logger.info("url:"+url);
			String ret = new String(HttpsUtil.httpsGetForPay(url, ""));
			System.out.println("ret" + ret);
			// logger.info("ret"+ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;

	}

	/**
	 * 获取微信的openId
	 * 
	 * <p>
	 * Title: getWinxinOpenId
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 */
	public String getWinxinOpenId() {
		String openId = "";
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			// oauth_code上一步获取的code，同一code不可重复使用。
			paramMap.put("app_code", "1231");

			// 外部订单号，开发者定义订单号
			paramMap.put("mchid", PropertiesUtil.getValueByKey("properties/qtpay.properties", "qtpay.mchid"));

			String sign = ParamUtilForPay.toParam(paramMap,
					PropertiesUtil.getValueByKey("properties/qtpay.properties", "qtpay.key"));
			// 这个是用户的appId
			String appCode = PropertiesUtil.getValueByKey("properties/qtpay.properties", "qtpay.code");
			// 拼接参数
			String paramstr = getParam(paramMap);
			// 拼接URL
			String url = address + PropertiesUtil.getValueByKey("properties/qtpay.properties", "qtpay.getWeixinOpen")
					+ paramstr;
			// 调用返回数据
			String ret = new String(HttpsUtil.httpsPostForPay(url, "", appCode, sign));
			System.out.println("url:" + url);
			// logger.info("url:"+url);
			System.out.println("ret" + ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return openId;
	}

	/**
	 * 拼接1、 获取微信oauth的code的get参数
	 * <p>
	 * Title: getParam
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param paramMap
	 * @return
	 */
	public String getParam(Map<String, Object> paramMap) {
		StringBuffer paramStrbu = new StringBuffer();
		Set<String> key = paramMap.keySet();
		int i = 0;
		for (Iterator<String> it = key.iterator(); it.hasNext();) {
			String k = it.next();
			if (i == 0) {
				paramStrbu.append("?");
			} else {
				paramStrbu.append("&");
			}
			paramStrbu.append(k);
			paramStrbu.append("=");
			paramStrbu.append(paramMap.get(k));
			i++;
		}
		return paramStrbu.toString();
	}

	public static void main(String a[]) {
		// proCreateOrder("123","1");

		// getWinxinOpenId();
	}

	/**
	 * 
	 * <p>
	 * Title: callBack
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param parmap
	 * @return
	 */
	public Map<String, Object> callBack(Map<String, Object> parmap, String reqsign) {
		Map<String, Object> retMap = new HashMap<String, Object>();

		try {
			String sign = ParamUtilForPay.toParam(retMap,
					PropertiesUtil.getValueByKey("properties/qtpay.properties", "qtpay.key"));

			if (sign.equals(reqsign)) {
				retMap.put("code", 0);
			} else {
				retMap.put("code", 1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}
}