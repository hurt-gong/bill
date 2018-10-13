package cn.yu2.baomihua.openapi.module;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.openapi.entity.Company;
import cn.yu2.baomihua.openapi.module.ICompanyModule;
import cn.yu2.baomihua.openapi.service.ICompanyService;

/**  
* <p>Title: CompanyModuleImpl</p>  
* <p>Description: </p>  
* @author hurt-gong  
* @date 2018年10月11日  
*/  
public class CompanyModuleImpl extends AbstractModule implements ICompanyModule {

	@Autowired
	private ICompanyService companyService;
 
 
	@Override
	public List<Company> getCompanyList() { 
		return companyService.getCompanyList();
	}
 

}
