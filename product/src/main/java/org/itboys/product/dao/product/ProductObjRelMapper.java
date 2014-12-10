package org.itboys.product.dao.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.itboys.product.entity.mysql.product.ProductObjRel;

/**
 * product关联对象接口DAO
 * @author huml
 *
 */
public interface ProductObjRelMapper {

	public List<ProductObjRel> findByProductId(Long productId);
	
	public int delete(Long productId);
	
	public int dltByProType(@Param("productId") Long productId,@Param("type") Integer type);
	
	public void batchInsert(List<ProductObjRel> list);
	
	public List<ProductObjRel> findByProType(@Param("productId") Long productId,@Param("type") Integer type);
	
	public Long isExists(@Param("productId") Long productId,@Param("type") Integer type,@Param("objId")Long objId);
}
