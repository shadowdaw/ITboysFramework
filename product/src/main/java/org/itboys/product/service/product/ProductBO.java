package org.itboys.product.service.product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.itboys.framework.query.Page;
import org.itboys.framework.query.PageQuery;
import org.itboys.framework.query.PageQueryUtils;
import org.itboys.product.dao.product.ProductMapper;
import org.itboys.product.dto.product.ProductCountDTO;
import org.itboys.product.dto.product.ProductDTO;
import org.itboys.product.dto.product.ProductResult;
import org.itboys.product.entity.mysql.product.Product;
import org.itboys.product.entity.mysql.product.ProductCategory;
import org.itboys.product.entity.mysql.product.ProductCount;
import org.itboys.product.entity.mysql.product.ProductDetail;
import org.itboys.product.entity.mysql.product.ProductDetailCode;
import org.itboys.product.entity.mysql.product.ProductDetails;
import org.itboys.product.entity.mysql.product.ProductExt;
import org.itboys.product.entity.mysql.product.ProductImages;
import org.itboys.product.entity.mysql.product.ProductKeyword;
import org.itboys.product.entity.mysql.product.ProductParam;
import org.itboys.product.entity.mysql.product.ProductStockSize;
import org.itboys.product.entity.mysql.product.ProductText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 产品管理业务层逻辑
 * 
 * @author huml
 * 
 */
@Service
public class ProductBO {

	@Autowired
	private ProductCategoryBO productCategoryBO;
	@Autowired
	private ProductExtBO productExtBO;
	@Autowired
	private ProductImagesBO productImagesBO;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductCountBO productCountBO;
	@Autowired
	private ProductTextBO productTextBO;
	@Autowired
	private ProductKeywordBO productKeywordBO;
	@Autowired
	private ProductParamBO productParamBO;
	@Autowired
	private ProductDetailBO productDetailBO;
	@Autowired
	private ProductDetailsBO productDetailsBO;
	@Autowired
	private ProductStockSizeBO productStockSizeBO;
	@Autowired
	private ProductCommentBO productCommentBO;
	@Autowired
	private ProductDetailCodeBO productDetailCodeBO;
	@Autowired
	private ProductCategoryConfigBO productCategoryConfigBO;

	public ProductDTO getProductDTO(Long productId, String... needObjectName) {
		ProductDTO dto = new ProductDTO();
		Product product = productMapper.findProductById(productId);
		dto.setProduct(product);
		if (ArrayUtils.contains(needObjectName, ProductDTO.PRODUCT_TEXT)) {
			dto.setProductText(productTextBO.findByProductId(productId));
		}
		if (ArrayUtils.contains(needObjectName, ProductDTO.PRODUCT_IMAGES)) {
			dto.setProductImages(productImagesBO.findByProductId(productId));
		}
		if (ArrayUtils.contains(needObjectName, ProductDTO.PRODUCT_COUNT)) {
			dto.setProductCount(productCountBO.findByProductId(productId));
		}
		if (ArrayUtils.contains(needObjectName, ProductDTO.PRODUCT_COMMENT)) {
			dto.setProductComments(productCommentBO.findByProductId(productId));
		}
		if (ArrayUtils.contains(needObjectName, ProductDTO.PRODUCT_PARAM)) {
			dto.setParamMap(productParamBO.getParamMap(productId));
		}
		return dto;
	}

	public ProductResult getProduct(Long productId) {
		productCountBO.addViewCount(productId);
		return productMapper.findById(productId);
	}

	public Product findProductById(Long id) {
		return productMapper.findProductById(id);
	}

	public List<Product> findHotProduct(Map<String, Object> sqlMap) {
		return productMapper.findHotProduct(sqlMap);
	}

	public Page<ProductResult> pageQuery(final Page<ProductResult> page, final Map<String, Object> sqlMap) {
		return PageQueryUtils.pageQuery(page, sqlMap, new PageQuery<ProductResult>() {
			@Override
			public long count() {
				return productMapper.count(sqlMap);
			}

			@Override
			public List<ProductResult> list() {
				List<ProductResult> list = productMapper.list(sqlMap);
				return list;
			}
		});
	}

