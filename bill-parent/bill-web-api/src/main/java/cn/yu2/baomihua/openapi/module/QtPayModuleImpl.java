package cn.yu2.baomihua.openapi.module;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.openapi.service.IQtPayService;

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
public class QtPayModuleImpl extends AbstractModule implements IQtPayModule {

	@Autowired
	private IQtPayService qtPayService;

	/**
	 * 下订单
	 * <p>
	 * Title: proCreateOrder
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 */
	public Map<String, Object> proCreateOrder(Map<String, Object> parmap) {
		return qtPayService.proCreateOrder(parmap);

	}

	public Map<String, Object> callBack(Map<String, Object> parmap, String reqsign) {
		return qtPayService.callBack(parmap, reqsign);
	}

}
