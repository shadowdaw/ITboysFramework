package org.itboys.product.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.itboys.admin.entity.AdminUser;
import org.itboys.admin.service.AdminOrgService;
import org.itboys.admin.service.AdminUserService;
import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.admin.tools.LoginHolder;
import org.itboys.admin.tools.WebConstants;
import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.product.entity.mongo.AdvertisementItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

/**
 * 广告 素材
 * @author huml
 */
@Service
public class AdvertisementItemService extends BaseShopService<AdvertisementItem, Long> {
	
	private static final long serialVersionUID = -675582776314331293L;
	
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private AdminOrgService adminOrgService;
	
	@Resource(name="productDS")
	private MongoDataSource adverItemDataSource;
	
	@Override
	protected MongoDataSource getMongoDataSource() {
		return adverItemDataSource;
	}

	@Override
	protected Class<AdvertisementItem> getEntityClass() {
		return AdvertisementItem.class;
	}

	/**
	 * 根据广告id查询广告素材信息
	 * 一个广告对应多条广告素材信息
	 * @return
	 */
	public List<AdvertisementItem> getListByAdvertisementId(Long advertisementId){
		Map<String, Object> param = Maps.newHashMapWithExpectedSize(1);
		param.put("advertisementId", advertisementId);
		return list(param);
	}
	
	
	/**
	 * 插入操作
	 * @param entity
	 */
	public void save(AdvertisementItem entity){
		LoginHolder.prepareLoginMessage(entity,WebConstants.ALL_USER_SESSIONKEY);
		entity.setCreateTime(new Date());
		entity.setCreator(AdminSessionHolder.getAdminUserId());
		entity.setUpdater(AdminSessionHolder.getAdminUserId());
		entity.setUpdateTime(new Date());
		
		AdminUser user = adminUserService.getById(AdminSessionHolder.getAdminUserId());
		entity.setOrgId(user.getOrgId());
		if(user.getOrgId() != 0L){
			entity.setOrgName(adminOrgService.getById(user.getOrgId()).getFullNamePath());
		}
		super.save(entity);
	}
	
	/**
	 * 更新操作
	 * @param entity
	 * @return
	 */
	public void update(AdvertisementItem entity){
		LoginHolder.prepareLoginMessage(entity,WebConstants.ALL_USER_SESSIONKEY);
		AdvertisementItem item = getById(entity.getId());
		entity.setCreateTime(item.getCreateTime());
		entity.setCreator(item.getCreator());
		entity.setOrgId(item.getOrgId());
		entity.setUpdater(AdminSessionHolder.getAdminUserId());
		entity.setUpdateTime(new Date());
		super.update(entity);
	}
	
	/**
	 * 删除
	 */
	public void deleteByAdvertisementId(Long advertisementId){
		List<AdvertisementItem> list = getListByAdvertisementId(advertisementId);
		for(AdvertisementItem item : list){
			update(item.getId(), "isDeleted", 1);
		}
	}

}
