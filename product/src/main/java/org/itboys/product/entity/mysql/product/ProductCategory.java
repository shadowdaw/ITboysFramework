package org.itboys.product.entity.mysql.product;

import java.util.List;

import org.itboys.product.entity.mysql.BaseEntity;

/**
 * 产品分类表
 * @author huml
 * 
 */
public class ProductCategory extends BaseEntity {

	private static final long serialVersionUID = -3712533166237860098L;
	
	public static final String PATH_SPLIT = "/";
	
	private String name;
	private Long parentId;
	private Integer level;
	private Integer sort;
	private Integer isDeleted;
	private String fullIdPath;
	private String desc;
	private Integer isBottom; //是否最底层
	private Long projectId;//项目ID  默认项目为0
	private String image; //分类图片
	
	List<ProductCategory> child;
	
	public static Integer ISBOTTOM_YES = 1;//是
	public static Integer ISBOTTOM_NO= 2;//否
	
	
	public Integer getIsBottom() {
		return isBottom;
	}
	public void setIsBottom(Integer isBottom) {
		this.isBottom = isBottom;
	}
	public Long get_parentId(){
		return this.parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public List<ProductCategory> getChild() {
		return child;
	}
	public void setChild(List<ProductCategory> child) {
		this.child = child;
	}
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getFullIdPath() {
		return fullIdPath;
	}
	public void setFullIdPath(String fullIdPath) {
		this.fullIdPath = fullIdPath;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	
	
	
}
