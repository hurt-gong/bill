package cn.yu2.baomihua.openapi.module;

import java.util.Map;

/**  
* <p>Title: IQtPayModule</p>  
* <p>Description: </p>  
* @author hurt-gong  
* @date 2018年11月10日  
*/  
public interface IQtPayModule {
	
	/**
	 * 下订单
	 * <p>Title: proCreateOrder</p>  
	 * <p>Description: </p>  
	 * @return
	 */
	public Map<String, Object> proCreateOrder(Map<String, Object> parmap) ;
	
	
	public Map<String, Object> callBack(Map<String, Object> parmap, String reqsign);
}
