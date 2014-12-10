package org.itboys.portal.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.commons.dto.Option;
import org.itboys.commons.utils.time.TimeUtils;
import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.portal.entity.Codes;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


/**
 * 配置表相关业务层
 * 
 * @author huml
 * 
 */
@Service
public class CodesService extends BasePortalService<Codes, Long> {

	private static final long serialVersionUID = -2195132069015638773L;
	
	@Resource(name="portalDS")
	private MongoDataSource codesDataSource;

	@Override
	protected MongoDataSource getMongoDataSource() {
		return codesDataSource;
	}

	@Override
	protected Class<Codes> getEntityClass() {
		return Codes.class;
	}
	

	public void insert(Codes codes) {
		if (StringUtils.isBlank(codes.getRelObjectId())) {
			codes.setRelObjectId(Codes.DEFAULT_REL_OBJ_ID);
		}
		AdminSessionHolder.prepareAdminAndProjectLoginData(codes);
		save(codes);
	}

	public void doUpdate(Codes codes) {
		AdminSessionHolder.prepareAdminAndProjectLoginData(codes);
		update(codes);
	}

	/**
	 * 根据type 查找到codes 的信息 并将 key-value 组装成 Option对象 返回
	 * 
	 * @param type
	 * @return
	 */
	public List<Option> getCodes(String type) {
		return getCodes(Codes.DEFAULT_REL_OBJ_ID, type);
	}

	/**
	 * 根据三个参数 查找到codes 的信息 并将 key-value 组装成 Option对象 返回
	 * 
	 * @param appKey
	 * @param relObjectId
	 * @param type
	 * @return
	 */
	public List<Option> getCodes(String relObjectId, String type) {
		Map<String, Object> map = Maps.newHashMapWithExpectedSize(2);
		map.put("relObjectId", relObjectId);
		map.put("type", type);
		List<Codes> codes = list(map);
		List<Option> options = Lists.newArrayListWithCapacity(codes.size());
		for(Codes code : codes){
			options.add(new Option(code.getKey(), code.getValue()));
		}
		return options;
	}

	/**
	 * 根据type和上次更新时间 获取更新列表
	 * 
	 * @param type
	 * @param date
	 * @return
	 */
	public List<Codes> getCodesByTypeAndUpdateTime(List<String> typeList, String date) {
		Map<String, Object> map = Maps.newHashMapWithExpectedSize(2);
		map.put("type in", typeList);
		map.put("updateTime >", TimeUtils.getDateValue(date));
		return list(map);
	}

	/**
	 * 根据type和上次更新时间 获取更新列表
	 * 
	 * @param type
	 * @param date
	 * @return
	 */
	public List<Codes> getCodesByTypeAndUpdateTime(String type, String date) {
		Map<String, Object> map = Maps.newHashMapWithExpectedSize(2);
		map.put("type", type);
		map.put("updateTime >", TimeUtils.getDateValue(date));
		return list(map);
	}


}
