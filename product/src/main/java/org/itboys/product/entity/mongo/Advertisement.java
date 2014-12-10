package org.itboys.product.entity.mongo;

import java.util.Date;

import org.itboys.portal.entity.PortalBaseEntity;

import com.google.code.morphia.annotations.Entity;

/**
 * 广告
 * @author huml
 */
@Entity(value = "Advertisement", noClassnameStored = true)
public class Advertisement extends PortalBaseEntity {

	private static final long serialVersionUID = 707547592220989529L;
	private Long adzoneId;
	private String name;
	private Integer type;
	private String page;
	private String desc;
	private String ext;
	private Date startTime;
	private Date endTime;
	private Integer status;
	private Integer layoutType; //广告位布局类型
	
//	private List<AdvertisementItem> items ;
	
	public static Integer TYPE_1 = 1;//纯图片广告
	public static Integer TYPE_2 = 2;//图片加标题加内容
	public static Integer TYPE_3 = 3;//标题加内容
	public static Integer TYPE_4 = 4;//自定义html代码(包括js和css)广告联盟头发时候用 5:flash'
	
	public static Integer STATUS_0 = 0;//创建
	public static Integer STATUS_1 = 1;//审核通过生效
	public static Integer STATUS_2 = 2;//审核部不通过 下架
	public static Integer STATUS_3 = 3;//直接下架
	public static Integer STATUS_4 = 4;//删除
	
	public static Integer LAYOUTTYPE_1 = 1;//忽略行列
	public static Integer LAYOUTTYPE_2 = 2;//指定行列 为 2时候 创意元素必须置顶行列
	
	
	public Long getAdzoneId() {
		return adzoneId;
	}
	public void setAdzoneId(Long adzoneId) {
		this.adzoneId = adzoneId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getLayoutType() {
		return layoutType;
	}
	public void setLayoutType(Integer layoutType) {
		this.layoutType = layoutType;
	}
	
	
	
	
	
}
