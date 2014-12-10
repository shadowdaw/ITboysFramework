package org.itboys.product.dao.product;

import java.util.List;
import java.util.Map;

import org.itboys.product.entity.mysql.product.ProductComment;

/**
 * 商品评论
 * 
 * @author WangRan
 * 
 */
public interface ProductCommentMapper {
	/**
	 * 批量插入商品评论
	 */
	public void batchInsert(List<ProductComment> list);

	public List<ProductComment> list(Map<String, Object> sqlMap);

	public Integer count(Map<String, Object> sqlMap);

	public int delete(Long id);

	public int insert(ProductComment entity);

	public ProductComment findById(Long id);

	public int update(ProductComment entity);

	public long countForScore(Map<String, Object> sqlMap);
	
	public List<ProductComment> list2(Map<String, Object> sqlMap);

	public Integer count2(Map<String, Object> sqlMap);
	
	public Long avgScore(Long productId);

}
