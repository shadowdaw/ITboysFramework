package org.itboys.product.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.service.BaseService;
import org.itboys.mongodb.utils.page.Page;
import org.itboys.product.dto.ShopCategoryDTO;
import org.itboys.product.entity.mongo.Shop;
import org.itboys.product.entity.mongo.ShopCategory;
import org.itboys.product.entity.mongo.ShopProductCategory;
import org.itboys.product.tools.SessionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

@Service
public class ShopCategoryService extends BaseShopService<ShopCategory, Long> {
	private static final long serialVersionUID = -3240156008837513006L;

	@Autowired
	private ShopService shopService;
	
	@Resource(name="productDS")
	private MongoDataSource shopCategoryDataSource;
	
	@Override
	protected MongoDataSource getMongoDataSource() {
		return shopCategoryDataSource;
	}

	@Override
	protected Class<ShopCategory> getEntityClass() {
		return ShopCategory.class;
	}
	
	public void doSave(ShopCategory category){
		category.setCreateTime(new Date());
		category.setCreator(AdminSessionHolder.getAdminUserId());
		category.setUpdateTime(new Date());
		category.setUpdater(AdminSessionHolder.getAdminUserId());
		save(category);
		category.setFullIdPath("/"+category.getParentId()+"/"+category.getId()+"/");
		update(category);
	}
	
	public void doBatchSave(List<ShopCategory> list){
		for(ShopCategory category : list){
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
	public void doUpdate(ShopCategory t){
		ShopCategory category = getById(t.getId());
		t.setCreateTime(category.getCreateTime());
		t.setCreator(category.getCreator());
		t.setUpdateTime(new Date());
		t.setUpdater(AdminSessionHolder.getAdminUserId());
		t.setFullIdPath("/"+t.getParentId()+"/"+t.getId()+"/");
		t.setImage(category.getImage());
		update(t);
	}
	/**
	 * 带子分类及店铺
	 * @param request
	 * @return
	 */
	public Page<ShopCategoryDTO> pageDTO(HttpServletRequest request){
		Page<ShopCategory> page = pageQuery(request);
		List<ShopCategoryDTO> list = new ArrayList<ShopCategoryDTO>();
		for(ShopCategory category : page.getData()){
			List<ShopCategoryDTO> children = new ArrayList<ShopCategoryDTO>();
			if(category.getIsBottom() == 2){
				children = getByParentId(category.getId());
			}
			List<Shop> shops = shopService.getByCatId(category.getId());
			list.add(new ShopCategoryDTO(category, children, shops));
		}
		return new Page<>(page.getTotal(), list);
	}
	
	public List<ShopCategoryDTO> getByParentId(Long parentId){
		Map<String, Object> param = Maps.newHashMapWithExpectedSize(1);
		param.put("parentId", parentId);
		List<ShopCategory> categories = list(param);
		List<ShopCategoryDTO> list = new ArrayList<ShopCategoryDTO>();
		for(ShopCategory category : categories){
			List<ShopCategoryDTO> children = new ArrayList<ShopCategoryDTO>();
			if(category.getIsBottom() == 2){
				children = getByParentId(category.getId());
			}
			List<Shop> shops = shopService.getByCatId(category.getId());
			list.add(new ShopCategoryDTO(category, children, shops));
		}
		return list;
	}
	
	public List<ShopCategory> getChildren(Long parentId){
		Map<String, Object> param = Maps.newHashMapWithExpectedSize(1);
		param.put("parentId", parentId);
		List<ShopCategory> categories = list(param);
		return categories;
	}
	/**
	 * 判断是否有子分类
	 * @param id
	 * @return
	 */
	public boolean hasChild(Long id){
		Map<String, Object> param = Maps.newHashMapWithExpectedSize(1);
		param.put("parentId", id);
		List<ShopCategory> categories = list(param);
		if(categories.isEmpty()){
			return false;
		}
		return true;
	}
	/**
	 * 判断分类下是否有商店
	 * @param id
	 * @return
	 */
	public boolean hasShop(Long id){
		List<Shop> list = shopService.getByCatId(id);
		if(list.isEmpty()){
			return false;
		}else {
			return true;
		}
	}

}
