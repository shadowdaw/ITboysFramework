package org.itboys.product.service.product;

import java.util.List;
import java.util.Map;

import org.itboys.product.dao.product.ProductDetailMapper;
import org.itboys.product.entity.mysql.product.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品特征值 比如颜色 尺码等详情
 * @author huml
 *
 */
@	Service
public class ProductDetailBO {
	
	@Autowired
	private ProductDetailMapper productDetailMapper;
	
	
	public List<ProductDetail> getListByProductid(Long productId){
		return productDetailMapper.getListByProductid(productId);
	}
	
	/**
	 * 批量插入
	 * @param images
	 */
	public void batchInsert(List<ProductDetail> list){
		productDetailMapper.batchInsert(list);
	}
	
	public void insert(ProductDetail entity){
		productDetailMapper.insert(entity);
	}
	
	/**
	 * 删除
	 */
	public int deleteByProductid(Long productId){
		return productDetailMapper.deleteByProductId(productId);
	}
	
	public List<ProductDetail> getAll(){
		return productDetailMapper.getAll();
	}
	
	public List<ProductDetail>catList(List<Long>list){
		return productDetailMapper.catList(list);
	}
	
	public ProductDetail getProductDetailByid(Long id){
		return productDetailMapper.getById(id);
	}

	
	public int update(ProductDetail entity){
		return productDetailMapper.update(entity);
	}

	
	public List<ProductDetail> getList(Map<String, Object> sqlMap){
		return productDetailMapper.getList(sqlMap);
	}

}
