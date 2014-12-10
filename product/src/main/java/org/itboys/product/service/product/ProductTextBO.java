package org.itboys.product.service.product;

import org.itboys.product.dao.product.ProductTextMapper;
import org.itboys.product.entity.mysql.product.ProductText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品大字段
 * 
 * @author huml
 * 
 */
@Service
public class ProductTextBO {

	@Autowired
	private ProductTextMapper productTextMapper;

	/**
	 * 根据productId查询
	 * 
	 * @param productId
	 */
	public ProductText findByProductId(Long productId) {
		return productTextMapper.findByProductId(productId);
	}

	/**
	 * 插入操作
	 * 
	 * @param entity
	 */
	public void insert(ProductText entity) {
		productTextMapper.insert(entity);
	}

	/**
	 * 修改
	 * 
	 * @param productId
	 * @return
	 */
	public int update(ProductText entity) {
		ProductText text = productTextMapper.findByProductId(entity.getProductId());
		if (text != null) {
			return productTextMapper.update(entity);
		}
		productTextMapper.insert(entity);
		return 1;
	}

	/**
	 * 删除
	 */
	public int deleteByProductId(Long productId) {
		return productTextMapper.deleteByProductId(productId);
	}
}
