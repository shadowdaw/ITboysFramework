package org.itboys.product.service.product;

import java.util.List;
import java.util.Map;

import org.itboys.product.dao.product.ProductParamMapper;
import org.itboys.product.entity.mysql.product.ProductParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

/**
 * 商品参数值 比如颜色 尺码等详情
 * @author huml
 *
 */
@	Service
public class ProductParamBO {
	
	@Autowired
	private ProductParamMapper productParamMapper;
	
	
	public List<ProductParam> getListByProductId(Long productId){
		return productParamMapper.getListByProductId(productId);
	}
	
	public Map<Long, String> getParamMap(Long productId){
		List<ProductParam> list = productParamMapper.getListByProductId(productId);
		Map<Long, String> paramMap = Maps.newHashMapWithExpectedSize(list.size());
		for(ProductParam pp:list){
			paramMap.put(pp.getParamConfigId(), pp.getValue());
		}
		return paramMap;
	}
	
	/**
	 * 批量插入
	 * @param images
	 */
	public void batchInsert(List<ProductParam> list){
		productParamMapper.batchInsert(list);
	}
	
	/**
	 * 删除
	 */
	public int deleteByProductid(Long productId){
		return productParamMapper.deleteByProductId(productId);
	}
	
	/**
	 * 删除某个商品的某个参数值
	 * @param productId
	 * @param paramConfigId
	 * @return
	 */
	public int deleteByPidAndConfigId(Long productId,Long paramConfigId){
		return productParamMapper.deleteByPidAndConfigId(productId,paramConfigId);
	}
}
