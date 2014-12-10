package org.itboys.product.dto.product;

public class ProductCountDTO {

	private Long id;
	private String name;//商品名称
	private Integer buyedCount;//被购买次数
	private Integer viewCount;//浏览次数
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getBuyedCount() {
		return buyedCount;
	}
	public void setBuyedCount(Integer buyedCount) {
		this.buyedCount = buyedCount;
	}
	public Integer getViewCount() {
		return viewCount;
	}
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}
}