package org.itboys.product.service.product;

import java.util.List;
import java.util.Map;

import org.itboys.framework.query.Page;
import org.itboys.framework.query.PageQuery;
import org.itboys.framework.query.PageQueryUtils;
import org.itboys.product.dao.product.ProductImagesMapper;
import org.itboys.product.entity.mysql.product.ProductImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

/**
 * 商品图片管理
 * @author huml
 *
 */
@Service
public class ProductImagesBO {

	@Autowired
	private ProductImagesMapper productImagesMapper;
	
	/**
	 * 查询前几条产品关联图片信息
	 * 根据productId查询
	 * @param productId
	 */
	public List<ProductImages> findByProductId(Long productId,int rowStart,int pageSize){
		Map<String, Object> map = Maps.newHashMapWithExpectedSize(3);
		map.put("productId", productId);
		map.put("rowStart",rowStart);
		map.put("pageSize", pageSize);
		return productImagesMapper.findByProductId(map);
	}
	
	/**
	 * 查询前几条产品关联图片信息
	 * 根据productId查询
	 * @param productId
	 */
	public List<ProductImages> findByProductId(Long productId){
		Map<String, Object> map = Maps.newHashMapWithExpectedSize(1);
		map.put("productId", productId);
		return productImagesMapper.findByProductId(map);
	}
	
	/**
	 * 批量插入商品图片
	 * @param images
	 */
	public void batchInsert(List<ProductImages> list){
		productImagesMapper.batchInsert(list);
	}
	
	
	/**
	 * 删除 
	 */
	public int deleteByProductId(Long productId){
		return productImagesMapper.deleteByProductId(productId);
	}
	
	/**
	 * 根据图片id删除
	 */
	public int delete(Long id){
		return productImagesMapper.delete(id);
	}
	
	/**
	 * 查询图片list
	 */
	public List<ProductImages> list(Map<String, Object> sqlMap){
		return productImagesMapper.list(sqlMap);
	}
	
	
	public Page<ProductImages> pageQuery(Page<ProductImages> page, final Map<String, Object> sqlMap){
		return PageQueryUtils.pageQuery(page, sqlMap, new PageQuery<ProductImages>() {
			@Override
			public List<ProductImages> list(){
				List<ProductImages> list = productImagesMapper.list(sqlMap);
				return list;
			}
			@Override
			public long count(){
				return productImagesMapper.count(sqlMap);
			}
		});
	}
}
