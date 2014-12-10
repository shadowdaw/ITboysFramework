package org.itboys.portal.service;


import java.awt.Color;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.itboys.commons.utils.image.ImageWatermark;
import org.itboys.framework.resource.ResourceHolder;
import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.service.BaseService;
import org.itboys.portal.entity.Watermark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.code.morphia.query.Query;
import com.google.common.collect.Maps;

@Service
public class WatermarkService extends BaseService<Watermark, Long> {

	private static final long serialVersionUID = 2102597953073407585L;
	
	@Autowired
	private KVConfigService kvConfigService;
	@Autowired
	private ResourceHolder resourceHolder;
	
	public final String IMG_PATH= "D:/images/pretty";
	
	@Resource(name="portalDS")
	private MongoDataSource waterDataSource;
	@Override
	protected MongoDataSource getMongoDataSource() {
		return waterDataSource;
	}

	@Override
	protected Class<Watermark> getEntityClass() {
		return Watermark.class;
	}
	
	/**
	 * 根据配置项处理水印图片
	 * @param waterMark KV_CONFIG表里使用哪个水印ID的配置
	 * @param filePath 对哪个物理路径的图片的处理
	 */
	public void prepareWaterMark(String waterMark, String filePath) {
		if(StringUtils.isNotBlank(waterMark)){
			String	value=kvConfigService.getConfigValue(waterMark);
			if(NumberUtils.isDigits(value)){
				long waterMarkId = Long.parseLong(value);
				Watermark wm = getById(waterMarkId);
				if(wm!=null&&wm.getStatus()==1){
					if(wm.getType()==Watermark.TYPE_IMG){
						ImageWatermark.pressImage(IMG_PATH+filePath, IMG_PATH+wm.getWatermark(), wm.getX(), wm.getY(),wm.getAlpha().floatValue(), wm.getFlag());
					}else{
						String[] colors = StringUtils.split(StringUtils.trim(wm.getColor()),"_");
						ImageWatermark.pressText(IMG_PATH+ filePath,  IMG_PATH+wm.getWatermark(), wm.getFontName(), wm.getFontStyle(), wm.getFontSize(), 
								new Color(Integer.parseInt(colors[0]),Integer.parseInt(colors[1]),Integer.parseInt(colors[2])),
								wm.getX(), wm.getY(),wm.getAlpha().floatValue(), wm.getFlag());
					}
				}
			}
		}
	}
	
	public List<Watermark>list(Map<String,Object> param){
		Iterator<String> ite = param.keySet().iterator();
		Query<Watermark> query = getMongoDataSource().createQuery(getEntityClass());
		while (ite.hasNext()) {
			String key = ite.next();
			Object value = param.get(key);
			query = query.filter(key, value);
		}
		return query.asList();
	}
	
	/**
	 * 获取所有的水印信息
	 * @param projectId
	 * @return
	 */
	public List<Watermark> getWatermarkList(){
		Map<String,Object> param = Maps.newHashMapWithExpectedSize(0);
		return list(param);
	}
	

}
