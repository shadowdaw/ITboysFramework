package org.itboys.product.dao.order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.itboys.product.dto.order.OrderDTO;
import org.itboys.product.entity.mysql.Order;


/**
 * 订单DAO层接口
 */
public interface OrderMapper {

	/**
	 * 插入一个订单
	 * @param order
	 */
	public void insert(Order order);
	
	/**
	 * 获取订单I
	 * @param id
	 * @return
	 */
	public  Order  getOrder(Long id);
	
	/**
	 * 更新订单
	 * @param order
	 * @return
	 */
	public int update(Order order);
	
	/**
	 * 根据订单编号更新订单状态
	 * @param order
	 */
	public void updateOrderStatus(Order order);
	
	/**
	 * 根据查询条件 count订单数量
	 * @param paramMap
	 * key memberId 会员ID
	 * key orderNo 订单号
	 * key id 订单ID
	 * key status 订单状态
	 * key mobil  交易时候的手机号
	 * key name  交易时候的收获人姓名
	 * @return
	 */
	public long count(Map<String,Object> paramMap);
	
	
	//会员交易成功金额
	public BigDecimal memberOrderTotalFee(Long memberId);
	
	
	//会员交易成功订单总数
	public long memberOrderTotalNum(Long memberId);
	
	//会员交易成功产品总数
	public long memberProductTotalNum(Long memberId);
	
	/**
	 * 根据查询条件list订单列表
	 * @param paramMap
	 * key memberId 会员ID
	 * key orderNo 订单号
	 * key id 订单ID
	 * key status 订单状态
	 * key mobil  交易时候的手机号
	 * key name  交易时候的收获人姓名
	 * @return
	 */
	public  List<Order> list(Map<String,Object> paramMap);
	
	public List<Order>exportOrders(Map<String, Object> sqlMap);
	/**
	 * 填写快递单号之后,
	 * @param order
	 * @return
	 */
	public int updateExpress(Order order);
	/**
	 * 根据品牌id查找订单
	 * @param listfindByBrandIds
	 * @return 
	 */
	public List<Order>findByBrandIds(Map<String,Object> paramMap);

	
	
	/**
	 * 根据查询条件list订单列表
	 * 
	 */
	public  List<Order> listStatistics(Map<String,Object> paramMap);
	/**
	 * 根据查找订单数和利润
	 * @param list
	 * @return 
	 */
	public OrderDTO getStatistics(Map<String,Object> paramMap);
	
	/**
	 * 根据查找订单数和利润数量
	 * @param list
	 * @return 
	 */
	public long countStatistics(Map<String,Object> paramMap);
	
	public List<Order> getByIds(List<Long>list);
	
	/**
	 * 删除订单，目前是修改订单状态为-1
	 */
	public int upStatusIsDelete(Long id);
}
