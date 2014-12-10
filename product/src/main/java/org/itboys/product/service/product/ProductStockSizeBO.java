package org.itboys.product.service.product;

import java.util.List;
import java.util.Map;

import org.itboys.product.dao.product.ProductStockSizeMapper;
import org.itboys.product.entity.mysql.product.ProductStockSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品库存表：
 * 
 * @author huml
 *
 */
@Service
public class ProductStockSizeBO {

	@Autowired
	private ProductStockSizeMapper productStockSizeBOMapper;

	/**
	 * 批量插入   后面还有批量删除，单个库存查询，购买成功之后商品数量减一等等
	 * @param list
	 */
	
	public void batchInsert(List<ProductStockSize> list){
		productStockSizeBOMapper.batchInsert(list);
	}
	
	public ProductStockSize stockSize(Map<String, Object> map){
		return productStockSizeBOMapper.stockSize(map);
	}
	
	public List<ProductStockSize>getListByProductid(Long productId){
		return productStockSizeBOMapper.getListByProductid(productId);
	}
	
	public void delByProductId(Long productId){
		productStockSizeBOMapper.delByProductId(productId);
	}
	
	/**
	 * 修改
	 * 
	 * @param keyword
	 * @return 
	 * @return
	 */
	public void update(ProductStockSize entity) {
		productStockSizeBOMapper.update(entity);
	}
	/**
	 * 修改库存
	 * @param colorId 颜色id
	 * @param sizeId	尺码id
	 * @param productId	商品id
	 * @param buyNum	购买数量
	 * @return
	 */
	public int updateStockSize(Long colorId,Long sizeId,Long productId,Long buyNum){
		return productStockSizeBOMapper.updateStockSize(colorId,sizeId,productId,buyNum);
	}
	/**
	 * 退货加库存
	 * @param colorId
	 * @param sizeId
	 * @param productId
	 * @param buyNum
	 * @return
	 * 
	 */
	public int updatePlusStockSize(Long colorId,Long sizeId,Long productId,Long buyNum){
		return productStockSizeBOMapper.updatePlusStockSize(colorId,sizeId,productId,buyNum);
	}
	
}