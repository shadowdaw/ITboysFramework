package org.itboys.portal.service;

import java.util.List;

import javax.annotation.Resource;

import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.commons.utils.collection.CommonCollectionUtils;
import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.portal.entity.PortalAppElement;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Service
public class PortalAppElementService extends BasePortalService<PortalAppElement, Long> {

	private static final long serialVersionUID = 485244681206071096L;
	
	@Resource(name="portalDS")
	private MongoDataSource elementDataSource;

	@Override
	protected MongoDataSource getMongoDataSource() {
		return elementDataSource;
	}

	@Override
	protected Class<PortalAppElement> getEntityClass() {
		return PortalAppElement.class;
	}
	
	public void deleteByNav(Long navId){
		List<PortalAppElement> list = findByField("navId", navId);
		for(PortalAppElement element : list){
			delete(element.getId());
		}
	}
	
	public void insertNavItems(final Long navId,List<Long> itemIds){
		deleteByNav(navId);
		if(navId==null || CommonCollectionUtils.isEmpty(itemIds)){
			return ;
		}
		List<PortalAppElement> navItems = Lists.transform(itemIds, new Function<Long, PortalAppElement>() {
			@Override
			public PortalAppElement apply(Long input) {
				PortalAppElement appElement = new PortalAppElement();
				appElement.setNavId(navId);
				appElement.setElementId(input);
				AdminSessionHolder.prepareAdminAndProjectLoginData(appElement);
				return appElement;
			}
		});
		batchSave(navItems);
	}

}
