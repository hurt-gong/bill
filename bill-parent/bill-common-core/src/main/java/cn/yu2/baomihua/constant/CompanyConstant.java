package cn.yu2.baomihua.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Title: CompanyConstant
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author hurt-gong
 * @date 2018年10月11日
 */
public class CompanyConstant {

	public final Map<String, String> COMPANY = new HashMap<String, String>();

	/**
	 * 格式为 K：企业渠道码 V:秘钥
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */
	public CompanyConstant() {
		COMPANY.put("11010101", "bastpay");
	}
}
