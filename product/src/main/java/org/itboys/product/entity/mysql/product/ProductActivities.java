package org.itboys.product.entity.mysql.product;

import java.math.BigDecimal;
import java.util.Date;

import org.itboys.product.entity.mysql.BaseEntity;

/**
 * 商品活动信息
 * @author huml
 *
 */
public class ProductActivities extends BaseEntity {

	private static final long serialVersionUID = 122225555566666L;
	
	private Long id;
	private Long productBrandId;//商品品牌id
	private Long keeperId;//商家id
	private String activitieName;//活动名称
	private String activitieText;//活动主内容
	private Date startTime;//开始时间
	private Date endTime;//结束时间
	private Integer sort;//排序
	private Integer isDeleted;
	private String activitieImage;//活动图片
	private Integer isActivity;//1.正在活动  2.不是
	private String littleLogo;//活动小logo
	private BigDecimal minDiscount;//活动商品的最小折扣
	private BigDecimal maxDiscount;//活动商品最大折扣,根据品牌下所有商品的折扣计算出来
	private BigDecimal minPrice;//活动最低价格
	private Integer showType;//活动价格展示方式:0.最低价格 1.折扣
	private String middlePic;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProductBrandId() {
		return productBrandId;
	}
	public void setProductBrandId(Long productBrandId) {
		this.productBrandId = productBrandId;
	}
	public Long getKeeperId() {
		return keeperId;
	}
	public void setKeeperId(Long keeperId) {
		this.keeperId = keeperId;
	}
	public String getActivitieName() {
		return activitieName;
	}
	public void setActivitieName(String activitieName) {
		this.activitieName = activitieName;
	}
	public String getActivitieText() {
		return activitieText;
	}
	public void setActivitieText(String activitieText) {
		this.activitieText = activitieText;
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
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getActivitieImage() {
		return activitieImage;
	}
	public void setActivitieImage(String activitieImage) {
		this.activitieImage = activitieImage;
	}
	public Integer getIsActivity() {
		return isActivity;
	}
	public void setIsActivity(Integer isActivity) {
		this.isActivity = isActivity;
	}
	public String getLittleLogo() {
		return littleLogo;
	}
	public void setLittleLogo(String littleLogo) {
		this.littleLogo = littleLogo;
	}
	public BigDecimal getMinDiscount() {
		return minDiscount;
	}
	public void setMinDiscount(BigDecimal minDiscount) {
		this.minDiscount = minDiscount;
	}
	public BigDecimal getMaxDiscount() {
		return maxDiscount;
	}
	public void setMaxDiscount(BigDecimal maxDiscount) {
		this.maxDiscount = maxDiscount;
	}
	public BigDecimal getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}
	public Integer getShowType() {
		return showType;
	}
	public void setShowType(Integer showType) {
		this.showType = showType;
	}
	public String getMiddlePic() {
		return middlePic;
	}
	public void setMiddlePic(String middlePic) {
		this.middlePic = middlePic;
	}

}
