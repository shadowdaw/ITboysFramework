package org.itboys.product.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.utils.page.Page;
import org.itboys.product.entity.mongo.AdvertisementApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

@Service
public class AdvertisementApplyService extends BaseShopService<AdvertisementApply, Long> {

	private static final long serialVersionUID = -5138660869536539969L;
	
	@Resource(name="productDS")
	private MongoDataSource advertisementApplyDataSource;
	
	@Override
	protected MongoDataSource getMongoDataSource() {
		return advertisementApplyDataSource;
	}

	@Override
	protected Class<AdvertisementApply> getEntityClass() {
		return AdvertisementApply.class;
	}
	
	public void save(AdvertisementApply entity){
		entity.setCreateTime(new Date());
		entity.setCreator(AdminSessionHolder.getAdminUserId());
		entity.setUpdater(AdminSessionHolder.getAdminUserId());
		entity.setUpdateTime(new Date());
		super.save(entity);
	}
	
	public void update(AdvertisementApply entity){
		AdvertisementApply apply = getById(entity.getId());
		entity.setCreateTime(apply.getCreateTime());
		entity.setCreator(apply.getCreator());
		entity.setUpdater(AdminSessionHolder.getAdminUserId());
		entity.setUpdateTime(new Date());
		super.update(entity);
	}
	
	public void doDetele(Long id){
		update(id, "isDeleted", 1);
	}

}
