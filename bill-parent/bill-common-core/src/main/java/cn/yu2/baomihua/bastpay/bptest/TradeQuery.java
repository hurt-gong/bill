package cn.yu2.baomihua.bastpay.bptest;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.alibaba.fastjson.JSON;

import cn.yu2.baomihua.bastpay.bputil.CryptoUtil;
import cn.yu2.baomihua.bastpay.bputil.SignatureUtil;

public class TradeQuery {


        public static void main(String[] args) throws GeneralSecurityException, UnsupportedEncodingException,IOException
        {
            String s = test1();
           String url="https://mapi.bestpay.com.cn/mapi/uniformReceipt/tradeQuery" ;//查询接口
       //   String url="https://mapi.bestpay.com.cn/mapi/uniformReceipt/refundOrderQuery"; //退款查询接口

            String s1 = reqpost.reqPost(url,s);
            System.out.println("响应消息:"+s1);

        }

        //获取参数
        public static String test1()throws GeneralSecurityException, UnsupportedEncodingException {
            // 生产
            InputStream resourceAsStream = TradeQuery.class.getClassLoader().getResourceAsStream("生产0103.p12");

            String passwd = "83044799";
            String alias = "conname";
            String keyStoreType = "PKCS12";

            KeyCertInfo keyCertInfo = CryptoUtil.fileStreamToKeyCertInfo(resourceAsStream,passwd,keyStoreType,alias);

            BouncyCastleProvider bouncyCastleProvider = new BouncyCastleProvider();
            Signature signature = Signature.getInstance("SHA256withRSA",bouncyCastleProvider);

            //请求参数
            Map<String,String> translateResultData=new HashMap<String, String>();
             String tradeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            //订单查询 --退款查询
            translateResultData.put("institutionCode","3178031195590341");
            translateResultData.put("institutionType","MERCHANT");
            translateResultData.put("outTradeNo","201801055272151541310071231213");
            translateResultData.put("merchantNo","3178031195590341");
            translateResultData.put("tradeDate","2018-09-03 09:20:00");

            String content = signutils.assembelSignaturingData(translateResultData);
            String sign = SignatureUtil.sign(signature,content, (PrivateKey) keyCertInfo.getPrivateKey());
            X509Certificate x509Certificate = CryptoUtil.base64StrToCert(keyCertInfo.getBase64Cert());
            boolean isOk = SignatureUtil.verify(signature,content,sign,x509Certificate.getPublicKey());
            System.out.println("sign:"+sign);
            System.out.println("content:"+content);
            System.out.println("verify:"+isOk);

            translateResultData.put("sign",sign);
            System.out.println(JSON.toJSONString(translateResultData));
            String s = JSON.toJSONString(translateResultData);
            return s;
        }
    }
