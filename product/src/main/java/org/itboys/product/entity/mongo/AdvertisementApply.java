package org.itboys.product.entity.mongo;

import java.util.Date;

import org.itboys.portal.entity.PortalBaseEntity;

import com.google.code.morphia.annotations.Entity;
/**
 * 广告位申请
 * @author huml
 *
 */
@Entity(value = "AdvertisementApply", noClassnameStored = true)
public class AdvertisementApply extends PortalBaseEntity{

	private static final long serialVersionUID = 898400149962773593L;
	private Long memberId;
	private String name;
	private String phone;
	private String desc;
	private Integer status;

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
