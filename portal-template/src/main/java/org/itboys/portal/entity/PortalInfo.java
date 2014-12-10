package org.itboys.portal.entity;

import java.util.Date;

import org.itboys.admin.entity.BaseAdminEntity;

import com.google.code.morphia.annotations.Entity;

/**
 * 独立工程信息管理
 * @author huml
 *
 */
@Entity(value = "PortalInfo", noClassnameStored = true)
public class PortalInfo extends PortalBaseEntity {
	private static final long serialVersionUID = -8232054398674203722L;
	public static final String ATTACHEMENT_TYPE_IMAGE="infoImg";//信息图片
	public static final String ATTACHEMENT_TYPE_ATTACH="infoAttachs";//信息附件
	
	public static final String YES_STATUS="pub";//信息为发布状态
	public static final String CREATE_STATUS="create";//信息为创建状态
	
	public static final int RECOMMENT_YES=2;
	public static final int RECOMMENT_NO=1;
	
	private Long itemId;
	private String title;
	private String subtitle;
	private String summary;
	private String content;
	private String status;// 信息状态 create:创建,pub:发布,back:审核不通过 
	private Integer isHead;
	private Integer isSend;
	private Integer isRecommend;
	private String url;
	private String image;
	private Long publicUser;// 发布者
	private Date publicTime;// 发布时间
	private String listImage;//信息列表图
	private String tjImage;//推荐图片
	private Date lastSendTime;//最后推送时间
	private String source;//信息来源
	private Long tagId;//关联的标签id
	private String author;//作者
	private String keyword;//关键字
	private String field1;
	
	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getIsHead() {
		return isHead;
	}

	public void setIsHead(Integer isHead) {
		this.isHead = isHead;
	}

	public Integer getIsSend() {
		return isSend;
	}

	public void setIsSend(Integer isSend) {
		this.isSend = isSend;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getPublicUser() {
		return publicUser;
	}

	public void setPublicUser(Long publicUser) {
		this.publicUser = publicUser;
	}

	public Date getPublicTime() {
		return publicTime;
	}

	public void setPublicTime(Date publicTime) {
		this.publicTime = publicTime;
	}

	public String getListImage() {
		return listImage;
	}

	public void setListImage(String listImage) {
		this.listImage = listImage;
	}

	public String getTjImage() {
		return tjImage;
	}

	public void setTjImage(String tjImage) {
		this.tjImage = tjImage;
	}

	public Date getLastSendTime() {
		return lastSendTime;
	}

	public void setLastSendTime(Date lastSendTime) {
		this.lastSendTime = lastSendTime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}
}
