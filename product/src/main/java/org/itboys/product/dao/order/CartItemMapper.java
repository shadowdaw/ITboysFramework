package org.itboys.product.dao.order;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.itboys.product.entity.mysql.CartItem;

/**
 * 新购物车项 
 * @author huml
 *
 */
public interface CartItemMapper {


	/**
	 * 插入购物车项
	 * @param item
	 */
	public void insert(CartItem item);
	
	/**
	 * 编辑购物车项
	 * @param item
	 */
	public int update(CartItem item);

	/**
	 * 批量插入购物车项
	 * @param list
	 */
	public void batchInsert(List<CartItem> list);
	
	/**
	 * 根据会员ID和商品ID加载购物车项
	 * @param memberId
	 * @param productId
	 * @return
	 */
	public CartItem getCarItemByMemberId(@Param("memberId") Long memberId,@Param("productId") Long productId,@Param("guigeIds") String guigeIds);
	
	public CartItem getCarItemByMemberIdAndType(@Param("memberId") Long memberId,@Param("productId") Long productId,@Param("guigeIds") String guigeIds,@Param("type") Integer type,@Param("objId") Long objId);
	
	public void cookieToSession(@Param("memberId") Long memberId,@Param("cookie") String cookie);
	
	/**
	 * 根据购物车cookie key和商品ID加载购物车项
	 * @param cookie
	 * @param productId
	 * @return
	 */
	public CartItem getCarItemByCookie(@Param("cookie") String cookie,@Param("productId") Long productId,@Param("guigeIds") String guigeIds);
	
	public CartItem getCarItemByCookieAndType(@Param("cookie") String cookie,@Param("productId") Long productId,@Param("guigeIds") String guigeIds,@Param("type") Integer type,@Param("objId") Long objId);

	
	/**
	 * 根据会员ID加载购物车实体
	 * @param memberId
	 * @return
	 */
	public List<CartItem> getCarItemsByMemberId(Long memberId);
	
	/**
	 * 根据购物车cookie获取购物车项
	 * @param cookie
	 * @return
	 */
	public List<CartItem> getCarItemsByCookie(String cookie);
	
	/**
	 * 删除购物车项
	 * @param memberId
	 * @param productId
	 * @param guigeIds
	 * @return
	 */
	public int deleteCarItemByMemberId(@Param("memberId") Long memberId,@Param("productId") Long productId,@Param("guigeIds") String guigeIds);
	
	public int deleteCarItemByMemberIdAndType(@Param("memberId") Long memberId,@Param("productId") Long productId,@Param("guigeIds") String guigeIds,@Param("type") Integer type,@Param("objId") Long objId);
	
	public int deleteCarItemByMemberIdAndTypePrice(@Param("memberId") Long memberId,@Param("productId") Long productId,@Param("guigeIds") String guigeIds,@Param("type") Integer type,@Param("objId") Long objId,@Param("price") BigDecimal price);

	/**
	 * cookie key 和商品ID删除购物车项
	 * @param memberId
	 * @param productId
	 */
	public int deleteCarItemByCookie(@Param("cookie") String cookie,@Param("productId") Long productId,@Param("guigeIds") String guigeIds);
	
	
	public int deleteCarItemByCookieAndType(@Param("cookie") String cookie,@Param("productId") Long productId,@Param("guigeIds") String guigeIds,@Param("type") Integer type,@Param("objId") Long objId);

	/**
	 * cookie key ,商品ID,相同价格 删除购物车项
	 * @param cookie
	 * @param productId
	 * @param guigeIds
	 * @param type
	 * @param objId
	 * @return
	 */
	public int deleteCarItemByCookieAndTypePrice(@Param("cookie") String cookie,@Param("productId") Long productId,@Param("guigeIds") String guigeIds,@Param("type") Integer type,@Param("objId") Long objId,@Param("price") BigDecimal price);

	/**
	 * 根据会员Id删除所有购物车项
	 * @param memberId
	 */
	public int deleteCarItemsByMemberId(Long memberId);
	
	/**
	 * 根据COOKIE key删除购物车
	 * @param memberId
	 */
	public int deleteCarItemsByCookie(String cookie);
	
	/**
	 * 用户登入后 cookie转memberId
	 * @param cookie
	 * @return
	 */
	public int cookieToMember(@Param("cookie") String cookie,@Param("memberId") Long memberid);
	
	/**
	 * 删除购物车中失效的宝贝，就type为某个状态是是失效的
	 * @param type
	 * @return
	 */
	public int deleteByType(Integer type);
	
	/**
	 * 得到某种状态下的购物车项
	 * @param type
	 * @return
	 */
	public List<CartItem> getCarItemsByType(Integer type);
	
}
