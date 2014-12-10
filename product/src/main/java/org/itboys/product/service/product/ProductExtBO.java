package org.itboys.product.service.product;

import java.util.List;
import java.util.Map;

import org.itboys.product.dao.product.ProductExtMapper;
import org.itboys.product.entity.mysql.product.ProductExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

/**
 * 
 * @author huml
 *
 */
@Service
public class ProductExtBO {
	
	@Autowired
	private ProductExtMapper productExtMapper;
	
	/**
	 * 得到商品扩展信息的map
	 * @param memberId
	 * @return
	 */
	public Map<String,String> getMemberExtMap(Long productId){
		List<ProductExt> list = list(productId);
		Map<String,String> map = Maps.newHashMapWithExpectedSize(list.size());
		for(ProductExt ext:list){
			map.put(ext.getKey(), ext.getValue());
		}
		return map;
	}
	
	public List<ProductExt> list(Long productId){
		return productExtMapper.list(productId);
	}
	
	public void insert(List<ProductExt> exts){
		 productExtMapper.insert(exts);
	}

	public int delete(Long productId){
		return productExtMapper.delete(productId);
	}
}
