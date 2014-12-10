package org.itboys.product.entity.mysql.product;

import org.itboys.product.entity.mysql.BaseEntity;

/**
 * 商品品牌
 * @author huml
 * 
 */
public class ProductBrand extends BaseEntity {
	
	private static final long serialVersionUID = 8038598826068825877L;
	
	public static Integer ISRECOMMEND_NO = 1;//不推荐
	public static Integer ISRECOMMEND_YES = 2;//推荐
	
	private String name;//品牌名称
	private String enName;//品牌英文名
	private String logo;//品牌logo
	private String title;//品牌宣言 比如 耐克 just do it 安踏 我选择 我喜欢
	private Long catId;//一个品牌
	private Integer isRecommend; //是否推荐
	
	/*************针对目前主流品牌购物网站 和品牌团 对品牌进行的一些扩展字段*********/
	private String bigLog;//品牌log大图片地址 可以当做唯品会之类网站的活动主图
	private String mobileLogo;//品牌手机端显示logo
	private String mobiBigLogo;//品牌手机端显示大log
	private Integer descType;//品牌介绍展示方式 1:flash 2:大字段详细描述 3:flash+大字段详细描
	private String descFlashUrl;//品牌介绍 flash的url
	private String  desc;//'品牌详细介绍
	private String simpleDesc;//品牌简单描述
	private Long sellerId;//品牌关联卖家的ID 不需要的填0
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getCatId() {
		return catId;
	}
	public void setCatId(Long catId) {
		this.catId = catId;
	}
	public String getBigLog() {
		return bigLog;
	}
	public void setBigLog(String bigLog) {
		this.bigLog = bigLog;
	}
	public String getMobileLogo() {
		return mobileLogo;
	}
	public void setMobileLogo(String mobileLogo) {
		this.mobileLogo = mobileLogo;
	}
	public String getMobiBigLogo() {
		return mobiBigLogo;
	}
	public void setMobiBigLogo(String mobiBigLogo) {
		this.mobiBigLogo = mobiBigLogo;
	}
	public Integer getDescType() {
		return descType;
	}
	public void setDescType(Integer descType) {
		this.descType = descType;
	}
	public String getDescFlashUrl() {
		return descFlashUrl;
	}
	public void setDescFlashUrl(String descFlashUrl) {
		this.descFlashUrl = descFlashUrl;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getSimpleDesc() {
		return simpleDesc;
	}
	public void setSimpleDesc(String simpleDesc) {
		this.simpleDesc = simpleDesc;
	}
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	
	
	
	
	
}
