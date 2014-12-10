package org.itboys.mongodb.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.entity.BaseLongIdEntity;
import org.itboys.mongodb.utils.page.Page;
import org.itboys.mongodb.utils.page.PageQueryParam;
import org.itboys.mongodb.utils.query.PageQueryUtils;
import org.itboys.mongodb.utils.query.QueryParamUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;

/**
 * 业务层的父类了
 * @author 俊哥
 * @param <T> mongodb的实体对象
 * @param <K> 主键类型
 */
public abstract class BaseService<T,K> {

	protected abstract MongoDataSource getMongoDataSource();
	
	protected abstract Class<T> getEntityClass();
	
	@Autowired
	protected IDGeneratorService iDGeneratorService;
	
	public T getById(K id){
		return getMongoDataSource().get(getEntityClass(), id);
	}
	
	public void save(T t){
		if(t instanceof BaseLongIdEntity){
			BaseLongIdEntity entity = (BaseLongIdEntity)t;
			entity.setId(getNextId());
		}
		getMongoDataSource().save(t);
	}
	
	public void batchSave(List<T> list){
		for(T t:list){
			if(t instanceof BaseLongIdEntity){
				BaseLongIdEntity entity = (BaseLongIdEntity)t;
				entity.setId(getNextId());
			}
		}
		getMongoDataSource().save(list);
	}
	
	/**
	 * 判断对象含有filed的值是否存在
	 * @param filed
	 * @param value
	 * @return
	 */
	public boolean exists(String filed,Object value){
		return getMongoDataSource().createQuery(getEntityClass()).field(filed).equal(value).get()!=null;
	}
	
	public List<T> getByIds(List<K> ids){
		return getMongoDataSource().getList(getEntityClass(), ids);
	}
	
	public Query<T> getQueryById(K id){
		return getMongoDataSource().getQueryById(id, getEntityClass());
	}
	
	/**
	 * 查找字段名为field 的 并且值为 value的 value 可以为null
	 * @param filed
	 * @param obj
	 * @return
	 */
	public List<T> findByField(String filed,Object value){
		Query<T> query = getQueryByFiledAndValue(filed, value);
		return query.asList();
	}

	/**
	 * 查找字段名为field 的 并且值为 value的 value 的唯一对象
	 * @param filed
	 * @param obj
	 * @return
	 */
	public  T getByField(String filed,Object value){
		Query<T> query = getQueryByFiledAndValue(filed, value);
		return query.get();
	}
	
	public boolean delete(K id){
		return getMongoDataSource().delete(id, getEntityClass());
	}
	/**
	 * When t contains a exactly Id,update this entity.
	 * @param t
	 */
	public void update(T t){
		getMongoDataSource().save(t);
	}
	
	public int update(K id,UpdateOperations<T> ops){
		Query<T> query = getQueryById(id);
		return getMongoDataSource().update(query, ops).getUpdatedCount();
	}
	
	/**
	 * update 单个字段 更新 状态之类的用这个就可以了
	 * @param id
	 * @param filed
	 * @param value
	 * @return
	 */
	public int update(K id,String filed,Object value){
		UpdateOperations<T> ops =  getMongoDataSource().createUpdateOperations(getEntityClass());
		ops.set(filed, value);
		return this.update(id, ops);
	}
	
	/**
	 * 对一些Number 子类类型的字段做自增  操作 比如 int double float bigdecimal 等
	 *  如果做减法 则传负数 类似于 update set a=a+value
	 * @param id
	 * @param filed
	 * @param value
	 * @return
	 */
	public int inc(K id,String filed,Number value){
		UpdateOperations<T> ops =  getMongoDataSource().createUpdateOperations(getEntityClass());
		ops.inc(filed, value);
		return this.update(id, ops);
	}
	
	public long getNextId(){
		return iDGeneratorService.getNextId(getEntityClass().getName());
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
		Query<T> query = getMongoDataSource().createQuery(getEntityClass());
		while (ite.hasNext()) {
			String key = ite.next();
			Object value = param.get(key);
			query.filter(key, value);
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

	private Query<T> getQueryByFiledAndValue(String filed, Object value) {
		Query<T> query = (value==null?
				getMongoDataSource().createQuery(getEntityClass()).field(filed).doesNotExist()
				:
				getMongoDataSource().createQuery(getEntityClass()).field(filed).equal(value));
		return query;
	}
	
	
}
