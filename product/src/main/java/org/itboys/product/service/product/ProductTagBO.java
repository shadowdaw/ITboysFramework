package org.itboys.product.service.product;

import java.util.List;
import java.util.Map;

import org.itboys.framework.query.Page;
import org.itboys.framework.query.PageQuery;
import org.itboys.framework.query.PageQueryUtils;
import org.itboys.product.dao.product.ProductTagMapper;
import org.itboys.product.entity.mysql.product.ProductTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

/**
 * 商品标签BO
 * @author huml
 *
 */
@Service
public class ProductTagBO {
	
	@Autowired
	private ProductTagMapper productTagMapper;
	
	public Page<ProductTag> pageQuery(Page<ProductTag> page, final Map<String, Object> sqlMap){
		return PageQueryUtils.pageQuery(page, sqlMap, new PageQuery<ProductTag>() {
			@Override
			public List<ProductTag> list(){
				List<ProductTag> list = productTagMapper.list(sqlMap);
				return list;
			}
			@Override
			public long count(){
				return productTagMapper.count(sqlMap);
			}
		});
	}
	
	public ProductTag findById(Long id){
		Map<String, Object> sqlMap = Maps.newHashMapWithExpectedSize(1);
		sqlMap.put("id", id);
		List<ProductTag> modules = productTagMapper.list(sqlMap);
		return modules.isEmpty()?null:modules.get(0);
	}
	
	public void insert(ProductTag adminModule){
		productTagMapper.insert(adminModule);
	}
	
	public int update(ProductTag adminModule){
		return productTagMapper.update(adminModule);
	}
	
	public int delete(Long id){
		return productTagMapper.delete(id);
	}

	public List<ProductTag> list(Map<String, Object> queryParam) {
		return productTagMapper.list(queryParam);
	}
	
}
