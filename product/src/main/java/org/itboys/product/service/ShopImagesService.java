package org.itboys.product.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.service.BaseService;
import org.itboys.product.entity.mongo.ShopImages;
import org.itboys.product.entity.mongo.ShopProductCategory;
import org.itboys.product.tools.SessionHolder;
import org.springframework.stereotype.Service;

@Service
public class ShopImagesService extends BaseService<ShopImages, Long> {
	private static final long serialVersionUID = 4169294774486750787L;
	@Resource(name="productDS")
	private MongoDataSource shopImagesDataSource;
	@Override
	protected MongoDataSource getMongoDataSource() {
		return shopImagesDataSource;
	}

	@Override
	protected Class<ShopImages> getEntityClass() {
		return ShopImages.class;
	}
	
	public List<ShopImages> getByShopId(Long shopId){
		return findByField("shopId", shopId);
	}
	
	public void doSave(ShopImages category){
		category.setCreateTime(new Date());
		category.setCreator(AdminSessionHolder.getAdminUserId());
		category.setUpdateTime(new Date());
		category.setUpdater(AdminSessionHolder.getAdminUserId());
		save(category);
	}
	
	public void doBatchSave(List<ShopImages> list){
		for(ShopImages category : list){
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
	public void doUpdate(ShopImages t){
		t.setUpdateTime(new Date());
		t.setUpdater(AdminSessionHolder.getAdminUserId());
		update(t);
	}
	
	public void deleteByShop(Long shopId){
		List<ShopImages> list = findByField("shopId", shopId);
		for(ShopImages images : list){
			delete(images.getId());
		}
	}

}
