package org.itboys.product.dao.product;

import java.util.List;
import java.util.Map;

import org.itboys.product.entity.mysql.product.ProductCommentReplay;

public interface ProductCommentReplayMapper {
	public List<ProductCommentReplay> list(Map<String, Object> param);
	public Long count(Map<String, Object> param);
	
	public void insert(ProductCommentReplay productCommentReplay);
	public void update(ProductCommentReplay productCommentReplay);
	public void delete(Long id);
	
	public List<ProductCommentReplay> findByCommentId(Long commentId);
	public List<ProductCommentReplay> findByParentId(Long parentId);
	
	public ProductCommentReplay findById(Long id);
}
