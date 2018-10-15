package cn.yu2.baomihua.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 
/**  
* <p>Title: ParamUtil</p>  
* <p>Description: 参数签名的算法 </p>  
* @author hurt-gong  
* @date 2018年10月10日  
*/  
public class ParamUtilForPay {

	/**
	 * 
	 * 给企业， 直接拼接
	 * @param map
	 * @return
	 */
	public static String toParam(Map<String, Object> map,String keys) {

		String param = "";
		try {
			param = MD5Util.getMD5(accessParam(map,keys));
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
	public static String accessParam(Map<String, Object> map,String keys) {
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
				if(i<infoIds.size()-1){
					sb.append("&");
				}
			}
			
		sb.append(keys);	
			
		} catch (Exception e) {

		}
		return sb.toString();
	}

	
	/**  
	 * <p>Title: main</p>  
	 * <p>Description: </p>  
	 * @param a  
	 */
	public static void main(String[] a){
		Map<String, Object> map = new HashMap<String,Object>();
		
		map.put("abv", "123");
		map.put("bcs", "555");
		map.put("fdsf", "fsd");
		map.put("asddsfbv", "12sdfs3");
		
		
		System.out.println(toParam(map,"222222222222"));
		
	}
	 

}
