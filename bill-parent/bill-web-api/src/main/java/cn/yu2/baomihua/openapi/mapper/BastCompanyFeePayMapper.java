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

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.AutoMapper;

import cn.yu2.baomihua.openapi.entity.bastpay.BastCompanyFeePay;

/**
 * 
 * <p>
 * Title: CompanyMapper
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * @author hurt-gong
 * 
 * @date 2018年10月11日
 * 
 */
public interface BastCompanyFeePayMapper extends AutoMapper<BastCompanyFeePay> {

	public BastCompanyFeePay getBastCompanyFeePay(@Param("merchantNo") String merchantNo,
			@Param("outTradeNo") String outTradeNo, @Param("tradeAmt") String tradeAmt,
			@Param("tradeNo") String tradeNo);

}
