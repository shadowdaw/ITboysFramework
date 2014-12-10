package org.itboys.product.dao.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.itboys.product.dto.product.ProductBrandDTO;
import org.itboys.product.entity.mysql.product.ProductCategoryBrand;

/**
 * 商品品牌产品关联
 * @author huml
 *
 */
public interface ProductCategoryBrandMapper {

	
	public ProductCategoryBrand findByCatIdAndBrandId(@Param("catId")Long catId,@Param("brandId")Long brandId);
		
	public List<ProductBrandDTO> findByCatId(Long catId);
	
	public int delete(Long id);
	
	public int deleteByCatId(Long catId);
	
	public int deleteByBrandId(Long brandId);
	
	public void batchInsert(List<ProductCategoryBrand> list);
	
	public List<Long> getCateIdByBrandId(Long brandId);
	
}
