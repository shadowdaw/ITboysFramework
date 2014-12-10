package org.itboys.product.entity.mysql;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.itboys.member.entity.mongo.Member;

/**
 * 订单表映射实体类
 * 该实体类中status为-1时表示为删除状态
 */

public class Order extends BaseEntity {
	
	private static final long serialVersionUID = -3514971165161041123L;
	
	private String orderNo;// 订单编号,唯一
	private Long memberId;// 关联的会员ID
	private Integer status;// 订单状态
	private Integer payType;// 付款类型 0-线下付款 2-定金(部分在线支付) 3-全额在线支付 4信用支付 5信用卡分期付款  等等 待定
	private Integer payMethod;// 如果有金额是支付方式 0-支付宝 2-财付通 3-支付宝(网银) 4-财付通(网银) 5-直接网银 6-快钱 7-信用度 其他待定
	private Date createTime;// 完成时间
	private Date updateTime;// 完成时间
	private Date finishedTime;// 完成时间
	private BigDecimal totalFee;// 原始总价
	private BigDecimal fee;
	private BigDecimal expressFee;// 物流费用金额
	private BigDecimal expressAddFee;// 偏远地区物流补贴费用 没有填0
	private String memo; // 订单备注
	private BigDecimal orderFee;// 定金 不需要定金的不管
	private BigDecimal periodFee;// 分期付款的每期金额 多少期 如果分期是挂在订单行上的 忽略订单
	private Integer periodCount;// 分期付款的话 多少期 如果分期是挂在订单行上的 忽略订单'
	private Long contactId;//关联的联系地址或物流地址的ID'
	private String mobile;// 手机号
	private String name;// 收货人姓名
	private String email;// 邮箱地址
	private Date payTime;// 付款时间
	private Date cancelTime;// 取消时间
	private String cancelReason;// 取消原因
	private String wyCode;// 网银编码
	private Long sellerId;// 有卖家的话 就是卖家ID 否则就是0
	private Long objId; // 扩展关联字段1
	private Long objId2; // 扩展关联字段2
	private Integer isUsedVirtual;// 否使用了虚拟币1:使用了 0:没用
	
	private String express;//哪个物流公司 varchar(16) 关联数据字典表
	private String expressNo;//物流单号 varchar(32) 快递单号
	private Date sendTime;//发货时间 datetime 发货时间
	private Date receiveTime;//收货时间 datetime 收货时间
	
	private List<OrderItem> items ;
	private Member member;
	
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Integer getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}
	public Date getFinishedTime() {
		return finishedTime;
	}
	public void setFinishedTime(Date finishedTime) {
		this.finishedTime = finishedTime;
	}
	public BigDecimal getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}
	public BigDecimal getExpressFee() {
		return expressFee;
	}
	public void setExpressFee(BigDecimal expressFee) {
		this.expressFee = expressFee;
	}

	public BigDecimal getExpressAddFee() {
		return expressAddFee;
	}
	public void setExpressAddFee(BigDecimal expressAddFee) {
		this.expressAddFee = expressAddFee;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public BigDecimal getOrderFee() {
		return orderFee;
	}
	public BigDecimal getPeriodFee() {
		return periodFee;
	}
	public void setPeriodFee(BigDecimal periodFee) {
		this.periodFee = periodFee;
	}
	public Integer getPeriodCount() {
		return periodCount;
	}
	public void setPeriodCount(Integer periodCount) {
		this.periodCount = periodCount;
	}
	public Long getContactId() {
		return contactId;
	}
	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Date getCancelTime() {
		return cancelTime;
	}
	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public String getWyCode() {
		return wyCode;
	}
	public void setWyCode(String wyCode) {
		this.wyCode = wyCode;
	}
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	public Long getObjId() {
		return objId;
	}
	public void setObjId(Long objId) {
		this.objId = objId;
	}

	public Long getObjId2() {
		return objId2;
	}
	public void setObjId2(Long objId2) {
		this.objId2 = objId2;
	}
	public void setOrderFee(BigDecimal orderFee) {
		this.orderFee = orderFee;
	}
	public Integer getIsUsedVirtual() {
		return isUsedVirtual;
	}
	public void setIsUsedVirtual(Integer isUsedVirtual) {
		this.isUsedVirtual = isUsedVirtual;
	}
	public String getExpress() {
		return express;
	}
	public void setExpress(String express) {
		this.express = express;
	}
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Date getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

}
