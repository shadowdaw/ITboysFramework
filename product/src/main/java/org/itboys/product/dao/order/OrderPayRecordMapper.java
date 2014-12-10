package org.itboys.product.dao.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.itboys.product.entity.mysql.OrderPayRecord;

/**
 * 订单三方支付记录表相关DAO操作
 */
public interface OrderPayRecordMapper {

	/**
	 * 插
	 * 
	 * @param orderPayRecord
	 */
	public void insert(OrderPayRecord orderPayRecord);

	/**
	 * 根据订单ID 和外部支付平台类型 load相关数据 按ID 降序 取最后一条
	 * 
	 * @param orderId
	 * @param type
	 * @return
	 */
	public OrderPayRecord getOrderPayRecord(@Param("orderId") Long orderId, @Param("type") Integer type);

	/**
	 * 根据外部 ID 和外部支付平台类型 load相关数据 按ID 降序 取最后一条
	 * 
	 * @param orderId
	 * @param type
	 * @return
	 */
	public OrderPayRecord getOrderPayRecordByOutNo(@Param("externalId") String externalId, @Param("type") Integer type);

	public List<OrderPayRecord> getOrderPayRecordsByOutNo(@Param("externalId") String externalId,
			@Param("type") Integer type);

	/**
	 * 更新外部支付平台回调参数
	 * 
	 * @param externalId
	 * @param returnParam
	 * @return
	 */
	public int updateReturnParam(@Param("externalId") String externalId, @Param("outTradeNo") String outTradeNo,
			@Param("returnParam") String returnParam);

	/**
	 * 更新外部支付平台回调参数
	 * 
	 * @param externalId
	 * @param returnParam
	 * @return
	 */
	public int updateReturnParamByOrderNo(@Param("orderNo") String orderNo, @Param("outTradeNo") String outTradeNo,
			@Param("returnParam") String returnParam);

	/**
	 * 修改支付状态为成功
	 * 
	 * @param oe
	 * @return
	 */
	public int update(OrderPayRecord oe);

	public OrderPayRecord getOrderPayRecordByOrderNo(@Param("orderNo") String orderNo, @Param("type") Integer type);
}
