package org.itboys.product.service.order;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.itboys.commons.utils.collection.CommonCollectionUtils;
import org.itboys.product.dao.order.CartItemMapper;
import org.itboys.product.dto.order.CartItemDetail;
import org.itboys.product.dto.order.ShoppingCart;
import org.itboys.product.entity.mysql.CartItem;
import org.itboys.product.entity.mysql.product.Product;
import org.itboys.product.entity.mysql.product.ProductDetail;
import org.itboys.product.service.product.ProductBO;
import org.itboys.product.service.product.ProductDetailBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 新的购物车管理
 * @author huml
 *
 */
@Service
public class NewCarItemManager {

	@Autowired(required=false)
	private CartItemMapper newCarItemMapper;
	@Autowired
	private ProductBO productBO;
	@Autowired
	private ProductDetailBO productDetailBO;
	
	/**
	 * 登入会员往购物车加商品
	 * @param product
	 * @param num
	 * @param elements
	 * @param memberId
	 * @param guigeIds
	 */
	public  void addCarItem(Product product,int num,String elements,Long memberId,String guigeIds){
		addCarItem(product, num, elements, memberId, null,guigeIds);
	}
	
	/**
	 * 未登入会员往购物车里加东西
	 * @param product
	 * @param num
	 * @param elements
	 * @param cookie
	 * @param guigeIds
	 */
	public  void addCarItem(Product product,int num,String elements,String cookie,String guigeIds){
		addCarItem(product, num, elements, null, cookie,guigeIds);
	}
	
	/**
	 * 往购物车里加商品
	 * @param product 商品对象 BaseProduct的子类
	 * @param fee 根据促销算法算出来
	 * @param num 可传负数 从已有的商品中减数量的时候用
	 * @param elements 商品规格等描述信息 转字符串冗余
	 * @param memberId 会员ID 如果是登入的话 否则搞null 
	 * @param cookie 购物车 cookie key 如果没登入的话 否则搞null
	 * @return 1: 新加的一个商品 0: 在原来的商品上加 -1:删除一个商品
	 */
	public int addCarItem(Product product,int num,String elements,Long memberId,String cookie,String guigeIds){
		Long productId = product.getId();
		CartItem exists = (memberId!=null ? newCarItemMapper.getCarItemByMemberId(memberId, productId, guigeIds):newCarItemMapper.getCarItemByCookie(cookie, productId,guigeIds));
		if(exists!=null){
			if(exists.getNum()+num<=0){
				if(memberId!=null){
					newCarItemMapper.deleteCarItemByMemberId(memberId, productId, guigeIds);
				}else{
					newCarItemMapper.deleteCarItemByCookie(cookie, productId, guigeIds);
				}
				return -1;
			}
			exists.setFee(product.getPrice());
			exists.setPrice(product.getPrice());
			exists.setNum(exists.getNum()+num);
			exists.setElements(elements);
			exists.setGuigeIds(guigeIds);
			newCarItemMapper.update(exists);
			return 0;
		}else{
			CartItem item = new CartItem();
			item.setPrice(product.getPrice());
			item.setProductId(productId);
			item.setFee(product.getPrice());
			item.setElements(elements);
			item.setNum(num);
			item.setGuigeIds(guigeIds);
			if(memberId!=null){
				item.setMemberId(memberId);
				item.setCookie("0");//登录过就设置cookieid为0
			}else{
				item.setCookie(cookie);   //这里如果未登录就将cookie设置进去
				item.setMemberId(0l);   //未登录就设置memberid为0
			}
			newCarItemMapper.insert(item);//新增购物车里面的商品项
			return 1;//购物车里面新增了商品项
		}
	}
	
	public CartItem getCarItemByCookie(String cookie,Long productId,String guigeIds){
		return newCarItemMapper.getCarItemByCookie(cookie, productId, guigeIds);
	}
	
