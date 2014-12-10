package org.itboys.portal.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.itboys.admin.entity.BaseAdminEntity;
import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.mongodb.entity.BaseLongIdEntity;
import org.itboys.mongodb.service.BaseService;
import org.itboys.mongodb.utils.page.Page;
import org.itboys.mongodb.utils.page.PageQueryParam;
import org.itboys.mongodb.utils.query.PageQueryUtils;
import org.itboys.mongodb.utils.query.QueryParamUtils;
import org.itboys.portal.entity.PortalBaseEntity;

import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;

public abstract class BasePortalService<T, K> extends BaseService<T, K> {

	private static final long serialVersionUID = -3785934491437263541L;

	public void save(T t){
		if(t instanceof BaseAdminEntity){
			PortalBaseEntity entity = initBaseAdminEntity(t);
			entity.setCreator(AdminSessionHolder.getAdminUserId());
			entity.setUpdater(AdminSessionHolder.getAdminUserId());
			entity.setCreateTime(new Date());
			entity.setUpdateTime(new Date());
			entity.setCt(System.currentTimeMillis());
			entity.setUt(System.currentTimeMillis());
		}
		super.save(t);
	}
	
	public void doDelete(K id){
		update(id, "isDeleted", 1);
	}
	
	public void batchSave(List<T> list){
		for(T t:list){
			if(t instanceof BaseLongIdEntity){
				PortalBaseEntity entity = initBaseAdminEntity(t);
				entity.setCreator(AdminSessionHolder.getAdminUserId());
				entity.setUpdater(AdminSessionHolder.getAdminUserId());
				entity.setCreateTime(new Date());
				entity.setUpdateTime(new Date());
				entity.setCt(System.currentTimeMillis());
				entity.setUt(System.currentTimeMillis());
			}
		}
		super.batchSave(list);
	}
	
	public List<T> list(Map<String, Object> param){
		Iterator<String> ite = param.keySet().iterator();
		Query<T> query = getMongoDataSource().createQuery(getEntityClass()).filter("isDeleted", 0);
		while (ite.hasNext()) {
			String key = ite.next();
			Object value = param.get(key);
			query = query.filter(key, value);
		}
		return query.asList();
	}
	
	/**
	 * 不需要考虑登入信息的保存
	 * @param t
	 */
	public void saveWithoutLogin(T t){
		if(t instanceof BaseAdminEntity){
			PortalBaseEntity entity = initBaseAdminEntity(t);
			entity.setCreator(1L);
			entity.setUpdater(1L);
			entity.setCreateTime(new Date());
			entity.setUpdateTime(new Date());
			entity.setCt(System.currentTimeMillis());
			entity.setUt(System.currentTimeMillis());
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
				PortalBaseEntity entity = initBaseAdminEntity(t);
				entity.setCreator(1L);
				entity.setUpdater(1L);
				entity.setCreateTime(new Date());
				entity.setUpdateTime(new Date());
				entity.setCt(System.currentTimeMillis());
				entity.setUt(System.currentTimeMillis());
			}
		}
		super.batchSave(list);
	}
	
	public int update(K id,UpdateOperations<T> ops){
		ops.set("ur", AdminSessionHolder.getAdminUserId());
		ops.set("ut", System.currentTimeMillis());
		return super.update(id, ops);
	}
	

	private PortalBaseEntity initBaseAdminEntity(T t) {
		PortalBaseEntity entity = (PortalBaseEntity)t;
		long now = System.currentTimeMillis();
		entity.setCt(now);
		entity.setUt(now);
		return entity;
	}
	
	/**
	 * 带分页的查询
	 * @param param
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<T> pageQuery(HttpServletRequest request){
		Map<String,Object> param = QueryParamUtils.builderQueryMap(request);
		PageQueryParam<T> page = (PageQueryParam<T>) PageQueryUtils.preparePage(request);
		
		/* 统计总数  */
		Iterator<String> ite = param.keySet().iterator();
		Query<T> query = getMongoDataSource().createQuery(getEntityClass()).filter("isDeleted", 0);
		while (ite.hasNext()) {
			String key = ite.next();
			Object value = param.get(key);
			query = query.filter(key, value);
		}
		final long count = getMongoDataSource().getCount(query);
		/* 返回列表  */
		final List<T> list = query
							.order(page.getSortField())
							.offset(page.getPageIndex())
							.limit(page.getPageSize())
							.asList();
		
		return new Page<T>(count, list);
	}
	
	/**
	 * 带分页的查询
	 * @param param
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<T> pageQuery(Map<String, Object> param){
		
		/* 统计总数  */
		Iterator<String> ite = param.keySet().iterator();
		Query<T> query = getMongoDataSource().createQuery(getEntityClass()).filter("isDeleted", 0);
		while (ite.hasNext()) {
			String key = ite.next();
			Object value = param.get(key);
			if(!key.equals(PageQueryUtils.SORT_FIELD) && !key.equals(PageQueryUtils.PAGE_SIZE) && !key.equals(PageQueryUtils.ROW_START) && !key.equals(PageQueryUtils.SORT_ORDER)){
				query = query.filter(key, value);
			}
		}
		final long count = getMongoDataSource().getCount(query);
		if(StringUtils.isNotBlank((String)param.get(PageQueryUtils.SORT_FIELD))){
			query = query.order((String)param.get(PageQueryUtils.SORT_FIELD));
		}
		if(StringUtils.isNotBlank((String)param.get(PageQueryUtils.ROW_START))){
			query = query.offset((int)param.get(PageQueryUtils.ROW_START));
		}
		if(StringUtils.isNotBlank((String)param.get(PageQueryUtils.PAGE_SIZE))){
			query = query.limit((int)param.get(PageQueryUtils.PAGE_SIZE));
		}
		/* 返回列表  */
		final List<T> list = query.asList();
		
		return new Page<T>(count, list);
	}
	
}
