package org.itboys.product.dao.product;

import java.util.List;
import java.util.Map;

import org.itboys.product.dto.product.ProductCategoryDTO;
import org.itboys.product.entity.mysql.product.ProductCategory;

/**
 * 菜单管理DAO
 * @author huml
 *
 */
public interface ProductCategoryMapper {

	/**
	 * 根据查询条件查询产品分类
	 * 查询动态条件支持如下key
	 * id:主键
	 * name:分类名称 全模糊匹配
	 * parentId:父分类ID
	 * level:层级
	 * 排序条件 最后sql加上 动态排序条件会统一封装
	   <if test="orderByKey != null and orderByKey != ''">
			<![CDATA[ order by ${orderByKey} ]]>
		</if>
		别忘了加上 is_deleted=1
		不用分页
	 * @return
	 */
	public List<ProductCategory> list(Map<String,Object> queryParam);
	
	public int count(long pid);
	
	public List<ProductCategory>getByAccountId(Long shopkeeperAccountId);
	
	public List<ProductCategoryDTO> getBrandProductCategoryList(Map<String,Object> queryParam);
	
	/**
	 * 根据id查询
	 * @param id
	 */
	public ProductCategory findById(Long id);
	
	/**
	 * 插入操作
	 * @param entity
	 */
	public void insert(ProductCategory entity);
	
	/**
	 * 更新操作
	 * @param entity
	 * @return
	 */
	public int update(ProductCategory entity);
	
	/*
	 * 删除菜单
	 */
	public int delete(Long id);
	/**
	 * 获取所有的一级分类
	 * @return
	 */
	public List<ProductCategory> getBasicSort();
	
	public List<ProductCategory>getAll();
	
	public long count(Map<String,Object> queryParam);
	
	public List<ProductCategory>getListByIds(List<Long> list);
}
