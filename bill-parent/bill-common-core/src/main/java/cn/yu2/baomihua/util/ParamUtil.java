package cn.yu2.baomihua.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

 
/**  
* <p>Title: ParamUtil</p>  
* <p>Description: 参数签名的算法 </p>  
* @author hurt-gong  
* @date 2018年10月10日  
*/  
public class ParamUtil {

	/**
	 * 
	 * 给企业， 直接拼接
	 * @param map
	 * @return
	 */
	public static String toParam(Map<String, Object> map) {

		String param = "";
		try {
			param = SHA1.encode(accessParam(map));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return param;

	}
	
	
	/**
	 * 
	 * 给航信，需要去掉数据， 预计数据很大， 
	 * @param map
	 * @return
	 */
	public static String toAisinoParam(Map<String, Object> map) {

		String param = "";
		try {
			if(map.containsKey("pushData")){
				map.remove("pushData");
			}
			if(map.containsKey("signature")){
				map.remove("signature");
			}
			param = SHA1.encode(accessParam(map));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return param;

	}
	
	/**
	 * 
	 * 签名验证
	 * @param map
	 * @return
	 */
	public static String decryptParam(Map<String, Object> map) {

		String param = "";
		try {
			if(map.containsKey("signature")){
				map.remove("signature");
			}
			param = SHA1.encode(accessParam(map));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return param;

	}

	/**
	 * 排序
	 * 
	 * @param map
	 * @return
	 */
	public static String accessParam(Map<String, Object> map) {
		StringBuffer sb = new StringBuffer();
		try {

			List<Map.Entry<String, Object>> infoIds = new ArrayList<Map.Entry<String, Object>>(map.entrySet());

			// 对HashMap中的key 进行排序
			Collections.sort(infoIds, new Comparator<Map.Entry<String, Object>>() {
				public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
					return (o1.getKey()).toString().compareTo(o2.getKey().toString());
				}
			});
			//
			for (int i = 0; i < infoIds.size(); i++) {
				String id = infoIds.get(i).toString();
				sb.append(id);
			}
		} catch (Exception e) {

		}
		return sb.toString();
	}

	 

}
