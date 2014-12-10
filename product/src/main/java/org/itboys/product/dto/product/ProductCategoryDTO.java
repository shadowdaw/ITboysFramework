package org.itboys.product.dto.product;
/**
 * huml
 */
public class ProductCategoryDTO {
	
	private Long id;
	private Long fullIdPath;
	private String name;
	private Long brandId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public Long getFullIdPath() {
		return fullIdPath;
	}
	public void setFullIdPath(Long fullIdPath) {
		this.fullIdPath = fullIdPath;
	}
}