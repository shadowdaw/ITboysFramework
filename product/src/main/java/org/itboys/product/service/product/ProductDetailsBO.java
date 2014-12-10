package org.itboys.product.service.product;

import java.util.List;
import java.util.Map;

import org.itboys.product.dao.product.ProductDetailsMapper;
import org.itboys.product.entity.mysql.product.ProductDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailsBO {

	@Autowired
	private ProductDetailsMapper productDetailsMapper;
	
	public List<ProductDetails> getListByProductid(Long productId){
		return productDetailsMapper.getListByProductid(productId);
	}
	
	/**
	 * 批量插入
	 * @param images
	 */
	public void batchInsert(List<ProductDetails> list){
		productDetailsMapper.batchInsert(list);
	}
	
	public void insert(ProductDetails entity){
		productDetailsMapper.insert(entity);
	}
	
	/**
	 * 删除
	 */
	public int deleteByProductid(Long productId){
		return productDetailsMapper.deleteByProductId(productId);
	}
	
	public List<ProductDetails> getAll(){
		return productDetailsMapper.getAll();
	}
	
	public List<ProductDetails>catList(List<Long>list){
		return productDetailsMapper.catList(list);
	}
	
	public ProductDetails getProductDetailByid(Long id){
		return productDetailsMapper.getById(id);
	}

	
	public int update(ProductDetails entity){
		return productDetailsMapper.update(entity);
	}

	
	public List<ProductDetails> getList(Map<String, Object> sqlMap){
		return productDetailsMapper.getList(sqlMap);
	}
}
