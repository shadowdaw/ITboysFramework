package org.itboys.product.dao.product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.itboys.product.dto.product.ProductCountDTO;
import org.itboys.product.dto.product.ProductResult;
import org.itboys.product.entity.mysql.product.Product;

/**
 * 商品管理DAO
 * @author huml
 *
 */
public interface ProductMapper {

	/**
	 * 根据查询条件查询产品
		别忘了加上 is_deleted=1
		用分页
	 * @return
	 */
	public List<ProductResult> list(Map<String,Object> queryParam);
	
	public List<ProductCountDTO> listCount(Map<String,Object> queryParam);
	
	public long count(Map<String,Object> queryParam);
	
	public long countCount(Map<String,Object> queryParam);
	
	/**
	 * 根据id查询
	 * @param id
	 */
	public ProductResult findById(Long id);
	
	/**
	 * 根据id查询
	 * @param id
	 */
	public Product findProductById(Long id);
	/**
	 * 查询正在销售的商品的信息
	 * @param id
	 * @return
	 */
	public Product findSellProductById(Long id);
	
	/**
	 * 插入操作
	 * @param entity
	 */
	public int insert(Product entity);
	
	/**
	 * 更新操作
	 * @param entity
	 * @return
	 */
	public int update(Product entity);
	
	
	/**
	 * 删除
	 */
	public int delete(Long id);
	
	/**
	 * 得到采购信息关联采购人信息
	 */
	public List<Product> findCaigo();
	
	
	/***********
	 * 随机查询5条产品信息
	 * @param catId
	 * @param id
	 * @return
	 */
	public List<Product> getRand5Product(@Param("catId")Long catId,@Param("id")Long id);


	public int deleteByCreator(Long id);
	
	public List<Product>getListByCatId(Long catId);
	
	public List<Product>catList(List<Long>list);
	
	public List<Product>getListById(List<Long>list);
	
	public int updateStockSize(@Param("productId")Long productId,@Param("buyNum")Long buyNum);

	public int updatePlusStockSize(@Param("productId")Long productId,@Param("buyNum")Long buyNum);

	public List<Product>getAll();
	
	public List<Product> getByBrandId(Long id);
	
	public long countMap(Map<String,Object> queryParam);
	
	public List<Product> listMap(Map<String,Object> queryParam);
	
	public List<Product> getSampleProduct(@Param("lessPrice")BigDecimal lessPrice,@Param("morePrice")BigDecimal morePrice);
	
	public List<Product> getTeamBuyProducts();
	
	public List<Product> getRecommendProducts();
	
	public List<Product> getFittings(Map<String,Object> sqlMap);
	 
	public List<Product> getConceptCar();
	
	public List<Product> getWillSell(Map<String,Object> sqlMap);

	public List<ProductResult> listMapByTag(Map<String, Object> sqlMap);

	public long countMapByTag(Map<String, Object> sqlMap);

	public List<Product> findRecommend();

	public List<Product> findHotProduct(Map<String, Object> sqlMap);  
	
	public List<Product> distanceList(Map<String, Object> param);
}
