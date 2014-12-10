package org.itboys.product.service.product;

import java.util.List;

import org.itboys.product.dao.product.ProductCountMapper;
import org.itboys.product.entity.mysql.product.ProductCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品计数器
 * @author huml
 *
 */
@Service
public class ProductCountBO {

	@Autowired
	private ProductCountMapper productCountMapper;
	
	/**
	 * 根据productId查询
	 * @param productId
	 */
	public ProductCount findByProductId(Long productId){
		return productCountMapper.findByProductId(productId);
	}
	
	public int update(ProductCount entity){
		return productCountMapper.update(entity);
	}
	
	/**
	 * 插入操作
	 * @param entity
	 */
	public void insert(ProductCount entity){
		productCountMapper.insert(entity);
	}
	
	/**
	 * 添加购买数
	 * @param productId
	  * @param buyedCount 购买数量
	 * @return
	 */
	public int addBuyedCount(Long productId,Integer buyedCount){
		return productCountMapper.addBuyedCount(productId,buyedCount);
	}
	
	/**
	 * 添加评论数量
	 * @param productId
	 * @return
	 */
	public int addCommentCount(Long productId){
		return productCountMapper.addCommentCount(productId);
	}
	
	/**
	 * 修改评论次数
	 * @param productId
	 * @param commentCount
	 * @return
	 */
	public int updateCommentCount(Long productId,Integer commentCount){
		return productCountMapper.updateCommentCount(productId, commentCount);
	}
	
	/**
	 * 添加浏览数
	 * @param productId
	 * @return
	 */
	public int addViewCount(Long productId){
		return productCountMapper.addViewCount(productId);
	}
	
	/**
	 * 添加收藏数
	 * @param productId
	 * @return
	 */
	public int addCollectCount(Long productId){
		return productCountMapper.addCollectCount(productId);
	}
	
	/**
	 * 添加好评数
	 * @param productId
	 * @return
	 */
	public int addGoodCommentCount(Long productId){
		return productCountMapper.addGoodCommentCount(productId);
	}
	
	/**
	 * 添加中评数
	 * @param productId
	 * @return
	 */
	public int addMiddleCommentCount(Long productId){
		return productCountMapper.addMiddleCommentCount(productId);
	}
	
	/**
	 * 添加差评数
	 * @param productId
	 * @return
	 */
	public int addBadCommentCount(Long productId){
		return productCountMapper.addBadCommentCount(productId);
	}
	
	/**
	 * 删除  根据productId
	 * @param productId
	 * @return
	 */
	public int deleteByProductId(Long productId){
		return productCountMapper.deleteByProductId(productId);
	}
	/**
	 * 商品库存数量改变
	 */
	public int downProductStore(Long productId,Integer num){
		return productCountMapper.downProductStore(productId, num);
	}
	
	public List<ProductCount>getSecKissProductList(){
		return productCountMapper.getSecKissProductList();
	}
	
	public List<ProductCount>getBuyedList(){
		return productCountMapper.getBuyedList();
	}
	
	public List<ProductCount>getAll(){
		return productCountMapper.getAll();
	}
	
	public List<ProductCount>getBuyedListByids(List<Long>list){
		return productCountMapper.getBuyedListByids(list);
	}

	public int upGoodCommentCount(Long productId,Integer commentCount){
		return productCountMapper.upGoodCommentCount(productId,commentCount);
	}
	
	public int upMiddleCommentCount(Long productId,Integer commentCount){
		return productCountMapper.upMiddleCommentCount(productId,commentCount);
	}
	
	public int upBadCommentCount(Long productId,Integer commentCount){
		return productCountMapper.upBadCommentCount(productId,commentCount);
	}
	
	public List<ProductCount> getAllOrderByBuyed(){
		return productCountMapper.getAllOrderByBuyed();
	}
	
}
