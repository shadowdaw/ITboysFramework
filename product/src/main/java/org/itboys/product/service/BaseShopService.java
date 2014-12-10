package org.itboys.product.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.itboys.mongodb.service.BaseService;
import org.itboys.mongodb.utils.page.Page;
import org.itboys.mongodb.utils.page.PageQueryParam;
import org.itboys.mongodb.utils.query.PageQueryUtils;
import org.itboys.mongodb.utils.query.QueryParamUtils;

import com.google.code.morphia.query.Query;

public abstract class BaseShopService<T, K> extends BaseService<T, K> {

	private static final long serialVersionUID = -2846009899980731779L;

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
	
	public Long count(Map<String, Object> param){
		Iterator<String> ite = param.keySet().iterator();
		Query<T> query = getMongoDataSource().createQuery(getEntityClass()).filter("isDeleted", 0);
		while (ite.hasNext()) {
			String key = ite.next();
			Object value = param.get(key);
			query = query.filter(key, value);
		}
		query = query
				.order(StringUtils.isEmpty((String)param.get("orderByKey"))?"id":(String)param.get("orderByKey"))
				.offset(StringUtils.isEmpty((String)param.get("pageNoKey"))?0:(Integer)param.get("pageNoKey"))
				.limit(StringUtils.isEmpty((String)param.get("pageSizeKey"))?10:(Integer)param.get("pageSizeKey"));
		return getMongoDataSource().getCount(query);
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
		query = query
				.order(page.getSortField())
				.offset(page.getPageIndex())
				.limit(page.getPageSize());
		final List<T> list = query
							.asList();
		
		return new Page<T>(list, page.getPageIndex(), page.getPageSize(), count);
	}
	
	public void doDelete(K id){
		update(id, "isDeleted", 1);
	}
}
