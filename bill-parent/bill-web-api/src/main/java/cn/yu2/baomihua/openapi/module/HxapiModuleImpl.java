package cn.yu2.baomihua.openapi.module;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.openapi.entity.bastpay.BastCompanyDetil;
import cn.yu2.baomihua.openapi.entity.bastpay.BastServerCompany;
import cn.yu2.baomihua.openapi.service.IHxapiService;

/**
 * <p>
 * Title: CompanyModuleImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author hurt-gong
 * @date 2018年10月11日
 */
public class HxapiModuleImpl extends AbstractModule implements IHxapiModule {

	@Autowired
	private IHxapiService hxapiService;
 

	public  Map<String, Object> getToken(String userName, String password, String requestCode, String taxpayerId,
			String authorizationCode ){
		return hxapiService.getToken(userName, password, requestCode, taxpayerId, authorizationCode);
		
	}
	
}
