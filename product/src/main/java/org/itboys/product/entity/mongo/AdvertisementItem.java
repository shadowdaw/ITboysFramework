package org.itboys.product.entity.mongo;

import org.itboys.portal.entity.PortalBaseEntity;

import com.google.code.morphia.annotations.Entity;

/**
 * 广告元素 素材
 * @author huml
 */
@Entity(value = "AdvertisementItem", noClassnameStored = true)
public class AdvertisementItem extends PortalBaseEntity {

	private static final long serialVersionUID = -4391081694513027782L;
	private Long advertisementId;
	private Long orgId;
	private String orgName;
	private String title;
	private String filePath;
	private String clickurl;
	private String content;
	private Integer col;
	private Integer row;
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public Integer getCol() {
		return col;
	}
	public void setCol(Integer col) {
		this.col = col;
	}
	public Integer getRow() {
		return row;
	}
	public void setRow(Integer row) {
		this.row = row;
	}
	public Long getAdvertisementId() {
		return advertisementId;
	}
	public void setAdvertisementId(Long advertisementId) {
		this.advertisementId = advertisementId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getClickurl() {
		return clickurl;
	}
	public void setClickurl(String clickurl) {
		this.clickurl = clickurl;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
