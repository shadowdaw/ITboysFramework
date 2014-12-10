package org.itboys.product.dto.product;

import java.util.List;
import java.util.Map;

import org.itboys.product.entity.mysql.product.Product;
import org.itboys.product.entity.mysql.product.ProductComment;
import org.itboys.product.entity.mysql.product.ProductCount;
import org.itboys.product.entity.mysql.product.ProductExt;
import org.itboys.product.entity.mysql.product.ProductImages;
import org.itboys.product.entity.mysql.product.ProductParam;
import org.itboys.product.entity.mysql.product.ProductText;

/**
 * product的DTO 
 * 
 * @author huml
 * 
 */
public class ProductDTO {

	public static final String PRODUCT_TEXT = "productText";
	public static final String PRODUCT_IMAGES = "productImages";
	public static final String PRODUCT_PARAM = "param";
	public static final String PRODUCT_COUNT = "count";
	public static final String PRODUCT_COMMENT = "comment";

	private Product product;
	private ProductCount productCount;
	private ProductText productText;
	private List<ProductImages> productImages;
	private List<ProductExt> exts;
	private List<ProductComment> productComments;
	private Map<Long, String> paramMap;
	private List<ProductParam> productParams;
	private List<ParamValue> paramValue;

	public List<ParamValue> getParamValue() {
		return paramValue;
	}

	public void setParamValue(List<ParamValue> paramValue) {
		this.paramValue = paramValue;
	}

	public List<ProductParam> getProductParams() {
		return productParams;
	}

	public void setProductParams(List<ProductParam> productParams) {
		this.productParams = productParams;
	}

	public Map<Long, String> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<Long, String> paramMap) {
		this.paramMap = paramMap;
	}

	public List<ProductComment> getProductComments() {
		return productComments;
	}

	public void setProductComments(List<ProductComment> productComments) {
		this.productComments = productComments;
	}

	public ProductCount getProductCount() {
		return productCount;
	}

	public void setProductCount(ProductCount productCount) {
		this.productCount = productCount;
	}

	public ProductText getProductText() {
		return productText;
	}

	public void setProductText(ProductText productText) {
		this.productText = productText;
	}

	public List<ProductImages> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<ProductImages> productImages) {
		this.productImages = productImages;
	}

	public List<ProductExt> getExts() {
		return exts;
	}

	public void setExts(List<ProductExt> exts) {
		this.exts = exts;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
