package org.itboys.portal.service;

import javax.annotation.Resource;

import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.portal.entity.PortalAppNav;
import org.springframework.stereotype.Service;

@Service
public class PortalAppNavService extends BasePortalService<PortalAppNav, Long> {

	private static final long serialVersionUID = -1284184649376008691L;
	@Resource(name = "portalDS")
	private MongoDataSource navDataSource;

	@Override
	protected MongoDataSource getMongoDataSource() {
		return navDataSource;
	}

	@Override
	protected Class<PortalAppNav> getEntityClass() {
		return PortalAppNav.class;
	}

}
