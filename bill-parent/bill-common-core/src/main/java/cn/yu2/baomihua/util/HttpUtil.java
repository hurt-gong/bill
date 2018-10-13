package cn.yu2.baomihua.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;

/**
 * <p>
 * Title: HttpUtil
 * </p>
 * <p>
 * Description:工具类： 内容通过outstream.write的方式提交给对方
 * </p>
 * 
 * @author hurt-gong
 * @date 2018年10月10日
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
 

}
