package org.itboys.product.dao.product;

import java.util.List;

import org.itboys.product.entity.mysql.product.ProductExt;

/**
 * 商品字段扩展表 纵表
 * @author huml
 *
 */
public interface ProductExtMapper {

	public List<ProductExt> list(Long productId);
	
	public void insert(List<ProductExt> exts);

	public int delete(Long productId);
	
}
