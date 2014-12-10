package org.itboys.portal.service;

import java.util.List;

import javax.annotation.Resource;

import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.portal.entity.PortalInfoImage;
import org.springframework.stereotype.Service;

@Service
public class PortalInfoImageService extends BasePortalService<PortalInfoImage, Long> {

	private static final long serialVersionUID = 1585966260206169985L;
	
	@Resource(name="portalDS")
	private MongoDataSource imageDataSource;

	@Override
	protected MongoDataSource getMongoDataSource() {
		return imageDataSource;
	}

	@Override
	protected Class<PortalInfoImage> getEntityClass() {
		return PortalInfoImage.class;
	}
	
	/**
	 * 根据productId查询
	 * @param productId
	 */
	public List<PortalInfoImage> findByInfo(Long infoid){
		return imageDataSource.createQuery(getEntityClass()).filter("infoId", infoid)
				.asList();
	}
	
	/**
	 * 删除
	 */
	public void deleteByInfoId(Long infoid){
		List<PortalInfoImage> list = findByInfo(infoid);
		for(PortalInfoImage image : list){
			delete(image.getId());
		}
	}

}
