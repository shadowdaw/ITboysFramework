package org.itboys.portal.entity;

import org.itboys.admin.entity.BaseAdminEntity;

import com.google.code.morphia.annotations.Entity;

/**
 * portal导航布局表
 * @author Administrator
 *
 */
@Entity(value = "PortalAppNav", noClassnameStored = true)
public class PortalAppNav extends PortalBaseEntity {
	private static final long serialVersionUID = 2706660234624569000L;
	public static final Integer IS_LEF_YES=1;//是叶子节点
	public static final Integer IS_LEF_NO=0;//不是叶子节点
	
	private String appKey;
	private String channel;
	private String type;
	private Long parentId;
	private String content;
	private String script;
	private Integer isLef;//是否叶子节点 1是 0不是
	//以下为非持久字段
	private String appName;
	private String channelName;
	private String name;

	public Long get_parentId(){
		return this.parentId;
	}
	
	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public Integer getIsLef() {
		return isLef;
	}

	public void setIsLef(Integer isLef) {
		this.isLef = isLef;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
