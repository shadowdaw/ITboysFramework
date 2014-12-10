package org.itboys.product.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.itboys.commons.utils.collection.CommonCollectionUtils;
import org.itboys.commons.utils.collection.FetchCondition;
import org.itboys.framework.query.Page;
import org.itboys.framework.query.PageQuery;
import org.itboys.framework.query.PageQueryUtils;
import org.itboys.product.dao.product.ProductCategoryMapper;
import org.itboys.product.dao.product.ProductParamConfigMapper;
import org.itboys.product.dto.product.ProductCategoryDTO;
import org.itboys.product.entity.mysql.product.ProductCategory;
import org.itboys.product.tools.LoginHolder;
import org.itboys.product.tools.WebConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 产品分类管理业务对象
 * @author huml
 */
@Service
public class ProductCategoryBO {
	
	public static final String PATH_SPLIT = "/";

	@Autowired
	private ProductCategoryMapper productCategoryMapper;
	@Autowired
	private ProductParamConfigMapper productParamConfigMapper;
	
	/**
	 * 获取全部菜单
	 * @return
	 */
	public List<ProductCategory> getAllCategorys(Long projectId){
		Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		queryParam.put("projectId", projectId);
		return productCategoryMapper.list(queryParam);
	}
	
	/**
	 * 获取所有菜单已经匹配与品牌相关的数据列表
	 * @param brandId
	 * @return
	 */
	public List<ProductCategoryDTO> getBrandProductCategoryList(Long brandId){
		Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		queryParam.put("brandId", brandId);
		return productCategoryMapper.getBrandProductCategoryList(queryParam);
	}
	
	public List<ProductCategory>getByAccountId(Long shopkeeperAccountId){
		return productCategoryMapper.getByAccountId(shopkeeperAccountId);
	}
	
	/**
	 * 根据条件查询
	 * @return
	 */
	public List<ProductCategory> list(Map<String, Object> map){
		return productCategoryMapper.list(map);
	}
	
	/**
	 * 获取所有一级类目菜单
	 * @return
	 */
	public List<ProductCategory> getRootCategorys(Long parentId){
		Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		queryParam.put("parentId", 0l);
		List<ProductCategory> list = productCategoryMapper.list(queryParam);
		return list;
	}
	
	/**
	 * 获取所有一级类目菜单
	 * @return
	 */
	public List<ProductCategory> getRootCategorys(List<ProductCategory> all){
		List<ProductCategory> list = CommonCollectionUtils.filterCollection(all, new FetchCondition<ProductCategory>() {
			@Override
			public boolean needFetch(ProductCategory t) {
				return t.getParentId()==null || t.getParentId().equals(0L);
			}
		});
		return list;
	}
	
	/**
	 * 获取某个实例的所有子类列表
	 */
	public List<ProductCategory>getChildList(Long pId,List<ProductCategory>allList){
		if(pId != null && allList != null){
			List<ProductCategory> childList = new ArrayList<ProductCategory>();//这里如何设置初始大小?
			for(ProductCategory productCategory : allList){
				if(pId == productCategory.getParentId()){
					childList.add(productCategory);
				}
			}
			return childList;
		}
		return null;
	}
	
	/**
	 * 按list的顺序取出一个分类的祖先到自己的 集合
	 * @param catId
	 * @return
	 */
	public List<ProductCategory> getFullPath(Long catId){
		ProductCategory category = getProductCategory(catId);
		if(category==null){
			return Lists.newArrayListWithCapacity(0);
		}else if(category.getParentId().equals(0L)){
			List<ProductCategory> list = Lists.newArrayListWithCapacity(1);
			list.add(category);
			return list;
		}
		String fullIdPath = category.getFullIdPath();
		String[] ids = StringUtils.split(fullIdPath, PATH_SPLIT);
		List<ProductCategory> list = Lists.newArrayListWithCapacity(ids.length);
		for(String id:ids){
			if(NumberUtils.isDigits(id)){
				list.add(getProductCategory(Long.parseLong(id)));
			}
		}
		return list;
	}
	
	/**
	 * 根据ID加载菜单对象
	 * @param id
	 * @return
	 */
	public ProductCategory getProductCategory(Long id){
		return productCategoryMapper.findById(id);
	}
	
	/**
	 * 根据ID加载菜单对象
	 * @param id
	 * @return
	 */
	public List<ProductCategory> getChilidsByParentid(Long parentId){
		Map<String,Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		queryParam.put("parentId", parentId);
		List<ProductCategory> list = productCategoryMapper.list(queryParam);
		return list;
	}
	
	/*
	 * 添加产品分类
	 */
	public void insert(ProductCategory entity){
		if(entity.getParentId()==null){
			entity.setParentId(0L);
		}
		LoginHolder.prepareLoginMessage(entity, WebConstants.ALL_USER_SESSIONKEY);
		if(entity.getParentId()==0L){
			//entity.setFullIdPath(StringUtils.EMPTY);
			entity.setLevel(1);
			entity.setFullIdPath(PATH_SPLIT+entity.getId()+PATH_SPLIT);
			productCategoryMapper.insert(entity);
			entity.setFullIdPath(PATH_SPLIT+entity.getId()+PATH_SPLIT);
			productCategoryMapper.update(entity);
		}else{
			ProductCategory parent = getProductCategory(entity.getParentId());
			entity.setLevel(parent.getLevel()+1);
			entity.setFullIdPath(parent.getFullIdPath()+entity.getId()+PATH_SPLIT);
			productCategoryMapper.insert(entity);
			entity.setFullIdPath(parent.getFullIdPath()+entity.getId()+PATH_SPLIT);
			productCategoryMapper.update(entity);
		}
	}

	
	/*
	 * 修改产品分类
	 */
	public int update(ProductCategory entity){
		LoginHolder.prepareLoginMessage(entity,WebConstants.ALL_USER_SESSIONKEY);//组装当前登入者信息
		return productCategoryMapper.update(entity);
	}
	
	/*
	 * 删除产品分类
	 */
	public int delete(Long id){
		productParamConfigMapper.deleteByCatId(id);
		int result = productCategoryMapper.delete(id);
		return result;
	}
	/**
	 * 获取所有的一级分类
	 * @return
	 */
	public List<ProductCategory> getBasicSort(){
		return productCategoryMapper.getBasicSort();
	}
	
	public List<ProductCategory>getAll(){
		return productCategoryMapper.getAll();
	}
	
	public Page<ProductCategory> pageQuery(final Page<ProductCategory> page, final Map<String, Object> sqlMap) {
		return PageQueryUtils.pageQuery(page, sqlMap, new PageQuery<ProductCategory>() {
			@Override
			public long count() {
				return productCategoryMapper.count(sqlMap);
			}

			@Override
			public List<ProductCategory> list() {
				List<ProductCategory> list = productCategoryMapper.list(sqlMap);
				return list;
			}
		});
	}
	
	public List<ProductCategory>getListByIds(List<Long> ids){
		return productCategoryMapper.getListByIds(ids);
	}
	
}
