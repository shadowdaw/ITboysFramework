package org.itboys.product.entity.mysql.product;

import java.io.Serializable;

/**
 * 商品计数器表
 * @author huml
 * 
 */
public class ProductCount implements Serializable {

	
	private static final long serialVersionUID = 7124723631232884549L;
	
	private Long productId;
	private Integer productStore;//商品库存

	private Integer viewCount;  //被浏览次数
	private Integer buyedCount;  //被购买次数
	private Integer collectCount;  //被收藏次数
	private Integer goodCommentCount;  //好评数
	private Integer middleCommentCount;  //中评数
	private Integer badCommentCount;  //差评数
	private Integer commentCount;  //总评数
	
	
	public Integer getProductStore() {
		return productStore;
	}
	public void setProductStore(Integer productStore) {
		this.productStore = productStore;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Integer getViewCount() {
		return viewCount;
	}
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}
	public Integer getBuyedCount() {
		return buyedCount;
	}
	public void setBuyedCount(Integer buyedCount) {
		this.buyedCount = buyedCount;
	}
	public Integer getCollectCount() {
		return collectCount;
	}
	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}
	public Integer getGoodCommentCount() {
		return goodCommentCount;
	}
	public void setGoodCommentCount(Integer goodCommentCount) {
		this.goodCommentCount = goodCommentCount;
	}
	public Integer getMiddleCommentCount() {
		return middleCommentCount;
	}
	public void setMiddleCommentCount(Integer middleCommentCount) {
		this.middleCommentCount = middleCommentCount;
	}
	public Integer getBadCommentCount() {
		return badCommentCount;
	}
	public void setBadCommentCount(Integer badCommentCount) {
		this.badCommentCount = badCommentCount;
	}
	public Integer getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	
	
	
}
