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

import java.util.List;
import java.util.Map;

import com.baomidou.framework.service.ISuperService;

import cn.yu2.baomihua.openapi.entity.Company;
import cn.yu2.baomihua.openapi.entity.bastpay.BastCompanyDetil;
import cn.yu2.baomihua.openapi.entity.bastpay.BastServerCompany;
import cn.yu2.baomihua.openapi.mapper.BastServerCompanyMapper;

/**  

* <p>Title: ICompanyService</p>  

* <p>Description: </p>  

* @author hurt-gong  

* @date 2018年10月11日  

*/
public interface IHxapiService extends ISuperService<BastServerCompany> {

	public  Map<String, Object> getToken(String userName, String password, String requestCode, String taxpayerId,
			String authorizationCode );
}