	public Page<ProductCountDTO> pageQueryCount(final Map<String, Object> sqlMap) {
		return PageQueryUtils.pageQuery(sqlMap, new PageQuery<ProductCountDTO>() {
			@Override
			public long count() {
				// TODO Auto-generated method stub
				return productMapper.countCount(sqlMap);
			}

			@Override
			public List<ProductCountDTO> list() {
				// TODO Auto-generated method stub
				List<ProductCountDTO> list = productMapper.listCount(sqlMap);
				return list;
			}
		});
	}

	// portal接口用
	public Page<ProductResult> pageQuerys(final Map<String, Object> sqlMap) {
		return PageQueryUtils.pageQuery(sqlMap, new PageQuery<ProductResult>() {
			@Override
			public long count() {
				// TODO Auto-generated method stub
				return productMapper.count(sqlMap);
			}

			@Override
			public List<ProductResult> list() {
				// TODO Auto-generated method stub
				List<ProductResult> list = productMapper.list(sqlMap);
				return list;
			}
		});
	}

	public List<Product> findRecommend() {
		return productMapper.findRecommend();
	}

	public List<ProductResult> list(Map<String, Object> map) {
		List<ProductResult> list = productMapper.list(map);
		return list;
	}

	public long count(Map<String, Object> map) {
		Long coun = productMapper.count(map);
		return coun;
	}

	public List<Product> getRand5Product(Long catId, Long id) {
		return productMapper.getRand5Product(catId, id);
	}

	/**
	 * 保存商品
	 * 
	 * @param product
	 * @param productText大字段
	 * @param productImages
	 *            商品图片 没有就传null
	 * @param exts
	 *            商品扩展字段 没有就传null saveProduct
	 */
	public int saveProduct(Product product, ProductText productText, List<ProductImages> productImages,
			List<ProductExt> exts, List<ProductKeyword> keys) {
		int pro;
		if (product.getId() == null) {
			ProductCategory pcat = productCategoryBO.getProductCategory(product.getCatId());
			if (pcat != null) {
				product.setFullCatidPath(pcat.getFullIdPath());
			} else {
				product.setFullCatidPath("");
			}
//			product.setIsRecommend(Product.ISRECOMMEND_NO);
			product.setIsDeleted(Product.ISDELETED_1);
			pro = productMapper.insert(product);
			// 商品图片
			if (productImages != null && !productImages.isEmpty()) {
				for (ProductImages pi : productImages) {
					pi.setProductId(product.getId());
				}
				productImagesBO.batchInsert(productImages);
			}
			// 商品大字段
			if (productText != null && !productText.equals("")) {
				productText.setProductId(product.getId());
				productTextBO.insert(productText);
			}
		
			// 商品扩展字段
			if (exts != null && !exts.isEmpty()) {
				for (ProductExt pe : exts) {
					pe.setProductId(product.getId());
				}
				productExtBO.insert(exts);
			}
			saveCount(product);

			// 商品关键字
			if (keys != null && !keys.isEmpty()) {
				for (ProductKeyword pe : keys) {
					pe.setProductId(product.getId());
				}
				productKeywordBO.batchInsert(keys);
			}
			// TODO 商品特征大类 和特征值
			// TODO 商品关联分类下参数详细值
		} else {
			// 修改。。。。。。。。。。。。。。
			//这个时候商品的fullCatIdPaht 也要改的
			ProductCategory pcat = productCategoryBO.getProductCategory(product.getCatId());
			if (pcat != null) {
				product.setFullCatidPath(pcat.getFullIdPath());
			}else{
				product.setFullCatidPath("");
			}
			product.setIsDeleted(Product.ISDELETED_1);
			pro = productMapper.update(product);
			// 商品图片
			// productImagesBO.deleteByProductId(product.getId());
			if (productImages != null && !productImages.isEmpty()) {
				for (ProductImages pi : productImages) {
					pi.setProductId(product.getId());
				}
				productImagesBO.batchInsert(productImages);
			}
			// 商品大字段
			productText.setProductId(product.getId());
			productTextBO.update(productText);
			// 商品扩展字段
			productExtBO.delete(product.getId());
			if (exts != null && !exts.isEmpty()) {
				for (ProductExt pe : exts) {
					pe.setProductId(product.getId());
				}
				productExtBO.insert(exts);
			}
			// 商品关键字
			productKeywordBO.deleteByProductId(product.getId());
			if (keys != null && !keys.isEmpty()) {
				for (ProductKeyword pe : keys) {
					pe.setProductId(product.getId());
				}
				productKeywordBO.batchInsert(keys);
			}
		}
		return pro;
	}

