/**  

* <p>Title: CompanyMapper.java</p>  

* <p>Description: </p>  

* <p>Copyright: Copyright (c) 2017</p>  

* <p>Company: baomihua</p>  

* @author hurt-gong

* @date 2018年10月11日  

* @version 1.0  

*/  
package cn.yu2.baomihua.openapi.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;

import cn.yu2.baomihua.openapi.entity.bastpay.BastCompanyDetil;

/**  

* <p>Title: CompanyMapper</p>  

* <p>Description: </p>  

* @author hurt-gong  

* @date 2018年10月11日  

*/
public interface BastCompanyDetilMapper extends AutoMapper<BastCompanyDetil> {

	public BastCompanyDetil getBastCompanyDetail(@Param("productNo") String productNo);
	
}
