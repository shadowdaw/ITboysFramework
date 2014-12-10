package org.itboys.product.service.order;

import java.util.List;
import java.util.Map;

import org.itboys.framework.query.Page;
import org.itboys.framework.query.PageQuery;
import org.itboys.framework.query.PageQueryUtils;
import org.itboys.product.dao.order.OrderItemMapper;
import org.itboys.product.entity.mysql.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单服务层
 *
 */
@Service
public class OrderItemBO {
	
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	
	/**
	 * 分页查询OrderItem Interface
	 * 根据会员获取购买的产品
	 * @param page
	 * @param queryMap
	 * @return
	 */
	public Page<OrderItem> pageQueryInterface(final Map<String,Object> sqlMap){
		return PageQueryUtils.pageQuery(sqlMap, new PageQuery<OrderItem>() {
			@Override
			public List<OrderItem> list(){
				List<OrderItem> list = orderItemMapper.getItemsByMemberId(sqlMap);
				return list;
			}
			@Override
			public long count(){
				return orderItemMapper.getItemsByMemberIdCount(sqlMap);
			}
		});
	}
	
	/**
	 * 根据订单编号获取订单行信息
	 * @return
	 */
	public List<OrderItem> findOrderItemListByOrderId(Long orderId){
		return orderItemMapper.getItems(orderId);
	}
	
	public void batchInsert(List<OrderItem> list){
		orderItemMapper.batchInsert(list);
	}
	
	public int updatecommentStuats (List<Long> id){		
		return orderItemMapper.updateCommentStatus(id);
	}
	
	//某个时间段内购买的商品，根据购买数量倒序
	public List<OrderItem> getOrderByProCount(Map<String, Object> sqlMap){
		return orderItemMapper.getOrderByProCount(sqlMap);
	}
	
	public OrderItem findById(Long id){
		return orderItemMapper.getById(id);
	}
	
	public void update(OrderItem orderItem){
		orderItemMapper.update(orderItem);
	}
}
