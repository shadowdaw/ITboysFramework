package org.itboys.admin.service;

import java.util.List;

import org.itboys.admin.entity.BaseAdminEntity;
import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.mongodb.entity.BaseLongIdEntity;
import org.itboys.mongodb.service.BaseService;

import com.google.code.morphia.query.UpdateOperations;

public abstract class BaseAdminService<T, K> extends BaseService<T, K> {

	public void save(T t){
		if(t instanceof BaseAdminEntity){
			BaseAdminEntity entity = initBaseAdminEntity(t);
			entity.setCr(AdminSessionHolder.getAdminUserId());
			entity.setUr(AdminSessionHolder.getAdminUserId());
		}
		super.save(t);
	}
	
	public void batchSave(List<T> list){
		for(T t:list){
			if(t instanceof BaseLongIdEntity){
				BaseAdminEntity entity = initBaseAdminEntity(t);
				entity.setCr(AdminSessionHolder.getAdminUserId());
				entity.setUr(AdminSessionHolder.getAdminUserId());
			}
		}
		super.batchSave(list);
	}

	
	/**
	 * 不需要考虑登入信息的保存
	 * @param t
	 */
	public void saveWithoutLogin(T t){
		if(t instanceof BaseAdminEntity){
			BaseAdminEntity entity = initBaseAdminEntity(t);
			entity.setCr(1L);
			entity.setUr(1L);
		}
		super.save(t);
	}
	
	/**
	 * 不需要考虑登入信息的批量保存
	 * @param t
	 */
	public void saveWithoutLogin(List<T> list){
		for(T t:list){
			if(t instanceof BaseLongIdEntity){
				BaseAdminEntity entity = initBaseAdminEntity(t);
				entity.setCr(1L);
				entity.setUr(1L);
			}
		}
		super.batchSave(list);
	}
	
	public int update(K id,UpdateOperations<T> ops){
		ops.set("ur", AdminSessionHolder.getAdminUserId());
		ops.set("ut", System.currentTimeMillis());
		return super.update(id, ops);
	}
	

	private BaseAdminEntity initBaseAdminEntity(T t) {
		BaseAdminEntity entity = (BaseAdminEntity)t;
		long now = System.currentTimeMillis();
		entity.setCt(now);
		entity.setUt(now);
		return entity;
	}
}
