package org.itboys.product.service.product;

import java.util.List;

import org.itboys.product.dao.product.ProductCategoryBrandMapper;
import org.itboys.product.dto.product.ProductBrandDTO;
import org.itboys.product.entity.mysql.product.ProductCategoryBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 品牌分类关联表
 * @author huml
 *
 */
@Service
public class ProductCategoryBrandBO {
	
	@Autowired
	private ProductCategoryBrandMapper productCategoryBrandMapper;
	
	public List<ProductBrandDTO> findByCatId(Long catId){
		return productCategoryBrandMapper.findByCatId(catId);
	}
	
	public ProductCategoryBrand findByCatIdAndBrandId(Long catId,Long brandId){
		return productCategoryBrandMapper.findByCatIdAndBrandId(catId,brandId);
	}
	
	public int delete(Long id){
		return productCategoryBrandMapper.delete(id);
	}
	
	public int deleteByCatId(Long catId){
		return productCategoryBrandMapper.deleteByCatId(catId);
	}
	
	public int deleteByBrandId(Long brandId){
		return productCategoryBrandMapper.deleteByBrandId(brandId);
	}
	
	public void batchInsert(List<ProductCategoryBrand> list){
		productCategoryBrandMapper.batchInsert(list);
	}

	public List<Long> getCateIdByBrandId(Long brandId){
		return productCategoryBrandMapper.getCateIdByBrandId(brandId);
	}
}
