package org.itboys.product.dto;

import java.util.List;

import org.itboys.product.entity.mongo.Shop;
import org.itboys.product.entity.mongo.ShopImages;
import org.itboys.product.entity.mongo.ShopProductCategory;
import org.itboys.product.entity.mongo.ShopUser;

public class ShopDTO {
	private Shop shop;
	private List<ShopUser> users;
	private List<ShopImages> images;
	private List<ShopProductCategory> categories;
	
	public ShopDTO(){}
	public ShopDTO(Shop shop,List<ShopUser> users,List<ShopImages> images,List<ShopProductCategory> categories){
		this.shop = shop;
		this.users = users;
		this.images = images;
		this.categories = categories;
	}
	public List<ShopProductCategory> getCategories() {
		return categories;
	}
	public void setCategories(List<ShopProductCategory> categories) {
		this.categories = categories;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public List<ShopUser> getUsers() {
		return users;
	}
	public void setUsers(List<ShopUser> users) {
		this.users = users;
	}
	public List<ShopImages> getImages() {
		return images;
	}
	public void setImages(List<ShopImages> images) {
		this.images = images;
	}
}
