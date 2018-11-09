package cn.yu2.baomihua.openapi.entity.bastpay;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @author uskytec
 *
 */
@TableName(value = "bast_company_detail")
public class BastCompanyDetil implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId
	private Long id;
	/** 登录号 */
	private String productNo;

	/** 企业名称 */
	private String merchantName;

	/**税号  */
	private String taxNumber;

	 
	/** 地区编码 */
	private String areaCode;

	/** 注册类型 */
	private String registerType;

	/**企业类型  */
	private String merchantType;
	
	/** 税务机关代码 */
	private String taxAuthorityCode;

	/**企业地址 */
	private String enterpriseAddress;

	/**开票机号  */
	private String machineCode;
	
	/**开票人手机号 */
	private String invoicePhone;
	
	
	/**邮箱  */
	private String email;

	 
	/** 最大开票点数 */
	private String maxInvoiceNumber;
 

	/**备案号  */
	private String recordNumber;
	
	/** 法人身份证号 */
	private String cardNumber;

	/**法人手机号  */
	private String legalPhone;

	/**联系人姓名  */
	private String contantName;
	
	/**软件代码 */
	private String sorfwareCode;
	
	
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


	public String getMerchantName() {
		return merchantName;
	}


	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}


	public String getTaxNumber() {
		return taxNumber;
	}


	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}


	public String getAreaCode() {
		return areaCode;
	}


	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}


	public String getRegisterType() {
		return registerType;
	}


	public void setRegisterType(String registerType) {
		this.registerType = registerType;
	}


	public String getMerchantType() {
		return merchantType;
	}


	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}


	public String getTaxAuthorityCode() {
		return taxAuthorityCode;
	}


	public void setTaxAuthorityCode(String taxAuthorityCode) {
		this.taxAuthorityCode = taxAuthorityCode;
	}


	public String getEnterpriseAddress() {
		return enterpriseAddress;
	}


	public void setEnterpriseAddress(String enterpriseAddress) {
		this.enterpriseAddress = enterpriseAddress;
	}


	public String getMachineCode() {
		return machineCode;
	}


	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}


	public String getInvoicePhone() {
		return invoicePhone;
	}


	public void setInvoicePhone(String invoicePhone) {
		this.invoicePhone = invoicePhone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMaxInvoiceNumber() {
		return maxInvoiceNumber;
	}


	public void setMaxInvoiceNumber(String maxInvoiceNumber) {
		this.maxInvoiceNumber = maxInvoiceNumber;
	}


	public String getRecordNumber() {
		return recordNumber;
	}


	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}


	public String getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	public String getLegalPhone() {
		return legalPhone;
	}


	public void setLegalPhone(String legalPhone) {
		this.legalPhone = legalPhone;
	}


	public String getContantName() {
		return contantName;
	}


	public void setContantName(String contantName) {
		this.contantName = contantName;
	}


	public String getSorfwareCode() {
		return sorfwareCode;
	}


	public void setSorfwareCode(String sorfwareCode) {
		this.sorfwareCode = sorfwareCode;
	}


	public String getInsetDate() {
		return insetDate;
	}


	public void setInsetDate(String insetDate) {
		this.insetDate = insetDate;
	}


	private String insetDate;

}
