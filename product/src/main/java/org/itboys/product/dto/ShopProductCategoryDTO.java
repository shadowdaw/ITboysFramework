package org.itboys.product.dto;

import java.util.List;

import org.itboys.product.entity.mongo.ShopProductCategory;
import org.itboys.product.entity.mysql.product.Product;

public class ShopProductCategoryDTO {
	private ShopProductCategory shopProductCategory;
	private List<Product> product;
	public ShopProductCategory getShopProductCategory() {
		return shopProductCategory;
	}
	public void setShopProductCategory(ShopProductCategory shopProductCategory) {
		this.shopProductCategory = shopProductCategory;
	}
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
}
