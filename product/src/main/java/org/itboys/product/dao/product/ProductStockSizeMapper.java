package org.itboys.product.dao.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.itboys.product.entity.mysql.product.ProductStockSize;

public interface ProductStockSizeMapper {

	public void batchInsert(List<ProductStockSize> list);
	
	public ProductStockSize stockSize(Map<String, Object> map);
	
	public List<ProductStockSize>getListByProductid(Long productId);
	
	public void delByProductId(Long productId);
	
	public void update(ProductStockSize entity);  
	
	public int updateStockSize(@Param("colorId")Long colorId,@Param("sizeId")Long sizeId,@Param("productId")Long productId,@Param("buyNum")Long buyNum);
	//退货产品加库存
	public int updatePlusStockSize(@Param("colorId")Long colorId,@Param("sizeId")Long sizeId,@Param("productId")Long productId,@Param("buyNum")Long buyNum);
	
}