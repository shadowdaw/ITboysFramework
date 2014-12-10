package org.itboys.product.service.product;

import java.util.List;
import java.util.Map;

import org.itboys.framework.query.Page;
import org.itboys.framework.query.PageQuery;
import org.itboys.framework.query.PageQueryUtils;
import org.itboys.product.dao.product.ProductParamConfigMapper;
import org.itboys.product.dto.product.ParamValue;
import org.itboys.product.entity.mysql.product.ProductParamConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品分类的默认参数code的配置
 * 
 * @author huml
 */
@Service
public class ProductParamConfigBO {

	@Autowired
	private ProductParamConfigMapper productParamConfigMapper;
	
	/**
	 * 分页
	 * @param catId
	 * @return
	 */
	public Page<ProductParamConfig> pageQuery(Page<ProductParamConfig> page, final Map<String, Object> sqlMap){
		return PageQueryUtils.pageQuery(page, sqlMap, new PageQuery<ProductParamConfig>() {
			@Override
			public List<ProductParamConfig> list(){
				List<ProductParamConfig> list = productParamConfigMapper.list(sqlMap);
				return list;
			}
			@Override
			public long count(){
				return productParamConfigMapper.count(sqlMap);
			}
		});
	}

	public List<ProductParamConfig> getListByCatid(Long catId) {
		return productParamConfigMapper.getListByCatid(catId);
	}

	public List<ProductParamConfig> list(Map<String, Object> map) {
		return productParamConfigMapper.list(map);
	}

	/**
	 * 根据id查询
	 * 
	 * @param id
	 */
	public ProductParamConfig findById(Long id) {
		return productParamConfigMapper.findById(id);
	}

	/**
	 * 插入操作
	 * 
	 * @param entity
	 */
	public void insert(ProductParamConfig entity) {
		productParamConfigMapper.insert(entity);
	}

	/**
	 * 更新操作
	 * 
	 * @param entity
	 * @return
	 */
	public void update(ProductParamConfig entity) {
		productParamConfigMapper.update(entity);
	}

	/**
	 * 删除
	 */
	public int delete(Long id) {
		return productParamConfigMapper.delete(id);
	}

	public int deleteByCatId(Long catId) {
		return productParamConfigMapper.deleteByCatId(catId);
	}

	/**
	 * 根据productid查询出productparam 中的productparamConfigId
	 * 然后查询出productparamcoNFIG中的值
	 * 
	 * @param productId
	 * @return
	 */
	public List<ProductParamConfig> getListByProductId(Long productId) {
		return productParamConfigMapper.getListByProductId(productId);
	}

	public List<ParamValue> findParamAndValue(Long productId) {
		return productParamConfigMapper.findParamAndValue(productId);
	}

}