	@Transactional
	public void saveProduct(Product product, ProductText productText, List<ProductImages> productImages,
			List<ProductExt> exts, List<ProductKeyword> keys, List<ProductParam> params, String colorSizeStr,
			String productParamStr) {
		if (product.getId() != null) {
			// r如果不为空,则先删除旧的:product_detail_code product_detail product_param
			Long productId = product.getId();
			productDetailCodeBO.deleteByProductid(productId);
			productDetailBO.deleteByProductid(productId);
			productParamBO.deleteByProductid(productId);
			productStockSizeBO.delByProductId(productId);
			// 还需批量删除商品关联下的颜色尺码的库存量：
		}
		saveProduct(product, productText, productImages, exts, keys, params);
		Long productId = product.getId();
		if (colorSizeStr.length() > 0) {
			String[] arr = StringUtils.split(colorSizeStr, "***");// colorSizeStr
			// 第一个保存的是颜色尺码类别id，先颜色后尺码，再其他，无颜色尺码就一个的就一个，if contains ,,, 那么。。。。。。
			Long colorTypeId = null;
			Long sizeTypeId = null;
			if (StringUtils.contains(arr[0], ",,,")) {
				String[] colorSizeType = StringUtils.split(arr[0], ",,,");
				for (int i = 0; i < colorSizeType.length; i++) {
					ProductDetailCode productDetailCode = new ProductDetailCode();
					productDetailCode.setProductId(productId);
					productDetailCode.setCategoryCodeConfigId(Long.valueOf(colorSizeType[i]));
					productDetailCodeBO.insert(productDetailCode);
					if (i == 0) {
						colorTypeId = productDetailCode.getId();
					} else {
						sizeTypeId = productDetailCode.getId();
					}
				}
			}
			// 取出数组第三个值就是颜色和尺码的：以颜色在前，尺码在后中间以;;;分割，内部都以:::分割
			// 先保存颜色，再保存尺码，返回各自的id，同时将id和颜色/尺码值存在一个list结构里面:便于后面和库存关联的颜色尺码匹配，匹配上了的就保存起来
			Map<String, Long> idsMap = new HashMap<String, Long>();
			// 每保存一个img或xml都需要取出他们的值，所以，这里for循环保存
			if (StringUtils.contains(arr[2], ";;;")) {
				String sizeColorArr[] = StringUtils.split(arr[2], ";;;");
				for (int tt = 0; tt < sizeColorArr.length; tt++) {
					String colorOrSize[] = StringUtils.split(sizeColorArr[tt], ":::");
					for (int xx = 0; xx < colorOrSize.length; xx++) {
						ProductDetail productDetail = new ProductDetail();
						productDetail.setProductId(productId);
						if (tt == 0) {
							productDetail.setDetailCodeId(colorTypeId);// 颜色类别id
						} else {
							productDetail.setDetailCodeId(sizeTypeId);// 尺码类别id
						}
						// 这里加一个判断:if
						// colorOrSize[xx].contain("___")三个下划线,那么就拆分后set到iMg里面
						if (StringUtils.contains(colorOrSize[xx], "___")) {
							String[] colorNameSrc = StringUtils.split(colorOrSize[xx], "___");
							productDetail.setImg(colorNameSrc[0]);
							productDetail.setValue(colorNameSrc[1]);
						} else {
							productDetail.setValue(colorOrSize[xx]);
						}
						productDetailBO.insert(productDetail);// 保存：完成之后将颜色尺码值放入map：
						idsMap.put(colorOrSize[xx], productDetail.getId());
					}
				}
			}
			// 保存完成之后：来匹配每一条 颜色 ：：：尺码：：：库存
			String[] stock = StringUtils.split(arr[1], ";;;");
			int lenStock = stock.length;
			List<ProductStockSize> productStockSizeList0 = Lists.newArrayListWithExpectedSize(lenStock);
			// 遍历第二个arr[]那么就能拿到很多
			for (int wox = 0; wox < lenStock; wox++) {
				String[] xx = StringUtils.split(stock[wox], ":::");
				ProductStockSize productStockSizeIn = new ProductStockSize();
				productStockSizeIn.setProductId(productId);
				productStockSizeIn.setStockSize(Long.valueOf(xx[2]));
				for (String key : idsMap.keySet()) {
					if (StringUtils.equals(key, xx[0])) {
						productStockSizeIn.setDetailCodeId1(idsMap.get(key));
					}
					if (StringUtils.equals(key, xx[1])) {
						productStockSizeIn.setDetailCodeId2(idsMap.get(key));
					}
				}
				productStockSizeList0.add(productStockSizeIn);
			}
			// 批量保存颜色尺码的
			productStockSizeBO.batchInsert(productStockSizeList0);
		}
		if (productParamStr.length() > 0) {
			// 先保存颜色类别，返回id，然后保存尺码类别，返回id，
			// 保存productParamStr
			String[] productParam = StringUtils.split(productParamStr, ";;;");
			if (productParam.length > 0) {
				List<ProductParam> ppList = Lists.newArrayListWithCapacity(productParam.length);
				for (String str : productParam) {
					ProductParam entity = new ProductParam();
					String[] strArr = StringUtils.split(str, ":::");
					entity.setProductId(productId);
					entity.setParamConfigId(Long.parseLong(strArr[0]));
					if (strArr.length > 1) {
						entity.setValue(strArr[1]);
					}
					ppList.add(entity);
				}
				productParamBO.batchInsert(ppList);
			}
		}
	}

