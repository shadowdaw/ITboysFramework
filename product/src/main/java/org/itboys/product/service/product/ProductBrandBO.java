package org.itboys.product.service.product;

import java.util.List;
import java.util.Map;

import org.itboys.framework.query.Page;
import org.itboys.framework.query.PageQuery;
import org.itboys.framework.query.PageQueryUtils;
import org.itboys.product.dao.product.ProductBrandMapper;
import org.itboys.product.entity.mysql.product.ProductBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

/**
 * 产品品牌
 * @author huml
 */
@Service
public class ProductBrandBO {

	@Autowired
	private ProductBrandMapper productBrandMapper;
	@Autowired
	private ProductCategoryBrandBO productCategoryBrandBO;

	public List<ProductBrand> getListByCatid(Long catId){
		Map<String, Object> map = Maps.newHashMapWithExpectedSize(1);
		map.put("catId", catId);
		return list(map);
	}
	
	public List<ProductBrand> geRecommendBrand(int limitSize){
		Map<String, Object> map = Maps.newHashMapWithExpectedSize(3);
		map.put("isRecommend",ProductBrand.ISRECOMMEND_YES);
		map.put("rowStart", 0);
		map.put("pageSize", limitSize);
		return list(map);
	}
	
	public List<ProductBrand>getBySellerIdPcId(Map<String, Object> map){
		return productBrandMapper.getBySellerIdPcId(map);
	}
	
	public List<ProductBrand> list(Map<String, Object> map){
		return productBrandMapper.list(map);
	}
	
	public List<ProductBrand>allList(Map<String, Object> map){
		return productBrandMapper.allList(map);
	}
	
	public Page<ProductBrand> pageQuery(Page<ProductBrand> page, final Map<String, Object> sqlMap){
		return PageQueryUtils.pageQuery(page, sqlMap, new PageQuery<ProductBrand>() {
			@Override
			public long count() {
				return productBrandMapper.count(sqlMap);
			}
			@Override
			public List<ProductBrand> list() {
				return productBrandMapper.list(sqlMap);
			}
		});
	}
	
	public ProductBrand findById(Long id){
		return productBrandMapper.findById(id);
	}
	/**
	 * 执行批量插入,插入,操作不同的表,注意数据同步
	 * @param entity
	 * @param productCatIds
	 */
	public void insert(ProductBrand entity){
		productBrandMapper.insert(entity);
	}
	
	/**
	 * 同时执行删除,批量插入,更新,注意数据同步
	 * @param entity
	 * @param productCatIdArr
	 * @return
	 */
	public int update(ProductBrand entity){
		return productBrandMapper.update(entity);
	}
   
	public int delete(Long id){
		productCategoryBrandBO.deleteByBrandId(id);
		return productBrandMapper.delete(id);
	}
	
	public void deleteBySellerId(Long sellerId){
		productBrandMapper.deleteBySellerId(sellerId);
	}
	
	public long count(Map<String, Object> sqlMap){
		return productBrandMapper.count(sqlMap);
	}
}
