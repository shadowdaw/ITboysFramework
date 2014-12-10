package org.itboys.product.dto.order;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.itboys.commons.CommonConstants;

/**
 * 新的购物车对象
 * @author huml
 *
 */
public class ShoppingCart {

	private Long memberId;//会员ID
	
	private String cookie;//购物车cookiekey
	
	private BigDecimal totalPrices;//每个项目计算价格和的方式不同，这里可以个计算各的
	
	@SuppressWarnings("unchecked")
	private List<CartItemDetail> items = Collections.EMPTY_LIST;
	
	/**
	 * 购物车总价
	 * @return
	 */
	public BigDecimal getTotalPrice(){
		BigDecimal totalPrice = CommonConstants.ZERO;
		for(CartItemDetail cd:items){
			totalPrice=totalPrice.add(cd.getTotalPrice());
		}
		return totalPrice;
	}
	
	/**
	 * 购物车折扣价小计
	 * @return
	 */
	public BigDecimal getTotalDiscountPrice(){
		BigDecimal totalPrice = CommonConstants.ZERO;
		for(CartItemDetail cd:items){
			totalPrice=totalPrice.add(cd.getTotalDiscountPrice());
		}
		return totalPrice;
	}
	
	/**
	 * 市场价总价
	 * @return
	 */
	public BigDecimal getTotalMarketPrice(){
		BigDecimal totalPrice = CommonConstants.ZERO;
		for(CartItemDetail cd:items){
			totalPrice=totalPrice.add(cd.getTotalMarketPrice());
		}
		return totalPrice;
	}
	
	/**
	 * 购物车总价(如果不是促销商品就按这个价格计算)
	 * @return
	 */
	public BigDecimal getWuweiTotalPrice(){
		BigDecimal totalPrice = CommonConstants.ZERO;
		for(CartItemDetail cd:items){
			if(cd.getProduct().getType().equals(0)){//这个是促销商品
				totalPrice=totalPrice.add(cd.getTotalDiscountPrice());
			}else{
				totalPrice=totalPrice.add(cd.getTotalPrice());
			}
		}
		return totalPrice;
	}
	
	/**
	 * 扩展方法 根据某个字段 获取总价 基于baseproduct的子类的数字类型的字段用
	 * @param field
	 * @return
	 */
	public BigDecimal getTotalPriceByField(String field){
		BigDecimal totalPrice = CommonConstants.ZERO;
		for(CartItemDetail cd:items){
			totalPrice=totalPrice.add(cd.getTotalPriceByField(field));
		}
		return totalPrice;
	}
	
	/**
	 * 获得总重量
	 * @return
	 */
	public BigDecimal getTotalWeight(){
		BigDecimal totalWight =  new BigDecimal(0);
		for(CartItemDetail cd:items){
			totalWight = totalWight.add(cd.getProduct().getWeight());
		}
		return totalWight;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public List<CartItemDetail> getItems() {
		return items;
	}

	public void setItems(List<CartItemDetail> items) {
		this.items = items;
	}

	public BigDecimal getTotalPrices() {
		return totalPrices;
	}

	public void setTotalPrices(BigDecimal totalPrices) {
		this.totalPrices = totalPrices;
	}
	
	
	
}
