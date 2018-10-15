package cn.yu2.baomihua.openapi.module;

import java.util.List;
import java.util.Map;

import cn.yu2.baomihua.manage.entity.BookTag;
import cn.yu2.baomihua.openapi.entity.Company;
import cn.yu2.baomihua.openapi.entity.MsgHistory;

/**
 * @author uskytec
 *
 */
public interface ICompanyModule {
	/**
	 * 
	 * @return
	 */

	public List<Company> getCompanyList();
	
	

	/**
	 * 存储消息
	 * <p>Title: saveMsgHistory</p>  
	 * <p>Description: </p>  
	 * @param msgHistory
	 */
	public void saveMsgHistory(MsgHistory msgHistory);
	/**
	 * 根据企业信息查询结果
	 * <p>Title: search</p>  
	 * <p>Description: </p>  
	 * @param company
	 * @return
	 */
	public Map<String, Object> search(String companyInfo);

}
