package org.itboys.product.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.admin.tools.LoginHolder;
import org.itboys.admin.tools.WebConstants;
import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.product.entity.mongo.Advertisement;
import org.itboys.product.entity.mongo.AdvertisementItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;

/**
 * 广告
 * @author huml
 */
@Service
public class AdvertisementService extends BaseShopService<Advertisement, Long>{
	
	private static final long serialVersionUID = -3284530979687247931L;

	@Autowired
	private AdvertisementItemService advertisementItemService;
	
	@Resource(name="productDS")
	private MongoDataSource advertisementDataSource;
	
	@Override
	protected MongoDataSource getMongoDataSource() {
		return advertisementDataSource;
	}


	@Override
	protected Class<Advertisement> getEntityClass() {
		return Advertisement.class;
	}
	
	/**
	 * 根据广告位id查询广告信息
	 * 一个广告位对应多条广告信息
	 * @return
	 */
	public List<Advertisement> getListByAdzoneId(Long adzoneId){
		Map<String, Object> param = Maps.newHashMapWithExpectedSize(1);
		param.put("adzoneId", adzoneId);
		return list(param);
	}
	
	
	/**
	 * 插入操作
	 * @param entity
	 */
	public void save(Advertisement entity){
		LoginHolder.prepareLoginMessage(entity,WebConstants.ALL_USER_SESSIONKEY);
		entity.setCreateTime(new Date());
		entity.setCreator(AdminSessionHolder.getAdminUserId());
		entity.setUpdater(AdminSessionHolder.getAdminUserId());
		entity.setUpdateTime(new Date());
		super.save(entity);
	}
	
	/**
	 * 更新操作
	 * @param entity
	 * @return
	 */
	public void update(Advertisement entity){
		LoginHolder.prepareLoginMessage(entity,WebConstants.ALL_USER_SESSIONKEY);
		Advertisement item = getById(entity.getId());
		entity.setCreateTime(item.getCreateTime());
		entity.setCreator(item.getCreator());
		entity.setUpdater(AdminSessionHolder.getAdminUserId());
		entity.setUpdateTime(new Date());
		super.update(entity);
	}
	
	/**
	 * 删除
	 */
	@Transactional
	public void doDelete(Long id){
		update(id, "isDeleted", 1);
	}
	
	/**
	 *  根据广告位id删除
	 */
	@Transactional
	public void deleteByAdzoneId(Long adzoneId){
		List<Advertisement> list = getListByAdzoneId(adzoneId);
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				advertisementItemService.deleteByAdvertisementId(list.get(i).getId());
			}
		}
	}
	
}
