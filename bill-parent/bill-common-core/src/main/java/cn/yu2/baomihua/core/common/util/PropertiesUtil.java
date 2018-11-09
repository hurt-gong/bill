/**  
* <p>Title: PropertiesUtil.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2017</p>  
* <p>Company: baomihua</p>  
* @author hurt-gong
* @date 2018年11月8日  
* @version 1.0  
*/
package cn.yu2.baomihua.core.common.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

import cn.yu2.baomihua.bastpay.bptest.proCreateOrder;

/**
 * <p>
 * Title: PropertiesUtil
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author hurt-gong
 * @date 2018年11月8日
 */
public class PropertiesUtil {
	public static String getValueByKey(String filePath, String key) {
		Properties pps = new Properties();
		try {

			filePath = Thread.currentThread().getContextClassLoader().getResource("").getPath() + filePath;
			InputStream in = new BufferedInputStream(new FileInputStream(filePath));
			pps.load(in);
			String value = pps.getProperty(key);
			System.out.println(key + " = " + value);
			return value;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	// 读取Properties的全部信息
	public static void getAllProperties(String filePath) throws IOException {
		Properties pps = new Properties();
		InputStream in = new BufferedInputStream(new FileInputStream(filePath));
		pps.load(in);
		Enumeration en = pps.propertyNames(); // 得到配置文件的名字

		while (en.hasMoreElements()) {
			String strKey = (String) en.nextElement();
			String strValue = pps.getProperty(strKey);
			System.out.println(strKey + "=" + strValue);
		}

	}

	// 写入Properties信息
	public static void writeProperties(String filePath, String pKey, String pValue) throws IOException {
		Properties pps = new Properties();

		InputStream in = new FileInputStream(filePath);
		// 从输入流中读取属性列表（键和元素对）
		pps.load(in);
		// 调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
		// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
		OutputStream out = new FileOutputStream(filePath);
		pps.setProperty(pKey, pValue);
		// 以适合使用 load 方法加载到 Properties 表中的格式，
		// 将此 Properties 表中的属性列表（键和元素对）写入输出流
		pps.store(out, "Update " + pKey + " name");
	}

	public static void main(String[] a) {
		String merchantNo = PropertiesUtil.getValueByKey(
				"E:/project/bill-git/bill/bill-parent/bill-web-api/target/classes/properties/common.properties",
				"bastpay.merchantNo");
	}
}
