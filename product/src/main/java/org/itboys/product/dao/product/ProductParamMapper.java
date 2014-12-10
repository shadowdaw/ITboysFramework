package org.itboys.product.dao.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.itboys.product.entity.mysql.product.ProductParam;

/**
 * 商品参数值 比如颜色 尺码等详情
 * @author huml
 *
 */
public interface ProductParamMapper {

	
	public List<ProductParam> getListByProductId(Long productId);
	
	/**
	 * 批量插入
	 * @param images
	 */
	public void batchInsert(List<ProductParam> list);
	
	/**
	 * 删除
	 */
	public int deleteByProductId(Long productId);
	
	public int deleteByPidAndConfigId(@Param("productId") Long productId,@Param("paramConfigId") Long paramConfigId);
	
}
