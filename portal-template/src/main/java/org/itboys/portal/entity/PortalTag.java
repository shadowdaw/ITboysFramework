package org.itboys.portal.entity;

import org.itboys.admin.entity.BaseAdminEntity;

import com.google.code.morphia.annotations.Entity;

/**
 * 标签类管理
 * @author huml
 *
 */
@Entity(value = "PortalTag", noClassnameStored = true)
public class PortalTag extends PortalBaseEntity{
	
	private static final long serialVersionUID = 2516715262802960657L;
	private String name;//'标签名称',
	private Long itemId;//某个栏目下的标签
	private String type;//'1.信息 2...或其它'
	private String desc;//'描述',
		
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
