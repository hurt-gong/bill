package cn.yu2.baomihua.openapi.module;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;

import cn.yu2.baomihua.manage.entity.BookTag;
import cn.yu2.baomihua.openapi.entity.Company;
import cn.yu2.baomihua.openapi.entity.MsgHistory;
import cn.yu2.baomihua.openapi.entity.bastpay.BastCompanyDetil;
import cn.yu2.baomihua.openapi.entity.bastpay.BastServerCompany;

/**
 * @author uskytec
 *
 */
public interface IBastpayModule {
	
	public String proCreateOrder(String money, String goodsInfo);
	
	
	public Map<String, Object> addBastpayCompany(BastServerCompany bastServerCompany);

	
	public Map<String, Object> bastPayCallBack(String param);
	
	
	/**
	 * 对服务信息进行操作
	 * <p>
	 * Title: mergeBasePayDetaiInfo
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param bastCompanyDetil
	 * @return
	 */
	public Map<String, Object> mergeBasePayDetaiInfo(BastCompanyDetil bastCompanyDetil) ;
}
