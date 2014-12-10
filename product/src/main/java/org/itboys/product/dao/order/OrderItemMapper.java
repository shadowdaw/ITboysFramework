package org.itboys.product.dao.order;

import java.util.List;
import java.util.Map;

import org.itboys.product.entity.mysql.OrderItem;


/**
 * 订单明细表相关DAO操作
 */
public interface OrderItemMapper {

	/**
	 * 批量插入订单行
	 * 
	 * @param orderItem
	 */
	public void batchInsert(List<OrderItem> list);

	/**
	 * 根据订单ID加载订单行信息
	 * 
	 * @param orderId
	 * @return
	 */
	public List<OrderItem> getItems(Long orderId);

	/**
	 * 根据ID加载对象
	 * 
	 * @param id
	 * @return
	 */
	public OrderItem getById(Long id);

	public int update(OrderItem orderItem);

	public int updateCommentStatus(List<Long> id);

	public List<OrderItem> getItemsByOrderIds(List<Long> orderIds);

	public List<OrderItem> getItemsByMemberId(Map<String, Object> sqlMap);

	public long getItemsByMemberIdCount(Map<String, Object> sqlMap);

	/**
	 * 根据brandids查找订单项
	 * 
	 * @param list
	 * @return
	 */
	public List<OrderItem> findByBrandIdss(List<Long> list);

	/**
	 * 根据订单删除订单详细 status为-1是删除状态
	 */
	public int deleteByOrder(Long orderId);

	public List<OrderItem> getItemsByOrderNo(String orderNo);
	
	//某个时间段内购买的商品，根据购买数量倒序
	public List<OrderItem> getOrderByProCount(Map<String, Object> sqlMap);
}
