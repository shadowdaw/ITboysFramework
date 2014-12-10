package org.itboys.admin.service;

import javax.annotation.Resource;

import org.itboys.admin.entity.AdminPermission;
import org.itboys.mongodb.core.MongoDataSource;
import org.springframework.stereotype.Service;

/**
 * 权限service
 * @author WeiSky
 *
 */
@Service
public class AdminPermissionService extends BaseAdminService<AdminPermission, Long>{

	@Resource(name="adminDS")
	private MongoDataSource ds;
	
	@Override
	protected MongoDataSource getMongoDataSource() {
		return ds;
	}

	@Override
	protected Class<AdminPermission> getEntityClass() {
		return AdminPermission.class;
	}

}
