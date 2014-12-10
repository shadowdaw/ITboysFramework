package org.itboys.product.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.utils.page.Page;
import org.itboys.product.dto.ShopDTO;
import org.itboys.product.entity.mongo.Shop;
import org.itboys.product.entity.mongo.ShopImages;
import org.itboys.product.entity.mongo.ShopProductCategory;
import org.itboys.product.entity.mongo.ShopUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

@Service
public class ShopService extends BaseShopService<Shop, Long> {
	private static final long serialVersionUID = 1343724116936445848L;
	@Autowired
	private ShopUserService shopUserService;
	@Autowired
	private ShopImagesService shopImagesService;
	@Autowired
	private ShopProductCategoryService shopProductCategoryService;
	
	@Resource(name="productDS")
	private MongoDataSource shopServiceDataSource;
	@Override
	protected MongoDataSource getMongoDataSource() {
		return shopServiceDataSource;
	}

	@Override
	protected Class<Shop> getEntityClass() {
		return Shop.class;
	}
	
	public List<Shop> getByCatId(Long catId){
		Map<String, Object> param = Maps.newHashMap();
		param.put("catId", catId);
		return list(param);
	}
	
	public void doSave(Shop category , List<ShopImages> images){
		category.setCreateTime(new Date());
		category.setCreator(AdminSessionHolder.getAdminUserId());
		category.setUpdateTime(new Date());
		category.setUpdater(AdminSessionHolder.getAdminUserId());
		save(category);
		for(ShopImages image : images){
			image.setShopId(category.getId());
			image.setCreateTime(new Date());
			image.setCreator(AdminSessionHolder.getAdminUserId());
		}
		if(images != null && !images.isEmpty()){
			shopImagesService.batchSave(images);
		}
	}
	
	public void doBatchSave(List<Shop> list){
		for(Shop category : list){
			category.setCreateTime(new Date());
			category.setCreator(AdminSessionHolder.getAdminUserId());
			category.setUpdateTime(new Date());
			category.setUpdater(AdminSessionHolder.getAdminUserId());
		}
		batchSave(list);
	}
	/**
	 * When t contains a exactly Id,update this entity.
	 * @param t
	 */
	public void doUpdate(Shop t,List<ShopImages> images){
		t.setUpdateTime(new Date());
		t.setUpdater(AdminSessionHolder.getAdminUserId());
		update(t);
		if(images != null && !images.isEmpty()){
			for(ShopImages image : images){
				image.setShopId(t.getId());
				image.setCreateTime(new Date());
				image.setCreator(AdminSessionHolder.getAdminUserId());
			}
			shopImagesService.deleteByShop(t.getId());
			shopImagesService.batchSave(images);
		}
	}
	
	/**
	 * 分页查找 包含店铺图片和店铺用户
	 * @param request
	 * @return
	 */
	public Page<ShopDTO> getShopDTO(HttpServletRequest request){
		Page<Shop> page = pageQuery(request);
		List<ShopDTO> list = new ArrayList<ShopDTO>();
		for(Shop shop : page.getData()){
			ShopDTO dto = new ShopDTO();
			dto.setShop(shop);
			dto.setImages(shopImagesService.getByShopId(shop.getId()));
			dto.setUsers(shopUserService.getByShopId(shop.getId()));
			dto.setCategories(shopProductCategoryService.getByShopId(shop.getId()));
			list.add(dto);
		}
		return new Page<ShopDTO>(page.getTotal(), list);
	}
	
	public ShopDTO getShopDTO(Long shopId){
		Shop shop = getById(shopId);
		List<ShopImages> images = shopImagesService.getByShopId(shopId);
		List<ShopUser> users = shopUserService.getByShopId(shopId);
		List<ShopProductCategory> categories = shopProductCategoryService.getByShopId(shopId);
		return new ShopDTO(shop, users, images,categories); 
	}
	
	public void doDelete(Long id){
		update(id, "isDeleted", 1);
		shopImagesService.deleteByShop(id);

	}
	
	

}
