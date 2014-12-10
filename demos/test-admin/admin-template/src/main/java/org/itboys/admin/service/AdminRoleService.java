package org.itboys.admin.service;

import javax.annotation.Resource;

import org.itboys.admin.entity.AdminRole;
import org.itboys.mongodb.core.MongoDataSource;
import org.springframework.stereotype.Service;

/**
 * 角色service
 * @author WeiSky
 *
 */
@Service
public class AdminRoleService extends BaseAdminService<AdminRole, Long>{
	
	@Resource(name="adminDS")
	private MongoDataSource ds;

	@Override
	protected MongoDataSource getMongoDataSource() {
		return ds;
	}

	@Override
	protected Class<AdminRole> getEntityClass() {
		return AdminRole.class;
	}

}
