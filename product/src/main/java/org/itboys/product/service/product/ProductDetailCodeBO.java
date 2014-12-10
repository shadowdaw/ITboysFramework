package org.itboys.product.service.product;

import java.util.List;

import org.itboys.product.dao.product.ProductDetailCodeMapper;
import org.itboys.product.entity.mysql.product.ProductDetailCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品特征值的配置code 就是商品有哪些主特征 比如 颜色 尺码 品种 等
 * @author huml
 *
 */
@	Service
public class ProductDetailCodeBO {
	
	@Autowired
	private ProductDetailCodeMapper productDetailCodeMapper;
	
	
	public List<ProductDetailCode> getListByProductid(Long productId){
		return productDetailCodeMapper.getListByProductid(productId);
	}
	
	/**
	 * 批量插入
	 * @param images
	 */
	public void batchInsert(List<ProductDetailCode> list){
		productDetailCodeMapper.batchInsert(list);
	}
	
	/**
	 * 删除
	 */
	public int deleteByProductid(Long productId){
		return productDetailCodeMapper.deleteByProductId(productId);
	}
	
	public void insert(ProductDetailCode entity){
		productDetailCodeMapper.insert(entity);
	}
}