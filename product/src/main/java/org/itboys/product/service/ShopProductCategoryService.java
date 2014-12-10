package org.itboys.product.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.service.BaseService;
import org.itboys.product.dto.ShopProductCategoryDTO;
import org.itboys.product.entity.mongo.ShopProductCategory;
import org.itboys.product.service.product.ProductBO;
import org.itboys.product.tools.SessionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopProductCategoryService extends BaseShopService<ShopProductCategory, Long> {
	private static final long serialVersionUID = 5802326050136311559L;
	@Autowired
	private ProductBO productBO;
	@Resource(name="productDS")
	private MongoDataSource shopProductCategoryDataSource;
	@Override
	protected MongoDataSource getMongoDataSource() {
		return shopProductCategoryDataSource;
	}

	@Override
	protected Class<ShopProductCategory> getEntityClass() {
		return ShopProductCategory.class;
	}
	
	public List<ShopProductCategory> getByShopId(Long shopId){
		return findByField("shopId", shopId);
	}
	
	public void doSave(ShopProductCategory category){
		category.setCreateTime(new Date());
		category.setCreator(SessionHolder.getAdminUserId());
		category.setUpdateTime(new Date());
		category.setUpdater(SessionHolder.getAdminUserId());
		save(category);
	}
	
	public void doBatchSave(List<ShopProductCategory> list){
		for(ShopProductCategory category : list){
			category.setCreateTime(new Date());
			category.setCreator(SessionHolder.getAdminUserId());
			category.setUpdateTime(new Date());
			category.setUpdater(SessionHolder.getAdminUserId());
		}
		batchSave(list);
	}
	/**
	 * When t contains a exactly Id,update this entity.
	 * @param t
	 */
	public void doUpdate(ShopProductCategory t){
		t.setUpdateTime(new Date());
		t.setUpdater(SessionHolder.getAdminUserId());
		update(t);
	}
	
	public ShopProductCategoryDTO findDTOById(Long id){
		ShopProductCategoryDTO dto = new ShopProductCategoryDTO();
		getById(id);
		dto.setShopProductCategory(getById(id));
		dto.setProduct(productBO.findByShopCatId(id));
		return dto;
	}
	
}
