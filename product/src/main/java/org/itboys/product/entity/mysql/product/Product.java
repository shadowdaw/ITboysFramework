package org.itboys.product.entity.mysql.product;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.itboys.product.entity.mysql.BaseEntity;

/**
 * 通用商品
 * 
 * @author huml
 */
public class Product extends BaseEntity {

	private static final long serialVersionUID = 3743705530517013442L;


	private Long adminUserId;
	private Long shopId;
	private Long shopCatId;
	private String name;
	private BigDecimal price;
	private BigDecimal discountPrice;
	private BigDecimal marketPrice;
	private String detailImage;
	private Date startTime;
	private Date endTime;
	private Long catId;
	private Long brandId;
	private String fullCatidPath;
	private Integer type; // 商品类型 各自系统自行定义
	private String modele;
	private String createFrom;
	private Integer isRecommend; // 是否推荐
	private Integer isDeleted;
	private String fied1; // 预留备用字段1
	private String fied2; // 预留备用字段2
	private String fied3; // 预留备用字段3
	private String fied4; // 预留备用字段4
	private String fied5; // 预留备用字段5
	private Integer score;// 购买商品赠送积分
	private Integer isNew;// 是否新品
	private BigDecimal weight; // 商品的净含量(如：2.5g)
	private Integer maxNum;// 商品的最大购买量
	private Integer status;

	public static Integer TYPE1 = 1;
	public static Integer TYPE2 = 2;
	public static Integer TYPE3 = 3;
	public static Integer TYPE4 = 4;

	public static Integer ISDELETED_1 = 1; // 新建
	public static Integer ISDELETED_2 = 2; // 审核通过
	public static Integer ISDELETED_3 = 3; // 审核不通过
	public static Integer ISDELETED_0 = 0; // 删除

	public static Integer ISRECOMMEND_NO = 1; // 不推荐
	public static Integer ISRECOMMEND_YES = 2; // 推荐

	private String keyword; // 外键字段
	private ProductText ptext;

	private String listImage; // 手机图片列表
	private Long productStore; // 商品库存
	/******* 非持久字段 ********/
	private String catName;
	private String brandName;
	private Long orderNum;//商品排序

	private List<ProductDetail> details;// 商品规格列表
	
	public Long getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(Long adminUserId) {
		this.adminUserId = adminUserId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getShopCatId() {
		return shopCatId;
	}

	public void setShopCatId(Long shopCatId) {
		this.shopCatId = shopCatId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public List<ProductDetail> getDetails() {
		return details;
	}

	public void setDetails(List<ProductDetail> details) {
		this.details = details;
	}

	public Long getProductStore() {
		return productStore;
	}

	public void setProductStore(Long productStore) {
		this.productStore = productStore;
	}

	/**
	 * 是否上架
	 * 
	 * @return
	 */
	public boolean isInStart() {
		return startTime != null && this.startTime.before(new Date());
	}

	/**
	 * 是否下架
	 * 
	 * @return
	 */
	public boolean isInEnd() {
		return endTime != null && endTime.before(new Date());
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getListImage() {
		return listImage;
	}

	public void setListImage(String listImage) {
		this.listImage = listImage;
	}

	private List<ProductExt> productExts;

	public List<ProductExt> getProductExts() {
		return productExts;
	}

	public void setProductExts(List<ProductExt> productExts) {
		this.productExts = productExts;
	}
	
	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductText getPtext() {
		return ptext;
	}

	public Integer getIsNew() {
		return isNew;
	}

	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}

	public void setPtext(ProductText ptext) {
		this.ptext = ptext;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	public String getFied1() {
		return fied1;
	}

	public void setFied1(String fied1) {
		this.fied1 = fied1;
	}

	public String getFied2() {
		return fied2;
	}

	public void setFied2(String fied2) {
		this.fied2 = fied2;
	}

	public String getFied3() {
		return fied3;
	}

	public void setFied3(String fied3) {
		this.fied3 = fied3;
	}

	public String getFied4() {
		return fied4;
	}

	public void setFied4(String fied4) {
		this.fied4 = fied4;
	}

	public String getFied5() {
		return fied5;
	}

	public void setFied5(String fied5) {
		this.fied5 = fied5;
	}

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getDetailImage() {
		return detailImage;
	}

	public void setDetailImage(String detailImage) {
		this.detailImage = detailImage;
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

	public Long getCatId() {
		return catId;
	}

	public void setCatId(Long catId) {
		this.catId = catId;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getFullCatidPath() {
		return fullCatidPath;
	}

	public void setFullCatidPath(String fullCatidPath) {
		this.fullCatidPath = fullCatidPath;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public String getCreateFrom() {
		return createFrom;
	}

	public void setCreateFrom(String createFrom) {
		this.createFrom = createFrom;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public Long getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Long orderNum) {
		this.orderNum = orderNum;
	}
}