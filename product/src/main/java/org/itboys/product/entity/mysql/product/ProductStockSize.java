package org.itboys.product.entity.mysql.product;

/**
 * 商品库存实体类
 * @author lenovo
 * 是否需要时间：比如修改时间，更新时间，谁修改的，谁增加的
 */
public class ProductStockSize {

	private Long id;
	private Long productId;		//商品id
	private Long detailCodeId1;	//特征值id：比如颜色id或尺码id
	private Long detailCodeId2;	//特征值id：比如尺码id或颜色i；---这里是是
	private Long detailCodeId3;	//备用字段 ：用于更多的特征配置
	private Long detailCodeId4;	//备用字段：用于更多的特征配置
	private Long stockSize;		//库存量
	private Long minSellNum;	//最小开卖数量:
	
	public Long getMinSellNum() {
		return minSellNum;
	}
	public void setMinSellNum(Long minSellNum) {
		this.minSellNum = minSellNum;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getDetailCodeId1() {
		return detailCodeId1;
	}
	public void setDetailCodeId1(Long detailCodeId1) {
		this.detailCodeId1 = detailCodeId1;
	}
	public Long getDetailCodeId2() {
		return detailCodeId2;
	}
	public void setDetailCodeId2(Long detailCodeId2) {
		this.detailCodeId2 = detailCodeId2;
	}
	public Long getDetailCodeId3() {
		return detailCodeId3;
	}
	public void setDetailCodeId3(Long detailCodeId3) {
		this.detailCodeId3 = detailCodeId3;
	}
	public Long getDetailCodeId4() {
		return detailCodeId4;
	}
	public void setDetailCodeId4(Long detailCodeId4) {
		this.detailCodeId4 = detailCodeId4;
	}
	public Long getStockSize() {
		return stockSize;
	}
	public void setStockSize(Long stockSize) {
		this.stockSize = stockSize;
	}
}
