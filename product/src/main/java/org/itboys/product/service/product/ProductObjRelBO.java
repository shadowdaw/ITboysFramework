package org.itboys.product.service.product;

import java.util.List;

import org.itboys.product.dao.product.ProductObjRelMapper;
import org.itboys.product.entity.mysql.product.ProductObjRel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * Product关联对象
 * @author huml
 */
@Service
public class ProductObjRelBO {

	@Autowired
	private ProductObjRelMapper productObjRelMapper;
	
	public void batchInsert(List<ProductObjRel> list){
		productObjRelMapper.batchInsert(list);
	}
	
	public int delete(Long productId){
		return productObjRelMapper.delete(productId);
	}
	
	public int dltByProType(Long productId,Integer type){
		return productObjRelMapper.dltByProType(productId,type);
	}
	
	
	/**
	 * 获取product所关联的所有对象id
	 * @param productId
	 * @return
	 */
	public List<Long> getObjIdsByProductId(Long productId){
		List<ProductObjRel> productObjRels =  productObjRelMapper.findByProductId(productId);
		List<Long> objIds = Lists.transform(productObjRels, new Function<ProductObjRel, Long>() {
			@Override
			public Long apply(ProductObjRel input) {
				return input.getObjId();
			}
		});
		return  objIds;
	}
	
	/**
	 * 获取product所关联类型的所有对象id
	 * @param productId
	 * @return
	 */
	public List<Long> getObjIdsByProductId(Long productId,Integer type){
		List<ProductObjRel> productObjRels =  productObjRelMapper.findByProType(productId,type);
		List<Long> objIds = Lists.transform(productObjRels, new Function<ProductObjRel, Long>() {
			@Override
			public Long apply(ProductObjRel input) {
				return input.getObjId();
			}
		});
		return  objIds;
	}
	/**
	 * 判断是否哦已经关联
	 * @param productId
	 * @param type
	 * @param objId
	 * @return
	 */
	public boolean isExists(Long productId,Integer type,Long objId){
		Long count = productObjRelMapper.isExists(productId, type, objId);
		if(count > 0){
			return false;
		}else {
			return true;
		}
	}
	 
}
