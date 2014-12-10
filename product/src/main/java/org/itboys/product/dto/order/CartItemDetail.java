package org.itboys.product.dto.order;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.itboys.commons.CommonConstants;
import org.itboys.commons.utils.number.ToNumberUtils;
import org.itboys.product.entity.mysql.CartItem;
import org.itboys.product.entity.mysql.product.Product;
import org.itboys.product.entity.mysql.product.ProductDetail;

/**
 * 新的购物车项DTO
 * @author huml
 *
 */
public class CartItemDetail extends CartItem {

	private static final long serialVersionUID = 6406377431226351992L;
	private Product product;
	private List<ProductDetail> productDetails;
	
	public CartItemDetail(){
		super();
	}
	
	public CartItemDetail(Product product,Integer num,Integer type,Long objId,String elementsDesc,List<ProductDetail> productDetails,String guigeIds){
		super();
		this.product = product;
		this.setNum(num);
		this.setType(type);
		this.setObjId(objId);
		this.setElements(elementsDesc);
		this.setGuigeIds(guigeIds);
		this.productDetails=productDetails;
	}
	

	public Product getProduct() {
		return product;
	}

	public List<ProductDetail> getProductDetails() {
		return productDetails;
	}
	
	/**
	 * 购物车明细总价格小计.
	 */
	public BigDecimal getTotalPrice() {
		if (product == null){
			return CommonConstants.ZERO;
		}else{
			BigDecimal price = product.getPrice();
			if(price==null || price.compareTo(CommonConstants.ZERO)<=0){
				return CommonConstants.ZERO;
			}
			return price.multiply(new BigDecimal(getNum()));
		}	
	}
	
	/**
	 * 购物车折扣价小计
	 * @return
	 */
	public BigDecimal getTotalDiscountPrice(){
		if (product == null){
			return CommonConstants.ZERO;
		}else{
			BigDecimal price = product.getDiscountPrice();
			if(price==null || price.compareTo(CommonConstants.ZERO)<=0){
				return CommonConstants.ZERO;
			}
			return price.multiply(new BigDecimal(getNum()));
		}	
	}
	
	/**
	 * 市场价总价
	 * @return
	 */
	public BigDecimal getTotalMarketPrice(){
		if (product == null){
			return CommonConstants.ZERO;
		}else{
			BigDecimal price = product.getMarketPrice();
			if(price==null || price.compareTo(CommonConstants.ZERO)<=0){
				return CommonConstants.ZERO;
			}
			return price.multiply(new BigDecimal(getNum()));
		}	
	}
	
	/**
	 * 扩展方法 根据某个字段 获取总价 基于baseproduct的子类的数字类型的字段用
	 * @param field
	 * @return
	 */
	public BigDecimal getTotalPriceByField(String field){
		try{
			Object filedValue = PropertyUtils.getProperty(product, field);
			BigDecimal decimalValue = ToNumberUtils.getBigDecimal(filedValue);
			decimalValue=(decimalValue==null?CommonConstants.ZERO:decimalValue);
			return decimalValue.multiply(new BigDecimal(getNum()));
		}catch(Exception e){
			if (e instanceof RuntimeException) {
				throw (RuntimeException) e;
			} else {
				throw new RuntimeException(e);
			}
		}
	}


}
