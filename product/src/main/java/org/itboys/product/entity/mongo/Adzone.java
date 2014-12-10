package org.itboys.product.entity.mongo;

import org.itboys.portal.entity.PortalBaseEntity;

import com.google.code.morphia.annotations.Entity;

/**
 * 广告位
 * @author huml
 */
@Entity(value = "Adzone", noClassnameStored = true)
public class Adzone extends PortalBaseEntity {

	private static final long serialVersionUID = 2547872236097710637L;

	/**
	 * 默认的配置ForbiddenWords 的projectId 
	 * 为了不写多套 项目的话就项目ID 通用的话就默认为default 的值0
	 */
	public static final Long DEFAULT_PROJECTID= 0l;
	
	private String name;
	private String desc;
	private Integer isTimeBetween;
	private Integer status;
	private String chargingMode;
	private Integer length;
	private Integer width;
	private Integer isSelf;
	private Long fee;
	private String feeUnit;
	
	public static Integer ISTIMEBETWEEN_YES = 1;//1:不包时 自定管理的广告位类型 认为管理的 此时一个adzone_detail只能关联一个 adzone_id 
	public static Integer ISTIMEBETWEEN_NO = 2;//2:分时发布 在一个 start_time end_time之间只能有一条记录
	public static Integer STATUS_0 = 0;//失效
	public static Integer STATUS_1 = 1;//生效
	public static Integer ISSELF_YES = 1;//1:投放自己网站的广告 不计费
	public static Integer ISSELF_NO = 0;//0:投放站内客户或站外客户的广告 计费',
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Integer getIsTimeBetween() {
		return isTimeBetween;
	}
	public void setIsTimeBetween(Integer isTimeBetween) {
		this.isTimeBetween = isTimeBetween;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getChargingMode() {
		return chargingMode;
	}
	public void setChargingMode(String chargingMode) {
		this.chargingMode = chargingMode;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getIsSelf() {
		return isSelf;
	}
	public void setIsSelf(Integer isSelf) {
		this.isSelf = isSelf;
	}
	public Long getFee() {
		return fee;
	}
	public void setFee(Long fee) {
		this.fee = fee;
	}
	public String getFeeUnit() {
		return feeUnit;
	}
	public void setFeeUnit(String feeUnit) {
		this.feeUnit = feeUnit;
	}
	
	
	
	
	
	
}
