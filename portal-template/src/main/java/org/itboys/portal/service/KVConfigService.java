package org.itboys.portal.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.portal.entity.KVConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KVConfigService extends BasePortalService<KVConfig, Long> {

	private static final long serialVersionUID = -3216626697613729504L;
	
	@Resource(name="portalDS")
	private MongoDataSource configDataSource;
	@Override
	protected MongoDataSource getMongoDataSource() {
		return configDataSource;
	}

	@Override
	protected Class<KVConfig> getEntityClass() {
		return KVConfig.class;
	}
	
	public void insert(KVConfig config){
		if(StringUtils.isBlank(config.getAppKey())){
			config.setAppKey(KVConfig.DEFAULT_APP_KEY);
		}
		AdminSessionHolder.prepareAdminAndProjectLoginData(config);
		save(config);
	}
	
	public void doUpdate(KVConfig config){
		AdminSessionHolder.prepareAdminAndProjectLoginData(config);
		update(config);
	};
	
	/**
	 * 根据key获得默认配置的值
	 * @param key
	 * @return
	 */
	public String getConfigValue(String key){
		return getConfigValue(KVConfig.DEFAULT_APP_KEY, key);
	}
	
	/**
	 * 根据appkey 和key 获取配置的值
	 * @param appkey
	 * @param key
	 * @return
	 */
	public String getConfigValue(String appkey,String key){
		List<KVConfig> list = list(new HashMap<String,Object>());
		for(KVConfig config:list){
			if(StringUtils.equals(appkey, config.getAppKey()) && StringUtils.equals(key, config.getKey()))
				return config.getValue();
		}
		return null;
	}
}
