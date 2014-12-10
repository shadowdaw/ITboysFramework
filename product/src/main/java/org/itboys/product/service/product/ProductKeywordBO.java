package org.itboys.product.service.product;

import java.util.List;

import org.itboys.product.dao.product.ProductKeywordMapper;
import org.itboys.product.entity.mysql.product.ProductKeyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品关键字
 * @author huml
 *
 */
@Service
public class ProductKeywordBO {

	@Autowired
	private ProductKeywordMapper productKeywordMapper;
	
	/**
	 * 根据productId查询
	 * @param productId
	 */
	public List<ProductKeyword> findByProductId(Long productId){
		return productKeywordMapper.findByProductId(productId);
	}
	
	/**
	 * 批量插入商品图片
	 * @param images
	 */
	public void batchInsert(List<ProductKeyword> list){
		productKeywordMapper.batchInsert(list);
	}
	
	
	/**
	 * 删除
	 */
	public int deleteByProductId(Long productId){
		return productKeywordMapper.deleteByProductId(productId);
	}
}