	/**
	 * 电动车商城保存商品---后面重构
	 * 
	 * @param product
	 * @param productText
	 * @param productImages
	 * @param exts
	 * @param keys
	 * @param params
	 * @param colorSizeStr
	 * @param productParamStr
	 * @return
	 */
	public int ddcSaveProduct(Product product, ProductText productText, List<ProductImages> productImages,
			List<ProductExt> exts, List<ProductKeyword> keys, List<ProductParam> params, String colorSizeStr,
			String productParamStr) {
		if (product.getId() != null) {
			// r如果不为空,则先删除旧的:product_detail_code product_detail product_param
			Long productId = product.getId();
			productDetailCodeBO.deleteByProductid(productId);
			productDetailBO.deleteByProductid(productId);
			productParamBO.deleteByProductid(productId);
			// 还需批量删除商品关联下的颜色尺码的库存量：
		}
		int pt = saveProduct(product, productText, productImages, exts, keys, params);
		saveCount(product);
		if (colorSizeStr.length() > 0) {
			String[] arr = StringUtils.split(colorSizeStr, "***");// colorSizeStr
			if (StringUtils.isNotEmpty(arr[0])) {
				ProductDetailCode productDetailCode = new ProductDetailCode();
				productDetailCode.setProductId(product.getId());
				productDetailCode.setCategoryCodeConfigId(Long.valueOf(arr[0]));
				productDetailCode.setIsChangePrice(ProductDetailCode.CHANGEPRICE_YES);
				productDetailCodeBO.insert(productDetailCode);
			}
			String strgg = arr[1];
			if (StringUtils.contains(strgg, ";;;")) {
				String[] arrgg = StringUtils.split(strgg, ";;;");// 规格
				for (int i = 0; i < arrgg.length; i++) {
					String strgp = arrgg[i];
					if (StringUtils.contains(strgp, "-")) {
						String[] arrgp = StringUtils.split(strgp, "-");// 库存
						ProductDetail productDetail = new ProductDetail();
						productDetail.setProductId(product.getId());
						productDetail.setDetailCodeId(Long.valueOf(arr[0]));
						productDetail.setValue(arrgp[0]);
						if (StringUtils.isNotEmpty(arrgp[2])) {
							double price = Double.valueOf(arrgp[2]);
							productDetail.setPrice(new BigDecimal(price)); // 价格
						}
						if (StringUtils.isNotEmpty(arrgp[1])) { // 库存
							String store = arrgp[1].toString();
							productDetail.setStockSize(Long.valueOf(store));
						}
						productDetailBO.insert(productDetail);
					}

				}
			} else {
				String strgp = strgg;
				if (StringUtils.contains(strgp, "-")) {
					String[] arrgp = StringUtils.split(strgp, "-");// 库存
					ProductDetail productDetail = new ProductDetail();
					productDetail.setProductId(product.getId());
					productDetail.setDetailCodeId(Long.valueOf(arr[0]));
					productDetail.setValue(arrgp[0]);
					if (StringUtils.isNotEmpty(arrgp[2])) {
						double price = Double.valueOf(arrgp[2]);
						productDetail.setPrice(new BigDecimal(price)); // 价格
					}
					if (StringUtils.isNotEmpty(arrgp[1])) { // 库存
						String store = arrgp[1].toString();
						productDetail.setStockSize(Long.valueOf(store));
					}
					productDetailBO.insert(productDetail);
				}
			}
		}
		if (productParamStr.length() > 0) {
			// 先保存颜色类别，返回id，然后保存尺码类别，返回id，
			// 保存productParamStr
			String[] productParam = StringUtils.split(productParamStr, ";;;");
			if (productParam.length > 0) {
				List<ProductParam> ppList = Lists.newArrayListWithCapacity(productParam.length);
				for (String str : productParam) {
					ProductParam entity = new ProductParam();
					String[] strArr = StringUtils.split(str, ":::");
					entity.setProductId(product.getId());
					entity.setParamConfigId(Long.parseLong(strArr[0]));
					if (strArr.length > 1) {
						entity.setValue(strArr[1]);
					}
					ppList.add(entity);
				}
				productParamBO.batchInsert(ppList);
			}
		}
		return pt;
	}

