package cn.yu2.baomihua.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;

/**
 * @author uskytec
 * 工具类： 内容通过outstream.write的方式提交给对方
 */
public class HttpUtil {

	/**
	 * post方式请求服务器(http协议)
	 * 
	 * @param url
	 *            请求地址
	 * @param content
	 *            参数
	 * @param charset
	 *            编码
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws IOException
	 */
	public static byte[] httpPost(String url, String content)
			throws NoSuchAlgorithmException, KeyManagementException, IOException {

		URL console = new URL(url);

		HttpURLConnection conn = (HttpURLConnection) console.openConnection();
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.connect();
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		out.write(content.getBytes("UTF-8"));
		// 刷新、关闭
		out.flush();
		out.close();
		InputStream is = conn.getInputStream();
		if (is != null) {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			is.close();
			return outStream.toByteArray();
		}
		return null;
	}

	
	 public static String doPostJsonParam(String urlStr, String
	            jsonInfo) {
	        String res = "";
	        try {
	            URL url = new URL(urlStr);
	            URLConnection con = url.openConnection();
	            con.setDoOutput(true);
	           
	            con.setRequestProperty("Pragma:", "no-cache");
	            con.setRequestProperty("Cache-Control", "no-cache");
	            con.setRequestProperty("Content-Type", "text/json");
	            OutputStreamWriter out = new
	                    OutputStreamWriter(con.getOutputStream());
	            out.write(new String(jsonInfo.getBytes("UTF-8")));
	            out.flush();
	            out.close();
	            BufferedReader br = new BufferedReader(new
	                    InputStreamReader(con.getInputStream()));
	            String line = "";
	            for (line = br.readLine(); line != null; line =
	                    br.readLine()) {
	                res += line;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return res;
	    }
	
	/**
	 * 提交
	 * 
	 * @param url
	 * @param content
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws IOException
	 */
	public static String post(String url, String content)
			throws NoSuchAlgorithmException, KeyManagementException, IOException {
		if (url == null) {
			return "";
		}
		if (url.indexOf("https") >= 0) {
			return new String(HttpsUtil.httpsPost(url, content));
		} else {
			return new String(httpPost(url, content));
		}

	}
	
	
	public static void main(String[] agr){
		
		//Map<String,Object> map = new HashMap<String,Object>();
		String url = "http://192.168.10.104:8080/openapi/getToken";
		
		JSONObject json =  new JSONObject();
		
		try {
		json.put("channel", "11010101"); //  慧云给提供的渠道ID
		json.put("version", "1.0"); // 当前为1.0
		json.put("msgId", UUID.randomUUID()); // uuid 生成1个 32位
		json.put("keys", AESEncryptUtil.aesEncrypt("11010101", AESEncryptUtil.getAESKey("bastpay"))); // AES(channel)  aeskey 双方协调
		json.put("timeStamp", System.currentTimeMillis() + ""); // 
		json.put("param1", "1.0");
		json.put("param2", "1.0");
		
		Map map = json.toJavaObject(Map.class);
		json.put("signature", "");
		
		
			post(url,json.toJSONString());
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

}
