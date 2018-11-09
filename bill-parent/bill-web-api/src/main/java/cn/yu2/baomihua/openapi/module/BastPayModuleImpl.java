package cn.yu2.baomihua.openapi.module;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;

import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.openapi.entity.Company;
import cn.yu2.baomihua.openapi.entity.MsgHistory;
import cn.yu2.baomihua.openapi.entity.bastpay.BastCompanyDetil;
import cn.yu2.baomihua.openapi.entity.bastpay.BastServerCompany;
import cn.yu2.baomihua.openapi.service.IBastPayService;
import cn.yu2.baomihua.openapi.service.ICompanyService;
import cn.yu2.baomihua.openapi.service.ICompanyTicketService;
import cn.yu2.baomihua.openapi.service.IMsgHistoryService;

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
public class BastPayModuleImpl extends AbstractModule implements IBastpayModule {

	@Autowired
	private IBastPayService bastPayService;

	/**
	 * 发送订单请求
	 * <p>
	 * Title: proCreateOrder
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 */
	@Override
	public String proCreateOrder(String money, String goodsInfo) {
		return bastPayService.proCreateOrder(money, goodsInfo);
	}

	@Override
	public Map<String, Object> addBastpayCompany(BastServerCompany bastServerCompany) {
		return bastPayService.addBastpayCompany(bastServerCompany);
	}

	/**
	 * 支付后的回调接口
	 * <p>
	 * Title: bastPayCallBack
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param param
	 * @return
	 */
	@Override
	public Map<String, Object> bastPayCallBack(String param) {
		return bastPayService.bastPayCallBack(param);
	}

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
	@Override
	public Map<String, Object> mergeBasePayDetaiInfo(BastCompanyDetil bastCompanyDetil) {
		return bastPayService.mergeBasePayDetaiInfo(bastCompanyDetil);
	}

}