	private void saveCount(Product product) {
		ProductCount count = productCountBO.findByProductId(product.getId());
		if (count != null) {
			return;
		}
		count = new ProductCount();
		count.setProductId(product.getId());
		productCountBO.insert(count);
	}

	/**
	 * 午苇后台保存商品
	 * 
	 * @param product
	 * @param productText
	 * @param productImages
	 * @param exts
	 * @param keys
	 * @param params
	 * @param colorSizeStr
	 * @return
	 */
	public int saveProduct(Product product, ProductText productText, List<ProductImages> productImages,
			List<ProductExt> exts, List<ProductKeyword> keys, List<ProductParam> params, String colorSizeStr) {
		int pt = saveProduct(product, productText, productImages, exts, keys);
		if (params != null && !params.isEmpty()) {
			for (ProductParam pp : params) {
				pp.setProductId(product.getId());
			}
			productParamBO.deleteByProductid(product.getId());
			productParamBO.batchInsert(params);
		}
		if (product.getId() != null) {
			// r如果不为空,则先删除旧的:product_detail_code product_detail product_param
			Long productId = product.getId();
			productDetailCodeBO.deleteByProductid(productId);
			productDetailBO.deleteByProductid(productId);
			// 还需批量删除商品关联下的颜色尺码的库存量：
		}
		if (colorSizeStr.length() > 0) {
			String[] arr = StringUtils.split(colorSizeStr, "***");// colorSizeStr
			if (StringUtils.isNotEmpty(arr[0])) {
				ProductDetailCode productDetailCode = new ProductDetailCode();
				productDetailCode.setProductId(product.getId());
				productDetailCode.setCategoryCodeConfigId(Long.valueOf(arr[0]));
				productDetailCode.setIsChangePrice(ProductDetailCode.CHANGEPRICE_YES);
				productDetailCodeBO.insert(productDetailCode);
			}
			String strgg = arr[1];
			if (StringUtils.contains(strgg, ";;;")) {
				String[] arrgg = StringUtils.split(strgg, ";;;");// 规格

				for (int i = 0; i < arrgg.length; i++) {
					String strgp = arrgg[i];
					if (StringUtils.contains(strgp, "-")) {
						String[] arrgp = StringUtils.split(strgp, "-");// 库存
						ProductDetail productDetail = new ProductDetail();
						productDetail.setProductId(product.getId());
						productDetail.setDetailCodeId(Long.valueOf(arr[0]));
						productDetail.setValue(arrgp[0]);
						if (StringUtils.isNotEmpty(arrgp[2])) {
							double price = Double.valueOf(arrgp[2]);
							productDetail.setPrice(new BigDecimal(price)); // 价格
						}
						if (StringUtils.isNotEmpty(arrgp[1])) { // 库存
							String store = arrgp[1].toString();
							productDetail.setStockSize(Long.valueOf(store));
						}

						productDetailBO.insert(productDetail);
					}

				}
			} else {
				String strgp = strgg;
				if (StringUtils.contains(strgp, "-")) {
					String[] arrgp = StringUtils.split(strgp, "-");// 库存
					ProductDetail productDetail = new ProductDetail();
					productDetail.setProductId(product.getId());
					productDetail.setDetailCodeId(Long.valueOf(arr[0]));
					productDetail.setValue(arrgp[0]);
					if (StringUtils.isNotEmpty(arrgp[2])) {
						double price = Double.valueOf(arrgp[2]);
						productDetail.setPrice(new BigDecimal(price)); // 价格
					}
					if (StringUtils.isNotEmpty(arrgp[1])) { // 库存
						String store = arrgp[1].toString();
						productDetail.setStockSize(Long.valueOf(store));
					}

					productDetailBO.insert(productDetail);
				}
			}
		}
		return pt;
	}

