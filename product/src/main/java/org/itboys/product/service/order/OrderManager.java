package org.itboys.product.service.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.itboys.framework.query.Page;
import org.itboys.framework.query.PageQuery;
import org.itboys.framework.query.PageQueryUtils;
import org.itboys.product.constant.TradeConstants;
import org.itboys.product.dao.order.OrderItemMapper;
import org.itboys.product.dao.order.OrderMapper;
import org.itboys.product.dto.order.OrderDTO;
import org.itboys.product.entity.mysql.Order;
import org.itboys.product.entity.mysql.OrderItem;
import org.itboys.member.service.MemberService;
import org.itboys.member.entity.mongo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

/**
 * 订单管理
 * @author huml
 *
 */
@Service
public class OrderManager {
	
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;
	@Autowired
	private MemberService memberMapper;
	/**
	 * 分页查询订单
	 * @param sqlMap
	 * @return
	 */
	public Page<Order> pageQuery(final Map<String, Object> sqlMap){
		Page<Order> page = PageQueryUtils.pageQuery(sqlMap, new PageQuery<Order>() {

			@Override
			public long count() {
				return orderMapper.count(sqlMap);
			}

			@Override
			public List<Order> list() {
				return orderMapper.list(sqlMap);
			}
		});
		return page;
	}
	
	public List<Order>exportOrders(Map<String, Object> sqlMap){
		return orderMapper.exportOrders(sqlMap);
	}
	
	/**
	 * 分页查询订单并带上订单行信息行
	 * @param sqlMap
	 * @return
	 */
	public Page<Order> pageQueryWithItems(final Map<String, Object> sqlMap){
		Page<Order> page = pageQuery(sqlMap);
		List<Order> orders = page.getResult();
		if(orders!=null && !orders.isEmpty()){
			List<Long> orderIds = Lists.transform(orders, TradeConstants.getOrderId);
			//查一次数据库 拿出所有订单关联的订单行 循环组装
			List<OrderItem> items = orderItemMapper.getItemsByOrderIds(orderIds);
			for(Order order:orders){
				order.setItems(new ArrayList<OrderItem>());
				for(OrderItem oi:items){
					if(oi.getOrderId().equals(order.getId())){
						order.getItems().add(oi);
					}
				}
			}
		}
		return page;
	}
	
	/**
	 * 分页查询订单并带上订单行信息行
	 * @param sqlMap
	 * @return
	 */
	public Page<Order> pageQueryWithItemsAndMembers(final Map<String, Object> sqlMap){
		Page<Order> page = pageQueryWithItems(sqlMap);
		List<Order> orders = page.getResult();
		if(orders!=null && !orders.isEmpty()){
			List<Long> memberIds = Lists.transform(orders, TradeConstants.getMemberId);
			//查一次数据库 拿出所有订单关联的订单行 循环组装
			List<Member> items = memberMapper.getByIds(memberIds);
			for(Order order:orders){
				for(Member oi:items){
					if(Long.valueOf(oi.getId()).equals(order.getMemberId())){
						order.setMember(oi);
					}
				}
			}
		}
		return page;
	}
	/**
	 * 分页查询订单
	 * @param sqlMap
	 * @return
	 */
	public Page<Order> pageQueryStatistics(final Map<String, Object> sqlMap){
		Page<Order> page = PageQueryUtils.pageQuery(sqlMap, new PageQuery<Order>() {
			@Override
			public long count() {
				return orderMapper.countStatistics(sqlMap);
			}

			@Override
			public List<Order> list() {
				return orderMapper.listStatistics(sqlMap);
			}
		});
		return page;
	}
	/**
	 * 查询订单项
	 * @param orderId
	 * @return
	 */
	public List<OrderItem> queryItems(Long orderId){
		return orderItemMapper.getItems(orderId);
	}
	
	public int update(Order order){
		return orderMapper.update(order);
	}
	
	public int updateItem(OrderItem orderItem){
		return orderItemMapper.update(orderItem);
	};
	
	public Order getOrder(Long id){
		return orderMapper.getOrder(id);
	}
	
	public OrderItem  getById(Long id){
		return orderItemMapper.getById(id);
	};
	
	public int updateExpress(Order order){
		return orderMapper.updateExpress(order);
	}
	
	public List<Order>findByBrandIds(Map<String,Object> paramMap){
		return orderMapper.findByBrandIds(paramMap);

	}
	
	public List<OrderItem>findByBrandIdss(List<Long> list){
		return orderItemMapper.findByBrandIdss(list);
	}
	
	public List<OrderItem>findByOids(List<Long>list){
		return orderItemMapper.getItemsByOrderIds(list); 
	}
	

	public  List<Order> listStatistics(Map<String,Object> paramMap){
		return orderMapper.listStatistics(paramMap); 
	}
	
	public OrderDTO getStatistics(Map<String,Object> paramMap){
		return orderMapper.getStatistics(paramMap);
	}
	
	public List<Order> getByIds(List<Long>list){
		return orderMapper.getByIds(list);
	}
	

}