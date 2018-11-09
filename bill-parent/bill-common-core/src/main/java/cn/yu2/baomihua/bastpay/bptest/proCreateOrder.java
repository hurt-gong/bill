package cn.yu2.baomihua.bastpay.bptest;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.alibaba.fastjson.JSON;

import cn.yu2.baomihua.bastpay.bputil.CryptoUtil;
import cn.yu2.baomihua.bastpay.bputil.SignatureUtil;

public class proCreateOrder {
    public static void main(String[] args) throws GeneralSecurityException, UnsupportedEncodingException,IOException
    {
      String s = test1();
        String url = "https://mapi.bestpay.com.cn/mapi/uniformReceipt/proCreateOrder";   //z下单接口

        String s1 = reqpost.reqPost(url,s);
        System.out.println("响应消息:"+s1);

    }

    //获取参数
    public static String test1()throws GeneralSecurityException, UnsupportedEncodingException {
        // 生产
        InputStream resourceAsStream = proCreateOrder.class.getClassLoader().getResourceAsStream("生产0103.p12");
        String passwd = "83044799";
        String alias = "conname";
        String keyStoreType = "PKCS12";


        KeyCertInfo keyCertInfo = CryptoUtil.fileStreamToKeyCertInfo(resourceAsStream,passwd,keyStoreType,alias);

        BouncyCastleProvider bouncyCastleProvider = new BouncyCastleProvider();
        Signature signature = Signature.getInstance("SHA256withRSA",bouncyCastleProvider);

        //请求参数
        Map<String,String> translateResultData=new HashMap<String, String>();

        translateResultData.put("merchantNo","3178031195590341");
        translateResultData.put("outTradeNo","201803424189032301234566");//每次修改
        translateResultData.put("tradeAmt","1");
        translateResultData.put("subject","ceshi");
        translateResultData.put("tradeChannel","APP"); //APP支付：固定传APP  手机网站支付：固定传H5  电脑网站支付：固定传WEB
        translateResultData.put("mediumType","WIRELESS");
        translateResultData.put("accessCode","CASHIER");
        translateResultData.put("ccy","156");
        translateResultData.put("goodsInfo","ceshi");
        translateResultData.put("notifyUrl","http://********");
        translateResultData.put("requestDate","2018-09-11 10:30:00");
        translateResultData.put("riskControlInfo","[{\"service_identify\":\"\",\"subject\":\"\",\"product_type\":\"\",\"boby\":\"\",\"goods_count\":1,\"service_cardno\":\"\"}]"); //请根据实际的业务情况来填写
        translateResultData.put("operator","3178031195590341");
        translateResultData.put("institutionCode","3178031195590341");
        translateResultData.put("institutionType","MERCHANT");
        translateResultData.put("timeOut","400");//订单有效期  单位秒 0-86400

       String content = signutils.assembelSignaturingData(translateResultData);
        System.out.println("sign明文:"+content);
        String sign = SignatureUtil.sign(signature,content, (PrivateKey) keyCertInfo.getPrivateKey());
        System.out.println("sign:"+sign);
        translateResultData.put("sign",sign);
        String s = JSON.toJSONString(translateResultData);
        return s;
    }
}