	/**
	 * 拼装的特征值/颜色
	 * 
	 * @param product
	 * @param productText
	 * @param productImages
	 * @param exts
	 * @param keys
	 * @param params
	 * @param colorSizeStr
	 * @param colorSizeStr2
	 * @return
	 */
	public int saveProducts(Product product, ProductText productText, List<ProductImages> productImages,
			List<ProductExt> exts, List<ProductKeyword> keys, List<ProductParam> params, String colorSizeStr,
			String colorSizeStr2) {
		int pt = saveProduct(product, productText, productImages, exts, keys, params, colorSizeStr);
		if (colorSizeStr2.length() > 0) {// 颜色字符串
			String[] arr2 = StringUtils.split(colorSizeStr2, "***");// colorSizeStr
			if (arr2.length > 1) {
				if (StringUtils.isNotEmpty(arr2[0])) {
					ProductDetailCode productDetailCode = new ProductDetailCode();
					productDetailCode.setProductId(product.getId());
					productDetailCode.setCategoryCodeConfigId(Long.valueOf(arr2[0]));
					productDetailCode.setIsChangePrice(ProductDetailCode.CHANGEPRICE_YES);
					productDetailCodeBO.insert(productDetailCode);
				}
				String strgg = arr2[1];
				if (StringUtils.contains(strgg, ":::")) {
					String[] arrgg2 = StringUtils.split(strgg, ":::");// 颜色
					for (int i = 0; i < arrgg2.length; i++) {
						String strgp = arrgg2[i];
						if (StringUtils.contains(strgp, "-")) {
							String[] arrgp = StringUtils.split(strgp, "-");// 库存
							ProductDetail productDetail = new ProductDetail();
							productDetail.setProductId(product.getId());
							productDetail.setDetailCodeId(Long.valueOf(arr2[0]));
							productDetail.setValue(arrgp[0]);
							if (StringUtils.isNotEmpty(arrgp[2])) {
								double price = Double.valueOf(arrgp[2]);
								productDetail.setPrice(new BigDecimal(price)); // 价格
							}
							if (StringUtils.isNotEmpty(arrgp[1])) { // 库存
								String store = arrgp[1].toString();
								productDetail.setStockSize(Long.valueOf(store));
							}
							productDetailBO.insert(productDetail);
						}
					}
				}
			}
		}
		return pt;
	}

