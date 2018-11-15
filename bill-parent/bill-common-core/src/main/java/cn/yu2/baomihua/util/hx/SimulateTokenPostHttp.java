package cn.yu2.baomihua.util.hx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class SimulateTokenPostHttp {

	// 第三方平台系统测试虚拟税号
	public static String userName = "1101011010DSF00002";
	// 平台注册码（无对应字段，用于password校验和Hmac校验）
	public static String password = "29829998";
	// 第三方平台编码（RequestCode）：
	public static String requestCode = "DSF00002";
	//测试税号（TaxpayerId）
	public static String taxpayerId = "911403016666666666";
	// 纳税人授权码（AuthorizationCode）：
	public static String authorizationCode = "W1K02MHO69";

	public static void main(String[] args) throws UnsupportedEncodingException {

		String reqJson = makebean();
		System.out.println("reqJson++++" + reqJson);
		String result = invokCloud(reqJson);
		
		JSONObject json = JSON.parseObject(result);
		System.out.println("result+++++++++++++" + result);
		String content = "";
		if(json !=null){
			String datagram = json.getString("datagram");
			if(datagram!=null){
				JSONObject datagramJson = JSON.parseObject(datagram);
				if(datagramJson !=null){
					String data = datagramJson.getString("Data");
					if(data!=null){
						JSONObject dataJson = JSON.parseObject(data);
						content = dataJson.getString("content");
					}
				}
			}
		}

		System.out.println("content+++++++++++++" + content);
		String token = "";
		if(content!=null && !"".equals(content)){
			String contentStr = ProtocolFactory.decode(content);
			System.out.println("content+++++++++++++" + contentStr);
			JSONObject contentJSon = JSON.parseObject(contentStr);
			if(contentJSon !=null ){
				token = contentJSon.getString("TOKEN");
			}
		}
		
		System.out.println("token+++++++++++++" + token);

	}

	public static String invokCloud(String requestJson) {
		String result = "";
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			URL realUrl = new URL("http://60.194.106.83:10013/51SAAS/interface/tokenMake");// 修改成我们提供的接口地址
			// URL realUrl = new
			// URL("http://192.168.15.179:27991/51SAAS/restInterface/saasReceive
			// ");
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Type", "application/json; charset=GBK");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(requestJson);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				System.out.println("关闭inputstream失败" + e2);
			}
		}
		return result;
	}

	public static String makebean() {
		String reqJson = null;

		try {

			String pass = "0267668058BiNxsgcDAY7rHA+WYD+gYw==";

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
	
	
	  public static String makebean1(){
	        String reqJson=null;



	        try {


	            String pass = "0267668058BiNxsgcDAY7rHA+WYD+gYw==";

	            REQUEST_GETSQXX request_fpkjxx_ddztcx=new REQUEST_GETSQXX();
	            request_fpkjxx_ddztcx.setNSRSBH("911403016666666666");//911403012017071316,15001020605191413
//	            requestEntity.setZCM("12345678");
	            request_fpkjxx_ddztcx.setLX("52");


	            SaasRestEntity saasRestEntity = new SaasRestEntity();
	            GlobalInfo globalInfo = new GlobalInfo();
	            globalInfo.setTerminalCode("0");
	            globalInfo.setAppId("983721180711151537");
	            globalInfo.setVersion("2.0");
	            globalInfo.setInterfaceCode("ECJSON.SQXX.BC.E_INV");
	            //ECXML.DDJS.YY.E_INV
	            globalInfo.setUserName("1101011010DSF00002");//111E5VDX,51YKPTEST000001,51H5TEST0000001
	            Password passwordcreate= PassWordCheck.passWordCreate("29829998");
	            globalInfo.setPassWord(passwordcreate.getSjm()+passwordcreate.getPass());
	            globalInfo.setTaxpayerId("911403016666666666");
	            globalInfo.setAuthorizationCode("W1K02MHO69");
	            globalInfo.setRequestCode("DSF00002");//51YKPTES,H5251YKP,51YKPTES
	            //globalInfo.setRequestCode("111E5VDX");
	            globalInfo.setRequestTime("2016-09-02 10:01:00");
	            globalInfo.setDataExchangeId("12110331ECXML.FPKJ.UPLOAD.E_INV");
	            ReturnStateInfo returnStateInfo = new ReturnStateInfo();
	            returnStateInfo.setReturnCode("0000");
	            returnStateInfo.setReturnMessage("success");


	            Data data = new Data();
	            DataDescription dataDescription=new DataDescription();
	            dataDescription.setZipCode("0");
	            dataDescription.setEncryptCode("0");
	            data.setDataDescription(dataDescription);
	            String requestNr= CommonUtil.objectToGson(request_fpkjxx_ddztcx);

	            data.setContent(new String(ProtocolFactory.encode(requestNr.getBytes()), "UTF-8"));

	            saasRestEntity.setGlobalInfo(globalInfo);
	            saasRestEntity.setReturnStateInfo(returnStateInfo);
	            saasRestEntity.setData(data);

	            reqJson = CommonUtil.objectToGson(saasRestEntity);
	            SaasShaReceive shaReceive=new SaasShaReceive();
	            shaReceive.setDatagram(reqJson);
	            shaReceive.setSigntype("HMacSHA256");
	            shaReceive.setSignature(HmacSHA256Util.HMACSHA256(reqJson.getBytes(),"29829998".getBytes()));
	            reqJson= CommonUtil.objectToGson(shaReceive);

	              } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return reqJson;
	    }

}
