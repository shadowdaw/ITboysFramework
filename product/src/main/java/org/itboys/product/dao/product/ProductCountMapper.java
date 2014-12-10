package org.itboys.product.dao.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.itboys.product.entity.mysql.product.ProductCount;

/**
 * 商品计数器表
 * @author huml
 *
 */
public interface ProductCountMapper {

	
	/**
	 * 根据productId查询
	 * @param productId
	 */
	public ProductCount findByProductId(Long productId);
	
	public int update(ProductCount entity);
	
	/**
	 * 插入操作
	 * @param entity
	 */
	public void insert(ProductCount entity);
	
	/**
	 * 添加购买数
	 * @param productId
	  * @param buyedCount 购买数量
	 * @return
	 */
	public int addBuyedCount(@Param("productId")Long productId,@Param("buyedCount")Integer buyedCount);
	
	public int upGoodCommentCount(@Param("productId")Long productId,@Param("goodCommentCount")Integer goodCommentCount);
	
	public int upBadCommentCount(@Param("productId")Long productId,@Param("badCommentCount")Integer badCommentCount);

	public int upMiddleCommentCount(@Param("productId")Long productId,@Param("middleCommentCount")Integer middleCommentCount);

	/**
	 * 添加评论数量
	 * @param productId
	 * @return
	 */
	public int addCommentCount(Long productId);
	
	/**
	 * 添加浏览数
	 * @param productId
	 * @return
	 */
	public int addViewCount(Long productId);
	
	/**
	 * 添加收藏数
	 * @param productId
	 * @return
	 */
	public int addCollectCount(Long productId);
	
	/**
	 * 添加好评数
	 * @param productId
	 * @return
	 */
	public int addGoodCommentCount(Long productId);
	
	/**
	 * 添加中评数
	 * @param productId
	 * @return
	 */
	public int addMiddleCommentCount(Long productId);
	
	/**
	 * 添加差评数
	 * @param productId
	 * @return
	 */
	public int addBadCommentCount(Long productId);
	
	/**
	 * 修改评论次数
	 * @param productId
	 * @param commentCount
	 * @return
	 */
	public int updateCommentCount(@Param("productId") Long productId,@Param("commentCount") Integer commentCount);
	
	
	/**
	 * 删除
	 */
	public int deleteByProductId(Long productId);
	
	/**
	 * 更新商品数目
	 * @param productId
	 * @param num
	 * @return
	 */
	public int  downProductStore(@Param("productId")Long productId,@Param("num")Integer num);
	/**
	 * 获取秒杀商品的购买次数等信息
	 * @return
	 */
	public List<ProductCount>getSecKissProductList();
	
	public List<ProductCount>getBuyedList();
	
	public List<ProductCount>getAll();
	
	public List<ProductCount>getBuyedListByids(List<Long>list);
	
	public List<ProductCount> getAllOrderByBuyed();
}