/**  

* <p>Title: ICompanyService.java</p>  

* <p>Description: </p>  

* <p>Copyright: Copyright (c) 2017</p>  

* <p>Company: baomihua</p>  

* @author hurt-gong

* @date 2018年10月11日  

* @version 1.0  

*/  
package cn.yu2.baomihua.openapi.service;

import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.framework.service.ISuperService;

import cn.yu2.baomihua.openapi.entity.CompanyTickets;

/**  

* <p>Title: ICompanyService</p>  

* <p>Description: </p>  

* @author hurt-gong  

* @date 2018年10月11日  

*/
public interface ICompanyTicketService extends ISuperService<CompanyTickets> {
	
	/**
	 * 根据企业信息查询结果
	 * <p>Title: search</p>  
	 * <p>Description: </p>  
	 * @param company
	 * @return
	 */
	public Map<String, Object> search(String companyInfo);
	
	
	/**
	 * 保存推过来的数据
	 * <p>Title: saveCompanyTickets</p>  
	 * <p>Description: </p>  
	 * @param companyTickets
	 */
	public void savePushData(JSONArray jsonArray,String msgId);

	
}
