package org.itboys.product.dao.order;

import java.util.List;

import org.itboys.product.entity.mysql.ReceiveAddress;


public interface ReceiveAddressMapper {
	/**
	 * 插入一条收货地址信息
	 * @param 
	 */
	public void insert(ReceiveAddress receiveAddress);
	/**
	 * 删除一条地址
	 * @param 地址id
	 * @return
	 */
	public int delete(Integer id);
	/**
	 * 修改一条地址
	 * 
	 */
	public int update(ReceiveAddress receiveAddress);
	/**
	 * 查询地址
	 * 
	 */
	public List<ReceiveAddress> getAddress(Long memberId);
	/**
	 * 拿到一条地址信息
	 */
	public ReceiveAddress getReceiveAddressById(long id);
	
	public List<ReceiveAddress> getByIds(List<Long>list);
}