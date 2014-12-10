package org.itboys.product.entity.mysql;

import java.math.BigDecimal;

/**
 * 三方支付交易记录实体类
 */
public class OrderPayRecord {
	
	private Long id;
	private Long orderId;//订单ID
	private String externalId;//外部订单号
	private Integer type;// '支付平台类型 0-支付宝  2-财付通 3-支付宝(网银) 4-财付通(网银) 等等 待定'
	private Integer status;//外部交易状态 1成功 2未付款 3取消 待定其他'
	private BigDecimal fee;//外站支付的金额
	private String returnParam;//外部回调参数 varchar(2000)
	private String outTradeNo;//外部交易号
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public String getReturnParam() {
		return returnParam;
	}
	public void setReturnParam(String returnParam) {
		this.returnParam = returnParam;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	
}
