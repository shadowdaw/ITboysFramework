package org.itboys.product.entity.mysql.product;

import java.math.BigDecimal;

import org.itboys.product.entity.mysql.BaseEntity;

/**
 * 产品规格特征明细 
 * 注意 detail1 detail2 detail3 按升序来存 默认情况下 detail 最多两个 不太可能3个 所以detail3 是将来备用字段 超过3个是不可能的
 * @author huml
 *
 */
public class ProductDetails  extends  BaseEntity{

	private static final long serialVersionUID = -2828544540129951507L;
	
	private Long productId;
	private Long detail1;//关联的特征值1的ID
	private Long detail2;//关联的特征值2的ID
	private Long detail3;//冗余字段 先不管 超过2个规格的很少
	private String bianma;//商品编码
	private String tiaoxingma;//条形码
	private BigDecimal price;//此规格的价格
	private Long count=0L;//库存
	private Integer is_deleted;//是否删除
    //这三个是冗余字段 先不考虑
	private String detailName1;
	private String detailName2;
	private String detailName3;
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getDetail1() {
		return detail1;
	}
	public void setDetail1(Long detail1) {
		this.detail1 = detail1;
	}
	public Long getDetail2() {
		return detail2;
	}
	public void setDetail2(Long detail2) {
		this.detail2 = detail2;
	}
	public Long getDetail3() {
		return detail3;
	}
	public void setDetail3(Long detail3) {
		this.detail3 = detail3;
	}
	public String getBianma() {
		return bianma;
	}
	public void setBianma(String bianma) {
		this.bianma = bianma;
	}
	public String getTiaoxingma() {
		return tiaoxingma;
	}
	public void setTiaoxingma(String tiaoxingma) {
		this.tiaoxingma = tiaoxingma;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Integer getIs_deleted() {
		return is_deleted;
	}
	public void setIs_deleted(Integer is_deleted) {
		this.is_deleted = is_deleted;
	}
	public String getDetailName1() {
		return detailName1;
	}
	public void setDetailName1(String detailName1) {
		this.detailName1 = detailName1;
	}
	public String getDetailName2() {
		return detailName2;
	}
	public void setDetailName2(String detailName2) {
		this.detailName2 = detailName2;
	}
	public String getDetailName3() {
		return detailName3;
	}
	public void setDetailName3(String detailName3) {
		this.detailName3 = detailName3;
	}
}
