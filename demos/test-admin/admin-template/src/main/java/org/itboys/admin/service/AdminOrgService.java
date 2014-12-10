package org.itboys.admin.service;

import javax.annotation.Resource;

import org.itboys.admin.entity.AdminOrg;
import org.itboys.mongodb.core.MongoDataSource;
import org.springframework.stereotype.Service;

/**
 * 组织的service层
 * @author WeiSky
 *
 */
@Service
public class AdminOrgService extends BaseAdminService<AdminOrg, Long>{

	@Resource(name="adminDS")
	private MongoDataSource ds;
	
	@Override
	protected MongoDataSource getMongoDataSource() {
		return ds;
	}

	@Override
	protected Class<AdminOrg> getEntityClass() {
		return AdminOrg.class;
	}

}
