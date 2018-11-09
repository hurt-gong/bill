package cn.yu2.baomihua.openapi.entity.bastpay;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @author uskytec
 *
 */
@TableName(value = "bast_fee_pay")
public class BastCompanyFeePay implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId
	private Long id;
	/** 登录号 */
	private String productNo;

	/** 交易商户号 */
	private String merchantNo;

	/** 商户订单号 */
	private String outTradeNo;

	/** 订单金额，单位分 */
	private String tradeAmt;

	/** 商品信息，在翼支付账单里展示 */
	private String goodsInfo;

	/** 下订单时间 */
	private String opertionTime;

	/** 更新时间 */
	private String updatTime;

 

	/**
	 * 翼支付订单状态，详见“七：订单状态” SUCCESS 交易成功 FAIL 交易失败，同一商户订单号不可再重复下单，但基于同一订单号可再次发起支付
	 * CLOSE 订单关闭，原因可能为订单超时等 WAITFORPAY 等待支付或退款中
	 * 
	 */
	private String tradeStatus;

	/** 收单订单号 */
	private String tradeprodNo;
	
	/**  交易号 **/
	private String tradeNo;
	
	/**  交易号 **/
	private String merchantOrderNo;

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

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTradeAmt() {
		return tradeAmt;
	}

	public void setTradeAmt(String tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	public String getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(String goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public String getOpertionTime() {
		return opertionTime;
	}

	public void setOpertionTime(String opertionTime) {
		this.opertionTime = opertionTime;
	}

	public String getUpdatTime() {
		return updatTime;
	}

	public void setUpdatTime(String updatTime) {
		this.updatTime = updatTime;
	}

 

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getTradeprodNo() {
		return tradeprodNo;
	}

	public void setTradeprodNo(String tradeprodNo) {
		this.tradeprodNo = tradeprodNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}

	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}

}
