package org.itboys.portal.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.portal.entity.PortalTag;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

@Service
public class PortalTagService extends BasePortalService<PortalTag, Long> {

	private static final long serialVersionUID = 7894733236332803771L;
	
	@Resource(name="portalDS")
	private MongoDataSource tagDataSource;
	@Override
	protected MongoDataSource getMongoDataSource() {
		return tagDataSource;
	}

	@Override
	protected Class<PortalTag> getEntityClass() {
		return PortalTag.class;
	}
	
	public List<PortalTag> getAll(Map<String, Object> sqlMap){
		List<PortalTag> list = list(sqlMap);
		return list;
	}
		
}
