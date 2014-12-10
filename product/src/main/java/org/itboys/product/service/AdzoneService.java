package org.itboys.product.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.admin.tools.LoginHolder;
import org.itboys.admin.tools.WebConstants;
import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.utils.page.Page;
import org.itboys.mongodb.utils.page.PageQueryParam;
import org.itboys.mongodb.utils.query.PageQueryUtils;
import org.itboys.mongodb.utils.query.QueryParamUtils;
import org.itboys.product.entity.mongo.Advertisement;
import org.itboys.product.entity.mongo.Adzone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.morphia.query.Query;
import com.google.common.collect.Maps;

/**
 * 广告位
 * @author huml
 */
@Service
public class AdzoneService extends BaseShopService<Adzone, Long>{
	
	private static final long serialVersionUID = 1868411823507317460L;
	
	@Resource(name="productDS")
	private MongoDataSource adzoneDataSource;
	
	@Override
	protected MongoDataSource getMongoDataSource() {
		return adzoneDataSource;
	}

	@Override
	protected Class<Adzone> getEntityClass() {
		return Adzone.class;
	}

	@Autowired
	private AdvertisementService advertisementService;
	
	public void insert(Adzone entity){
		//这个只能admin 用户搞 所以取admin用户的session key
		LoginHolder.prepareLoginMessage(entity,WebConstants.ALL_USER_SESSIONKEY);
		entity.setCreateTime(new Date());
		entity.setCreator(AdminSessionHolder.getAdminUserId());
		entity.setUpdater(AdminSessionHolder.getAdminUserId());
		entity.setUpdateTime(new Date());
		super.save(entity);
	}
	
	public void update(Adzone entity){
		//这个只能admin 用户搞 所以取admin用户的session key
		LoginHolder.prepareLoginMessage(entity,WebConstants.ALL_USER_SESSIONKEY);
		Adzone item = getById(entity.getId());
		entity.setCreateTime(item.getCreateTime());
		entity.setCreator(item.getCreator());
		entity.setUpdater(AdminSessionHolder.getAdminUserId());
		entity.setUpdateTime(new Date());
		super.update(entity);
	}
	
	@Transactional
	public void deleteById(Long id){
		advertisementService.deleteByAdzoneId(id);
		doDelete(id);
	}
	
	/**
	 * 更新状态
	 * @param entity
	 * @return
	 */
	public int updateStatus(Integer status,Long id){
		return update(id, "status", status);
	}
}
