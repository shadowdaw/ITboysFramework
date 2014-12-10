package org.itboys.product.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.product.entity.mongo.ShopUser;
import org.springframework.stereotype.Service;

@Service
public class ShopUserService extends BaseShopService<ShopUser, Long> {
	private static final long serialVersionUID = 6203452526502920738L;
	@Resource(name="productDS")
	private MongoDataSource shopUserDataSource;
	@Override
	protected MongoDataSource getMongoDataSource() {
		return shopUserDataSource;
	}

	@Override
	protected Class<ShopUser> getEntityClass() {
		return ShopUser.class;
	}
	
	public List<ShopUser> getByShopId(Long shopId){
		return findByField("shopId", shopId);
	}
	
	public void doSave(ShopUser category){
		category.setCreateTime(new Date());
		category.setCreator(AdminSessionHolder.getAdminUserId());
		category.setUpdateTime(new Date());
		category.setUpdater(AdminSessionHolder.getAdminUserId());
		save(category);
	}
	
	public void doBatchSave(List<ShopUser> list){
		for(ShopUser category : list){
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
	public void doUpdate(ShopUser t){
		ShopUser user = getById(t.getId());
		t.setCreateTime(user.getCreateTime());
		t.setCreator(user.getCreator());
		t.setUpdateTime(new Date());
		t.setUpdater(AdminSessionHolder.getAdminUserId());
		update(t);
	}
	
	/**
	 * 根据用户名查询用户
	 * 
	 * @param id
	 * @return
	 */
	
	public ShopUser getShopUser(String userName) {
		List<ShopUser> list = shopUserDataSource.createQuery(getEntityClass()).filter("userName", userName).asList();
		if(list.isEmpty()){
			list = null;
		}
		return list==null?null:list.get(0);
	}
	
	public void doDelete(Long id){
		update(id, "isDeleted", 1);
	}
	
	public void deleteByShopId(Long shopId){
		List<ShopUser> list = getByShopId(shopId);
		for(ShopUser user : list){
			delete(user.getId());
		}
	}

}
