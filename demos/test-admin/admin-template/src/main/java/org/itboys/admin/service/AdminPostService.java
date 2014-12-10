package org.itboys.admin.service;

import javax.annotation.Resource;

import org.itboys.admin.entity.AdminPost;
import org.itboys.mongodb.core.MongoDataSource;
import org.springframework.stereotype.Service;

/**
 * 职务service
 * @author WeiSky
 *
 */
@Service
public class AdminPostService extends BaseAdminService<AdminPost, Long>{

	@Resource(name="adminDS")
	private MongoDataSource ds;
	
	@Override
	protected MongoDataSource getMongoDataSource() {
		return ds;
	}

	@Override
	protected Class<AdminPost> getEntityClass() {
		return AdminPost.class;
	}

}
