package org.itboys.product.entity.mysql;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 购物车项实体
 * @author huml
 *
 */
public class CartItem extends BaseEntity{
	
	private static final long serialVersionUID = 7772186070636253598L;
	private Long memberId;
	private String cookie;
	private Long productId;
	private BigDecimal price; //购物车商品原价总和
	private BigDecimal fee; //购物车项目最终报价总和
	private Integer num;
	private Date updateTime;
	private String elements; //下单的一些规格 尺码之类的描述信息
	
	private String guigeIds;
	private Integer type;
	private Long objId;
	
	public String getGuigeIds() {
		return guigeIds;
	}
	public void setGuigeIds(String guigeIds) {
		this.guigeIds = guigeIds;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getObjId() {
		return objId;
	}
	public void setObjId(Long objId) {
		this.objId = objId;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getElements() {
		return elements;
	}
	public void setElements(String elements) {
		this.elements = elements;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