	public CartItem getCarItemByCookieAndType(String cookie,Long productId,String guigeIds,Integer type,Long objId){
		return newCarItemMapper.getCarItemByCookieAndType(cookie, productId, guigeIds,type,objId);
	}
	
	public CartItem getCarItemByMemberId(Long memberId,Long productId,String guigeIds){
		return newCarItemMapper.getCarItemByMemberId(memberId, productId, guigeIds);
	}
	
	public CartItem getCarItemByMemberIdAndType(Long memberId,Long productId,String guigeIds,Integer type,Long objId){
		return newCarItemMapper.getCarItemByMemberIdAndType(memberId, productId, guigeIds,type,objId);
	}
	
	public int deleteCarItemByCookies(String cookie,Long productId,String guigeIds){
		return newCarItemMapper.deleteCarItemByCookie(cookie, productId, guigeIds);
	}
	
	public int deleteCarItemByCookiesAndType(String cookie,Long productId,String guigeIds,Integer type,Long objId){
		return newCarItemMapper.deleteCarItemByCookieAndType(cookie, productId, guigeIds,type,objId);
	}
	
	public int deleteCarItemByMemberId(Long memberId,Long productId,String guigeIds){
		return newCarItemMapper.deleteCarItemByMemberId(memberId, productId, guigeIds);
	}
	public int deleteCarItemByMemberIdAndType(Long memberId,Long productId,String guigeIds,Integer type,Long objId){
		return newCarItemMapper.deleteCarItemByMemberIdAndType(memberId, productId, guigeIds,type,objId);
	}
	
	public int deleteCarItem(Long memberId,String cookie,Long productId,String guigeIds){
		return memberId!=null?deleteCarItemByMemberId(memberId, productId, guigeIds):deleteCarItemByCookies(cookie, productId, guigeIds);
	}
	
	public List<CartItem> getCarItemsByMemberId(Long memberId){
		return newCarItemMapper.getCarItemsByMemberId(memberId);
	}
	
	public List<CartItem> getCarItemsByCookie(String cookie){
		return newCarItemMapper.getCarItemsByCookie(cookie);
	}
	

	@SuppressWarnings("unchecked")
	public ShoppingCart getCar(Long memberId,String cookieKey){
		List<CartItem> carItems = memberId!=null?getCarItemsByMemberId(memberId):getCarItemsByCookie(cookieKey);
		List<CartItemDetail> items = Lists.newArrayListWithCapacity(carItems.size());
		for(CartItem ci:carItems){
			Long productId = ci.getProductId();
			Product product = productBO.findProductById(productId);
			//这个等用到的时候 根据 NewCarItem 的 guigeIds 劈开后 in查询获得
			List<Long> guigeIds =  CommonCollectionUtils.splictToLongList(ci.getGuigeIds(), ",");
			Map<String, Object> sqlMap = Maps.newHashMapWithExpectedSize(2);
			List<ProductDetail> details;
			if(!guigeIds.isEmpty() && guigeIds.size()>0){
				sqlMap.put("ids", guigeIds);
				details = productDetailBO.getList(sqlMap);
			}else{
				details = Collections.EMPTY_LIST;
			}
			
			if(product!=null){
				CartItemDetail cid = new CartItemDetail(product, ci.getNum(),ci.getType(),ci.getObjId(),ci.getElements(),details,ci.getGuigeIds());
				items.add(cid);
			}  
		}
		ShoppingCart car = new ShoppingCart();
		car.setItems(items);
		car.setMemberId(memberId);
		car.setCookie(cookieKey);
		return car;
	}
	
	/**
	 * 清空购物车
	 * @param cookie
	 * @param memberid
	 */
	public void clearCar(String cookie,Long memberId){
		if(memberId!=null){
			newCarItemMapper.deleteCarItemsByMemberId(memberId);
		}
		if(cookie!=null){
			newCarItemMapper.deleteCarItemsByCookie(cookie);
		}
	}
	
	public void cookieToSession(Long memberId,String cookie){
		newCarItemMapper.cookieToSession(memberId, cookie);
	}
}
