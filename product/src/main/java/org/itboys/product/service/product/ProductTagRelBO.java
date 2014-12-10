package org.itboys.product.service.product;

import java.util.List;
import java.util.Map;

import org.itboys.product.dao.product.ProductTagRelMapper;
import org.itboys.product.entity.mysql.product.ProductTagRel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * 商品标签关联BO
 * @author huml
 *
 */
@Service
public class ProductTagRelBO {
	
	@Autowired
	private ProductTagRelMapper productTagRelMapper;
	
	public void insert(ProductTagRel entity){
		productTagRelMapper.insert(entity);
	}
	
	public void delete(Long productId){
		productTagRelMapper.delete(productId);
	}
	
	
	public void batchInsert(List<ProductTagRel>list){
		productTagRelMapper.batchInsert(list);
	}
	
	public List<ProductTagRel> list(Map<String, Object> sqlMap){
		return productTagRelMapper.list(sqlMap);
	}
	
	public List<Long> getTagIds(List<ProductTagRel> list){
		return Lists.transform(list, new Function<ProductTagRel, Long>() {
			@Override
			public Long apply(ProductTagRel tagrel) {
				return tagrel.getProTagId();
			}
		});
	}
	
	public List<Long> getProIds(List<ProductTagRel> list){
		return Lists.transform(list, new Function<ProductTagRel, Long>() {
			@Override
			public Long apply(ProductTagRel tagrel) {
				return tagrel.getProductId();
			}
		});
	}
	
	public List<Long> selectProIds(Map<String,Object> sqlMap){
		return productTagRelMapper.selectProIds(sqlMap);
	}
}
