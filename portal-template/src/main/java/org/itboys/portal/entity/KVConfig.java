package org.itboys.portal.entity;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Indexed;

/**
 * key-value格式的 配置项 
 * @author huml
 *
 */
@Entity(value = "KVConfig", noClassnameStored = true)
public class KVConfig extends PortalBaseEntity {

	private static final long serialVersionUID = 1L;

	public static final String DEFAULT_APP_KEY="0";
	
	private String appKey="0";//不为项目的配置项则为0 否则为项目的code
	@Indexed(unique=true)
	private String key;
	private String value;
	private String desc;
	private Double scale;//   新商店 分红比例
	public Double getScale() {
		return scale;
	}
	public void setScale(Double scale) {
		this.scale = scale;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
