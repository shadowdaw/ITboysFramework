package org.itboys.product.entity.mysql;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * 订单明细表映射实体类
 * 该实体类中status为-1时表示为删除状态
 */
public class OrderItem extends BaseEntity {

	private static final long serialVersionUID = -874989911524541130L;
	private  Long id;
	private Long orderId;
	private String itemNo;
	private  Long productId;
	private String productName;
	private String productImg;
	private Integer num;
	private BigDecimal totalFee;
	private BigDecimal fee;
	private Date createTime;
	private Date updateTime;
	private Long memberId;
	private String elements;
	private Integer status;
	private Integer commentStatus;
	private BigDecimal orderedFee;
	private BigDecimal periodFee;
	private Integer periodCount;
	private Integer badStatus;
	
	private Long objId;
	private Long obj2Id;
	
	
	
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
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public BigDecimal getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getElements() {
		return elements;
	}
	public void setElements(String elements) {
		this.elements = elements;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCommentStatus() {
		return commentStatus;
	}
	public void setCommentStatus(Integer commentStatus) {
		this.commentStatus = commentStatus;
	}
	
	public BigDecimal getOrderedFee() {
		return orderedFee;
	}
	public void setOrderedFee(BigDecimal orderedFee) {
		this.orderedFee = orderedFee;
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
	public Integer getBadStatus() {
		return badStatus;
	}
	public void setBadStatus(Integer badStatus) {
		this.badStatus = badStatus;
	}
	public Long getObjId() {
		return objId;
	}
	public void setObjId(Long objId) {
		this.objId = objId;
	}
	public Long getObj2Id() {
		return obj2Id;
	}
	public void setObj2Id(Long obj2Id) {
		this.obj2Id = obj2Id;
	}

	
}
