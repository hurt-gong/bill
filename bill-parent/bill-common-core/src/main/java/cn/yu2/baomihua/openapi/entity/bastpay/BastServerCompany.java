package cn.yu2.baomihua.openapi.entity.bastpay;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @author uskytec
 *
 */
@TableName(value = "bast_server_company")
public class BastServerCompany implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId
	private Long id;

	/** 登录号 */
	private String productNo;

	/** 收件人 */
	private String addressee;

	/**收件地址  */
	private String address;

	 
	/** 收件省份 */
	private String province;

	/** 电话 */
	private String phone;

	/**企业名称  */
	private String companyName;
	
	/** 服务费 */
	private String serviceCharge;

	/** 快递费 */
	private String expressFee;

	/**生效日期的开始  */
	private String effectDateFrom;
	/**生效日期的结束 */
	private String effectDateTo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getAddressee() {
		return addressee;
	}
	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(String serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	public String getExpressFee() {
		return expressFee;
	}
	public void setExpressFee(String expressFee) {
		this.expressFee = expressFee;
	}
	public String getEffectDateFrom() {
		return effectDateFrom;
	}
	public void setEffectDateFrom(String effectDateFrom) {
		this.effectDateFrom = effectDateFrom;
	}
	public String getEffectDateTo() {
		return effectDateTo;
	}
	public void setEffectDateTo(String effectDateTo) {
		this.effectDateTo = effectDateTo;
	}
	 

}
