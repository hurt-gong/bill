package cn.yu2.baomihua.openapi.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.yu2.baomihua.bastpay.bptest.KeyCertInfo;
import cn.yu2.baomihua.bastpay.bptest.proCreateOrder;
import cn.yu2.baomihua.bastpay.bptest.reqpost;
import cn.yu2.baomihua.bastpay.bptest.signutils;
import cn.yu2.baomihua.bastpay.bputil.CryptoUtil;
import cn.yu2.baomihua.bastpay.bputil.SignatureUtil;
import cn.yu2.baomihua.core.common.util.PropertiesUtil;
import cn.yu2.baomihua.core.service.BaseServiceImpl;
import cn.yu2.baomihua.openapi.entity.bastpay.BastCompanyDetil;
import cn.yu2.baomihua.openapi.entity.bastpay.BastCompanyFeePay;
import cn.yu2.baomihua.openapi.entity.bastpay.BastServerCompany;
import cn.yu2.baomihua.openapi.mapper.BastCompanyDetilMapper;
import cn.yu2.baomihua.openapi.mapper.BastCompanyFeePayMapper;
import cn.yu2.baomihua.openapi.mapper.BastServerCompanyMapper;
import cn.yu2.baomihua.openapi.service.IBastPayService;
import cn.yu2.baomihua.util.DateUtils;

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
public class BastPayServiceImpl extends BaseServiceImpl<BastServerCompanyMapper, BastServerCompany>
		implements IBastPayService {

	@Autowired
	private BastCompanyFeePayMapper bastCompanyFeePayMapper;

	@Autowired
	private BastCompanyDetilMapper bastCompanyDetilMapper;

	/**
	 * 下订单
	 */
	public String proCreateOrder(String money, String goodsInfo) {
		// 获取参数
		// 生产

		String merchantNo = PropertiesUtil.getValueByKey("properties/common.properties", "bastpay.merchantNo");
		String passwd = PropertiesUtil.getValueByKey("properties/common.properties", "bastpay.passwd");
		String alias = PropertiesUtil.getValueByKey("properties/common.properties", "bastpay.alias");
		String keyStoreType = PropertiesUtil.getValueByKey("properties/common.properties", "bastpay.keyStoreType");
		String notifyUrl = PropertiesUtil.getValueByKey("properties/common.properties", "bastpay.notifyUrl");
		String outTradeNo = UUID.randomUUID().toString().replace("-", "");
		String url = PropertiesUtil.getValueByKey("properties/common.properties", "bastpay.proCreateOrder");

		String s = param(merchantNo, outTradeNo, passwd, alias, keyStoreType, notifyUrl, money, goodsInfo);

		String s1 = "";
		try {
			s1 = reqpost.reqPost(url, s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("响应消息:" + s1);

		return s1;

	}

	/**
	 * 参数的拼接
	 * <p>
	 * Title: param
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param merchantNo
	 * @param outTradeNo
	 * @param passwd
	 * @param alias
	 * @param keyStoreType
	 * @param notifyUrl
	 * @param money
	 * @param goodsInfo
	 * @return
	 */
	public String param(String merchantNo, String outTradeNo, String passwd, String alias, String keyStoreType,
			String notifyUrl, String money, String goodsInfo) {
		String s = "";
		try {
			InputStream resourceAsStream = proCreateOrder.class.getClassLoader()
					.getResourceAsStream("p12/product-test.p12");

			KeyCertInfo keyCertInfo = CryptoUtil.fileStreamToKeyCertInfo(resourceAsStream, passwd, keyStoreType, alias);

			BouncyCastleProvider bouncyCastleProvider = new BouncyCastleProvider();
			Signature signature = Signature.getInstance("SHA256withRSA", bouncyCastleProvider);

			// 请求参数
			Map<String, String> translateResultData = new HashMap<String, String>();

			translateResultData.put("merchantNo", merchantNo);
			translateResultData.put("outTradeNo", outTradeNo);// 每次修改
			translateResultData.put("tradeAmt", money);

			translateResultData.put("goodsInfo", goodsInfo);
			translateResultData.put("subject", goodsInfo);
			translateResultData.put("notifyUrl", notifyUrl);
			translateResultData.put("requestDate", DateUtils.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
			translateResultData.put("operator", merchantNo);
			translateResultData.put("institutionCode", merchantNo);
			translateResultData.put("tradeChannel", "APP"); // APP支付：固定传APP
			// 手机网站支付：固定传H5
			// 电脑网站支付：固定传WEB
			translateResultData.put("riskControlInfo",
					"[{\"service_identify\":\"\",\"subject\":\"\",\"product_type\":\"\",\"boby\":\"\",\"goods_count\":1,\"service_cardno\":\"\"}]"); // 请根据实际的业务情况来填写
			translateResultData.put("mediumType", "WIRELESS");
			translateResultData.put("accessCode", "CASHIER");
			translateResultData.put("ccy", "156");
			translateResultData.put("institutionType", "MERCHANT");
			translateResultData.put("timeOut", "400");// 订单有效期 单位秒 0-86400

			String content = signutils.assembelSignaturingData(translateResultData);
			System.out.println("sign明文:" + content);
			String sign = "";
			try {
				sign = SignatureUtil.sign(signature, content, (PrivateKey) keyCertInfo.getPrivateKey());
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SignatureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("sign:" + sign);
			translateResultData.put("sign", sign);
			s = JSON.toJSONString(translateResultData);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return s;

	}

	/**
	 * 
	 * 接口，添加服务企业
	 * <p>
	 * Title: addBastpayCompany
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param bastServerCompany
	 * @return
	 */
	public Map<String, Object> addBastpayCompany(BastServerCompany bastServerCompany) {

		String goodinfo = "51盒子";
		// 定义返回数据的结果盒子
		Map<String, Object> retmap = new HashMap<String, Object>();
		try {
			// 1、 保存企业信息

			int id = baseMapper.insert(bastServerCompany);

			// 2、下订单
			String orderInfo = proCreateOrder(bastServerCompany.getServiceCharge(), goodinfo);

			// 3 获取返回数据
			JSONObject json = null;
			BastCompanyFeePay bastCompanyFeePay = new BastCompanyFeePay();
			try {
				json = JSONObject.parseObject(orderInfo);
				if (json.getBooleanValue("success")) {
					JSONObject resultJson = json.getJSONObject("result");
					if (resultJson != null) {
						String merchantNo = resultJson.getString("merchantNo");
						bastCompanyFeePay.setMerchantNo(merchantNo);
						String merchantOrderNo = resultJson.getString("merchantOrderNo");
						bastCompanyFeePay.setMerchantOrderNo(merchantOrderNo);
						String tradeNo = resultJson.getString("tradeNo");
						bastCompanyFeePay.setTradeNo(tradeNo);
						String outTradeNo = resultJson.getString("outTradeNo");
						bastCompanyFeePay.setOutTradeNo(outTradeNo);
						String tradeStatus = resultJson.getString("tradeStatus");
						bastCompanyFeePay.setTradeStatus(tradeStatus);
						String tradeprodNo = resultJson.getString("tradeprodNo");
						bastCompanyFeePay.setTradeprodNo(tradeprodNo);
					}

					retmap = resultJson.toJavaObject(Map.class);

				}

			} catch (Exception e) {
			}
			bastCompanyFeePay.setGoodsInfo(goodinfo);
			bastCompanyFeePay.setOpertionTime(DateUtils.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
			bastCompanyFeePay.setUpdatTime(DateUtils.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
			bastCompanyFeePay.setTradeAmt(bastServerCompany.getServiceCharge());
			bastCompanyFeePay.setProductNo(bastServerCompany.getProductNo());
			bastCompanyFeePayMapper.insert(bastCompanyFeePay);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return retmap;

	}

	/**
	 * 支付后的回调接口
	 * <p>
	 * Title: bastPayCallBack
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param param
	 * @return
	 */
	public Map<String, Object> bastPayCallBack(String param) {
		Map<String, Object> returnMap = new HashMap<String, Object>();

		try {

			// 1、验证参数的返回值，
			JSONObject json = JSONObject.parseObject(param);
			String merchantNo = json.getString("merchantNo");

			String outTradeNo = json.getString("outTradeNo");

			String tradeAmt = json.getString("tradeAmt");

			String tradeNo = json.getString("tradeNo");

			String tradeStatus = json.getString("tradeStatus");

			BastCompanyFeePay bastCompanyFeePay = bastCompanyFeePayMapper.getBastCompanyFeePay(merchantNo, outTradeNo,
					tradeAmt, tradeNo);
			// REAL_TIME_PRO:高级即时到账
			// REFUND 退款
			String tradeType = json.getString("tradeType");
			if (bastCompanyFeePay == null) {
				Map<String, Object> ret = new HashMap<String, Object>();
				ret.put("statusCode", 502);
				ret.put("outTradeNo", "");
				ret.put("tradeNo", "");
				returnMap = returnInfo(true, "API505", "参数错误", ret);
				return returnMap;
			} else {
				bastCompanyFeePay.setUpdatTime(DateUtils.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
				bastCompanyFeePay.setTradeStatus(tradeStatus);
				bastCompanyFeePayMapper.updateById(bastCompanyFeePay);
			}

			Map<String, Object> ret = new HashMap<String, Object>();
			ret.put("statusCode", 200);
			ret.put("outTradeNo", outTradeNo);
			ret.put("tradeNo", tradeNo);
			returnMap = returnInfo(true, "", "", ret);

			// 2、数据库变更
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> ret = new HashMap<String, Object>();
			ret.put("statusCode", 502);
			ret.put("outTradeNo", "");
			ret.put("tradeNo", "");
			returnMap = returnInfo(true, "API505", "参数错误", ret);
		}

		return returnMap;
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

	/**
	 * 对服务信息进行操作
	 * <p>
	 * Title: mergeBasePayDetaiInfo
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param bastCompanyDetil
	 * @return
	 */
	public Map<String, Object> mergeBasePayDetaiInfo(BastCompanyDetil bastCompanyDetil) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (bastCompanyDetil != null && bastCompanyDetil.getProductNo() != null
					&& !"".equals(bastCompanyDetil.getProductNo())) {
				// 查询数据库里面是否有数据。
				BastCompanyDetil bastCompanyDetil_db = bastCompanyDetilMapper
						.getBastCompanyDetail(bastCompanyDetil.getProductNo());
				
				if(bastCompanyDetil_db == null){
					bastCompanyDetil.setInsetDate(DateUtils.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
					bastCompanyDetilMapper.insert(bastCompanyDetil);
				}else{
					bastCompanyDetil.setInsetDate(DateUtils.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
					bastCompanyDetil.setId(bastCompanyDetil_db.getId());
					bastCompanyDetilMapper.updateById(bastCompanyDetil);
				}
				map.put("code", 0);
				map.put("desc", "success");
			} else {
				map.put("code", 1);
				map.put("desc", "参数错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}

}