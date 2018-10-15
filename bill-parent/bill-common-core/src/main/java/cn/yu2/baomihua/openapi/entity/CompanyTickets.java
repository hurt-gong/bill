package cn.yu2.baomihua.openapi.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @author uskytec
 *
 */
@TableName(value = "companyTickets")
public class CompanyTickets implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId
	private Long id;

	/**  */
	private String openData;

	/**  */
	private String province;

	/**  */
	private String companyName;
	
	/** 企业纳税人识别号  */
	private String companyCode;
	
	/**
	 * 发票号码
	 */
	private String ticketsNumber;
	
	/**
	 * 发票代码
	 */
	private String ticketsCode;
	
	/**
	 * 买方企业名称
	 */
	
	private String buyCompany;
	
	/**
	 * 买方企业地址
	 */
	private String buyCompanyAddress;
	
	/**
	 * 销售额
	 */
	private String saleVolume;
	
	/**
	 * 状态普票，红票，废票
	 */
	private Integer status;
	
	/**
	 * msgID
	 */
	private String msgId;
	
	/**
	 * 插入时间
	 */
	private String insertData;
	
	/**
	 * 备注信息
	 */
	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getInsertData() {
		return insertData;
	}

	public void setInsertData(String insertData) {
		this.insertData = insertData;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	 
	public String getOpenData() {
		return openData;
	}

	public void setOpenData(String openData) {
		this.openData = openData;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getTicketsNumber() {
		return ticketsNumber;
	}

	public void setTicketsNumber(String ticketsNumber) {
		this.ticketsNumber = ticketsNumber;
	}

	public String getTicketsCode() {
		return ticketsCode;
	}

	public void setTicketsCode(String ticketsCode) {
		this.ticketsCode = ticketsCode;
	}

	public String getBuyCompany() {
		return buyCompany;
	}

	public void setBuyCompany(String buyCompany) {
		this.buyCompany = buyCompany;
	}

	public String getBuyCompanyAddress() {
		return buyCompanyAddress;
	}

	public void setBuyCompanyAddress(String buyCompanyAddress) {
		this.buyCompanyAddress = buyCompanyAddress;
	}

	public String getSaleVolume() {
		return saleVolume;
	}

	public void setSaleVolume(String saleVolume) {
		this.saleVolume = saleVolume;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
