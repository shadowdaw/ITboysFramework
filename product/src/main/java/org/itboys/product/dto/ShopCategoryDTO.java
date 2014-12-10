package org.itboys.product.dto;

import java.util.List;

import org.itboys.product.entity.mongo.Shop;
import org.itboys.product.entity.mongo.ShopCategory;

public class ShopCategoryDTO {
	private ShopCategory shopCategory;
	private List<ShopCategoryDTO> children;
	private List<Shop> shops;
	
	public ShopCategoryDTO(){}
	
	public ShopCategoryDTO(ShopCategory shopCategory,List<ShopCategoryDTO> children,List<Shop> shops){
		this.shopCategory = shopCategory;
		this.children = children;
		this.shops = shops;
	}
	
	public ShopCategory getShopCategory() {
		return shopCategory;
	}
	public void setShopCategory(ShopCategory shopCategory) {
		this.shopCategory = shopCategory;
	}
	public List<ShopCategoryDTO> getChildren() {
		return children;
	}
	public void setChildren(List<ShopCategoryDTO> children) {
		this.children = children;
	}

	public List<Shop> getShops() {
		return shops;
	}

	public void setShops(List<Shop> shops) {
		this.shops = shops;
	}
}
