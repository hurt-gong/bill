package cn.yu2.baomihua.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
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

	/**
	 * post
	 */
	public static String hxhttp(String url, String requestJson) {
		String result = "";
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			URL realUrl = new URL(url);// 修改成我们提供的接口地址

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
}