	/**
	 * 拼装的特征值/颜色,拼装的类型如下 39#21***1.20***1***1***1***一个大规格一个小规格
	 * 39#22***1.20***1***1***1*** &&& 21***1.20***2***2***2*** 一个大规格两个小规格
	 * 39|38#22,20***1.20***2***2***2*** 两个大规格两个小规格 。。。还有多种规格的组成
	 * 
	 * @param product
	 * @param productText
	 * @param productImages
	 * @param exts
	 * @param keys
	 * @param params
	 * @param colorSizeStr
	 * @param colorSizeStr2
	 * @return
	 * 
	 *         39|38#21,20***1.20***1***2***3***&&&22,20***1.20***4***5***6***
	 *         TODO:
	 */
	public int saveProducts(Product product, ProductText productText, List<ProductImages> productImages,
			List<ProductExt> exts, List<ProductKeyword> keys, List<ProductParam> params, String proGuigeDetails) {
		int pt = saveProduct(product, productText, productImages, exts, keys, params);
		if (product.getId() != null) {
			// r如果不为空,则先删除旧的:product_details, product_param
			Long productId = product.getId();
			// 还需批量删除商品关联下的颜色尺码的库存量
			productDetailsBO.deleteByProductid(productId);
			productParamBO.deleteByProductid(productId);
		}
		try {
			List<ProductDetails> lists = Lists.newArrayList();
			String[] details = StringUtils.split(proGuigeDetails, "#");
			if (details.length > 0) {
				// 第一个就是大规格的id
				String bigCode = details[0];
				String[] bigCodes = StringUtils.split(bigCode, "|");
				String smallCode = details[1];
				// 22,20***1.20***11***11***11***&&&21,20***1.20***12***12***12***
				String[] smallCodesPrice = StringUtils.split(smallCode, "&&&");
				if (smallCodesPrice.length > 0) {// 22,20***1.20***11***11***11***
					for (int j = 0; j < smallCodesPrice.length; j++) {
						String smallCodePrice = smallCodesPrice[j];// 多个小的规格
						String[] smallCodes = StringUtils.split(smallCodePrice, "***");
						if (smallCodes.length > 0) {
							String more_smallCodeIds = smallCodes[0];// 小规格的id
							String[] smallCodesId = StringUtils.split(more_smallCodeIds, ",");//22,20
							if(smallCodesId.length>0){
								ProductDetails proDetails = new ProductDetails();
								proDetails.setProductId(product.getId());
								proDetails.setDetail1(Long.valueOf(smallCodesId[0]));
								proDetails.setDetail2(Long.valueOf(smallCodesId[1]));
								proDetails.setPrice(new BigDecimal(smallCodes[1]));
								proDetails.setCount(Long.valueOf(smallCodes[2]));
								proDetails.setBianma(smallCodes[3]);
								proDetails.setTiaoxingma(smallCodes[4]);
								if(bigCodes.length>0){
									proDetails.setDetailName1(productCategoryConfigBO.findById(Long.valueOf(smallCodesId[0])).getValue());
									if(bigCodes.length>1){
										proDetails.setDetailName2(productCategoryConfigBO.findById(Long.valueOf(smallCodesId[0])).getValue());
									}
								}
								lists.add(proDetails);
							}
						}
					}
				}
			}
			if(lists.size()>0){
				productDetailsBO.batchInsert(lists);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pt;
	}

	public int saveProduct(Product product, ProductText productText, List<ProductImages> productImages,
			List<ProductExt> exts, List<ProductKeyword> keys, List<ProductParam> params) {
		int pt = saveProduct(product, productText, productImages, exts, keys);
		if (params != null && !params.isEmpty()) {
			for (ProductParam pp : params) {
				pp.setProductId(product.getId());
			}
			productParamBO.deleteByProductid(product.getId());
			productParamBO.batchInsert(params);
		}
		return pt;
	}

	public int update(Product entity) {
		return productMapper.update(entity);
	}

	/**
	 * 删除
	 */
	public int delete(Long id) {
		productCountBO.deleteByProductId(id);
		productExtBO.delete(id);
		productImagesBO.deleteByProductId(id);
		productKeywordBO.deleteByProductId(id);
		productTextBO.deleteByProductId(id);
		productParamBO.deleteByProductid(id);
		productDetailBO.deleteByProductid(id);
		productDetailCodeBO.deleteByProductid(id);
		return productMapper.delete(id);
	}

	public void deleteId(Long id) {
		productCountBO.deleteByProductId(id);
		productExtBO.delete(id);
		productImagesBO.deleteByProductId(id);
		productKeywordBO.deleteByProductId(id);
		productTextBO.deleteByProductId(id);
		productParamBO.deleteByProductid(id);
		productDetailBO.deleteByProductid(id);
		productDetailCodeBO.deleteByProductid(id);
		productMapper.delete(id);
	}

	// 删除会员发布的产品信息
	public int deleteByCreator(Long id) {
		return productMapper.deleteByCreator(id);
	}

	public List<Product> getListByCatId(Long catId) {
		return productMapper.getListByCatId(catId);
	}

	public Product findSellProductById(Long catId) {
		return productMapper.findSellProductById(catId);
	}

	public List<Product> catList(List<Long> list) {
		return productMapper.catList(list);
	}

	public List<Product> getListById(List<Long> list) {
		return productMapper.getListById(list);
	}

	public int updateStockSize(Long productId, Long buyNum) {
		return productMapper.updateStockSize(productId, buyNum);
	}

	public int updatePlusStockSize(Long productId, Long buyNum) {
		return productMapper.updatePlusStockSize(productId, buyNum);
	}

	public List<Product> getAll() {
		return productMapper.getAll();
	}

	public List<Product> getByBrandId(Long id) {
		return productMapper.getByBrandId(id);
	}

	public Page<ProductResult> pageQuery(final Map<String, Object> sqlMap) {
		return PageQueryUtils.pageQuery(sqlMap, new PageQuery<ProductResult>() {
			@Override
			public long count() {
				// TODO Auto-generated method stub
				return productMapper.countMap(sqlMap);
			}

			@Override
			public List<ProductResult> list() {
				// TODO Auto-generated method stub
				List<ProductResult> list = productMapper.list(sqlMap);
				return list;
			}
		});
	}

	/**
	 * 电动车根据tagId分页查询数据
	 * 
	 * @param sqlMap
	 * @return
	 */
	public Page<ProductResult> pageQueryByTag(final Map<String, Object> sqlMap) {
		return PageQueryUtils.pageQuery(sqlMap, new PageQuery<ProductResult>() {

			public long count() {
				return productMapper.countMapByTag(sqlMap);
			}

			public List<ProductResult> list() {
				List<ProductResult> list = productMapper.listMapByTag(sqlMap);
				return list;
			}
		});
	}

	public List<Product> getSampleProduct(BigDecimal lessPrice, BigDecimal morePrice) {
		return productMapper.getSampleProduct(lessPrice, morePrice);
	}

	public List<Product> getTeamBuyProducts() {
		return productMapper.getTeamBuyProducts();
	}

	public List<Product> getRecommendProducts() {
		return productMapper.getRecommendProducts();
	}

	public List<Product> getFittings(Map<String, Object> sqlMap) {
		return productMapper.getFittings(sqlMap);
	}

	public List<Product> getConceptCar() {
		return productMapper.getConceptCar();
	}

	public List<Product> getWillSell(Map<String, Object> sqlMap) {
		return productMapper.getWillSell(sqlMap);
	}
	
	public List<Product> distanceList(Map<String, Object> param){
		return productMapper.distanceList(param);
	}
	/**
	 * 通过shopId查询
	 * @param shopId
	 * @return
	 */
	public List<Product> findByShopId(Long shopId){
		Map<String, Object> param = Maps.newHashMapWithExpectedSize(1);
		param.put("shopId", shopId);
		return productMapper.listMap(param);
	}
	/**
	 * 通过shopCatId查询
	 * @param shopCatId
	 * @return
	 */
	public List<Product> findByShopCatId(Long shopCatId){
		Map<String, Object> param = Maps.newHashMapWithExpectedSize(1);
		param.put("shopId", shopCatId);
		return productMapper.listMap(param);
	}
	
}