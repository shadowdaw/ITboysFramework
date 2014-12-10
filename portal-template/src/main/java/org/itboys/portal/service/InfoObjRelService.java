package org.itboys.portal.service;

import java.util.List;

import javax.annotation.Resource;

import org.itboys.admin.entity.AdminPermission;
import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.service.BaseService;
import org.itboys.portal.entity.InfoObjRel;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Service
public class InfoObjRelService extends BaseService<InfoObjRel, Long>{

	private static final long serialVersionUID = 6639984939060516824L;
	@Resource(name="portalDS")
	private MongoDataSource infoObjReldDataSource;
	@Override
	protected MongoDataSource getMongoDataSource() {
		return infoObjReldDataSource;
	}

	@Override
	protected Class<InfoObjRel> getEntityClass() {
		return InfoObjRel.class;
	}
	
	public List<Long> findByObjIdAndType(Long objId, Integer type){
		List<InfoObjRel> list = infoObjReldDataSource.createQuery(getEntityClass()).filter("objId", objId)
				.filter("type", type).asList();
		return Lists.transform(list, new Function<InfoObjRel, Long>() {
			@Override
			public Long apply(InfoObjRel permission) {
				return permission.getId();
			}
		});
	}

	public void deleteByObjId(Long objId){
		List<InfoObjRel> list = findByField("objId", objId);
		for(InfoObjRel rel : list){
			delete(rel.getId());
		}
	}
	
	public void insert(InfoObjRel entity){
		List<InfoObjRel> list = findByField("infoId", entity.getInfoId());
		for(InfoObjRel rel : list){
			delete(rel.getId());
		}
		save(entity);
	}
	
	/**
	 * 获取info所关联的所有对象id
	 * @param infoId
	 * @return
	 */
	public List<Long> getObjIdsByInfoId(Long infoId){
		List<InfoObjRel> productObjRels =  findByField("infoId", infoId);
		List<Long> objIds = Lists.transform(productObjRels, new Function<InfoObjRel, Long>() {
			@Override
			public Long apply(InfoObjRel input) {
				return input.getObjId();
			}
		});
		return  objIds;
	}
	
	/**
	 * 获取某个对象关联的信息
	 * @param infoId
	 * @return
	 */
	public List<Long> getInfoIdByObj(Long objId){
		List<InfoObjRel> productObjRels =  findByField("objId", objId);
		List<Long> objIds = Lists.transform(productObjRels, new Function<InfoObjRel, Long>() {
			@Override
			public Long apply(InfoObjRel input) {
				return input.getInfoId();
			}
		});
		return  objIds;
	}

}
