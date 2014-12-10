package org.itboys.product.dto.order;

import java.math.BigDecimal;

import org.itboys.product.entity.mysql.Order;


/**
 **根据条件 统计order 条数 金额
 */

public class OrderDTO extends Order {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1477882199272994103L;

	/**
	 * 
	 */
	private  Integer numor;  			//条数
	
	private  BigDecimal  pfee;         //总收益

	public Integer getNumor() {
		return numor;
	}

	public void setNumor(Integer numor) {
		this.numor = numor;
	}

	public BigDecimal getPfee() {
		return pfee;
	}

	public void setPfee(BigDecimal pfee) {
		this.pfee = pfee;
	}
	

	


	


	
		
	
	
		
		
		
	
	

}
