package cn.yu2.baomihua.openapi.module;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.openapi.entity.Company;
import cn.yu2.baomihua.openapi.entity.MsgHistory;
import cn.yu2.baomihua.openapi.service.ICompanyService;
import cn.yu2.baomihua.openapi.service.ICompanyTicketService;
import cn.yu2.baomihua.openapi.service.IMsgHistoryService;

/**  
* <p>Title: CompanyModuleImpl</p>  
* <p>Description: </p>  
* @author hurt-gong  
* @date 2018年10月11日  
*/  
public class CompanyModuleImpl extends AbstractModule implements ICompanyModule {

	@Autowired
	private ICompanyService companyService;
 
	@Autowired
	private ICompanyTicketService companyTicketService;
	
	@Autowired
	private IMsgHistoryService msgHistoryService;
 
	@Override
	public List<Company> getCompanyList() { 
		return companyService.getCompanyList();
	}
 
	/**
	 * 存储消息
	 * <p>Title: saveMsgHistory</p>  
	 * <p>Description: </p>  
	 * @param msgHistory
	 */
	@Override
	public void saveMsgHistory(MsgHistory msgHistory){
		msgHistoryService.saveMsgHistory(msgHistory);
	}

	
	/**
	 * 根据企业信息查询结果
	 * <p>Title: search</p>  
	 * <p>Description: </p>  
	 * @param company
	 * @return
	 */
	@Override
	public Map<String, Object> search(String companyInfo){
		return companyTicketService.search(companyInfo);
	}
}
