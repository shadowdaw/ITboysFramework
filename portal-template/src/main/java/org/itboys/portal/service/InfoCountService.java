package org.itboys.portal.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.service.BaseService;
import org.itboys.portal.entity.InfoCount;
import org.springframework.stereotype.Service;


@Service
public class InfoCountService extends BaseService<InfoCount, Long> {

	private static final long serialVersionUID = -348564729488336293L;
	@Resource(name="portalDS")
	private MongoDataSource infoCountDataSource;
	@Override
	protected MongoDataSource getMongoDataSource() {
		return infoCountDataSource;
	}

	@Override
	protected Class<InfoCount> getEntityClass() {
		return InfoCount.class;
	}
	
	/**
	 * 浏览次数
	 * @param blogId
	 * @param viewCount
	 * @return
	 * update info_count set view_count=view_count+#{viewCount} where info_id=#{infoId}
	 */
	public void updateViewCount(Long infoId,Integer viewCount){
		List<InfoCount> list = findByField("infoId", infoId);
		for(InfoCount infoCount : list){
			update(infoCount.getId(), "viewCount", viewCount+infoCount.getViewCount());
		}
	}
	
	/**
	 * 评论次数
	 * @param blogId
	 * @param count1
	 * @return
	 */
	public void updateCount1(Long infoId,Integer count1){
		List<InfoCount> list = findByField("infoId", infoId);
		for(InfoCount infoCount : list){
			update(infoCount.getId(), "viewCount", count1+infoCount.getCount1());
		}
	}

	/**
	 * 收藏次数
	 * @param blogId
	 * @return
	 */
	public void updateCollectCount(Long infoId){
		List<InfoCount> list = findByField("infoId", infoId);
		for(InfoCount infoCount : list){
			update(infoCount.getId(), "viewCount", 1+infoCount.getCollectCount());
		}
	}
	
	/**
	 * 被赞或被顶的次数
	 * @param blogId
	 * @return
	 */
	public void updateGoodCount(Long infoId){
		List<InfoCount> list = findByField("infoId", infoId);
		for(InfoCount infoCount : list){
			update(infoCount.getId(), "viewCount", 1+infoCount.getGoodCount());
		}
	}

}
