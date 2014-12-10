package org.itboys.product.service.order;

import java.util.List;

import org.itboys.product.dao.order.OrderPayRecordMapper;
import org.itboys.product.entity.mysql.OrderPayRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderPayRecordBO {

	@Autowired
	private OrderPayRecordMapper orderPayRecordMapper;
	
	public void insert(OrderPayRecord oe){
		 orderPayRecordMapper.insert(oe);
	}
	
	public OrderPayRecord getOrderPayRecordByOutNo( String externalId, Integer type){
		return orderPayRecordMapper.getOrderPayRecordByOutNo(externalId, type);
	}
	
	public List<OrderPayRecord> getOrderPayRecordsByOutNo( String externalId, Integer type){
		return orderPayRecordMapper.getOrderPayRecordsByOutNo(externalId, type);
	}
	
	public OrderPayRecord getOrderPayRecordByOrderNo( String orderNo, Integer type){
		return orderPayRecordMapper.getOrderPayRecordByOrderNo(orderNo, type);
	}
	
	
	public OrderPayRecord getOrderPayRecord(Long orderId,Integer type){
		return orderPayRecordMapper.getOrderPayRecord(orderId, type);
		
	}
	public int updateReturnParam(String externalId, String outTradeNo,String returnParam){
		return orderPayRecordMapper.updateReturnParam(externalId, outTradeNo, returnParam);
	}
	
	public int updateReturnParamByOrderNo(String orderNo, String outTradeNo,String returnParam){
		return orderPayRecordMapper.updateReturnParamByOrderNo(orderNo, outTradeNo, returnParam);
	}

	public int update(OrderPayRecord oe){  
		return orderPayRecordMapper.update(oe);
	}

}
