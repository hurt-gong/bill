
package cn.yu2.baomihua.bastpay.bptest;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.alibaba.fastjson.JSON;

import cn.yu2.baomihua.bastpay.bputil.CryptoUtil;
import cn.yu2.baomihua.bastpay.bputil.SignatureUtil;

public class ReFund {
//退款
        public static void main(String[] args) throws GeneralSecurityException, UnsupportedEncodingException,IOException
        {
            String s = test1();
            String url = "https://mapi.bestpay.com.cn/mapi/uniformReceipt/tradeRefund";  // 退款接口
            String s1 = reqpost.reqPost(url,s);
            System.out.println("响应消息:"+s1);

        }

        //获取参数
        public static String test1()throws GeneralSecurityException, UnsupportedEncodingException {
            // 生产

            InputStream resourceAsStream = ReFund.class.getClassLoader().getResourceAsStream("生产0103.p12");
            String passwd = "83044799";
            String alias = "conname";
            String keyStoreType = "PKCS12";


            KeyCertInfo keyCertInfo = CryptoUtil.fileStreamToKeyCertInfo(resourceAsStream,passwd,keyStoreType,alias);

            BouncyCastleProvider bouncyCastleProvider = new BouncyCastleProvider();
            Signature signature = Signature.getInstance("SHA256withRSA",bouncyCastleProvider);

            //请求参数
            Map<String,String> translateResultData=new HashMap<String, String>();
             //退款

            translateResultData.put("outRequestNo","20180107825563745156700589");//每次修改 退款流水号 商户侧生成
            translateResultData.put("merchantNo","3178031195590341"); //商户号
            translateResultData.put("outTradeNo","2018010131007312136765");//原outTradeNo   交易订单号
            translateResultData.put("refundAmt","1"); // 单位 分
            translateResultData.put("requestDate","2018-08-17 10:46:15");
            translateResultData.put("operator","3178031195590341");
            translateResultData.put("tradeChannel","H5"); //渠道  与下单一样
            translateResultData.put("accessCode","CASHIER");
            translateResultData.put("ccy","156");
            translateResultData.put("institutionCode","3178031195590341");
            translateResultData.put("notifyUrl","");
			translateResultData.put("originalTradeDate","2018-11-02 12:21:23");//原交易日期  取下单时间

            String content = signutils.assembelSignaturingData(translateResultData);
            String sign = SignatureUtil.sign(signature,content, (PrivateKey) keyCertInfo.getPrivateKey());
            X509Certificate x509Certificate = CryptoUtil.base64StrToCert(keyCertInfo.getBase64Cert());
            boolean isOk = SignatureUtil.verify(signature,content,sign,x509Certificate.getPublicKey());
            System.out.println("sign:"+sign);
            System.out.println("content:"+content);
            System.out.println("verify:"+isOk);

            translateResultData.put("sign",sign);
            String s = JSON.toJSONString(translateResultData);
            return s;
        }

    }



