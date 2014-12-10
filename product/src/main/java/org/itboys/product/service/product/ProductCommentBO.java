package org.itboys.product.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.itboys.framework.query.Page;
import org.itboys.framework.query.PageQuery;
import org.itboys.framework.query.PageQueryUtils;
import org.itboys.product.dao.product.ProductCommentMapper;
import org.itboys.product.entity.mysql.product.ProductComment;
import org.itboys.webdata.SessionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

/**
 * 商品评论业务逻辑层
 * 
 * @author huml
 * 
 */
@Service
public class ProductCommentBO {
	@Autowired
	private ProductCommentMapper productCommentMapper;

	/**
	 * 这个分页sql条件符合于某些项目 count 与list的where 条件不一样
	 * 
	 * @param page
	 * @param sqlMap
	 * @return
	 */
	public Page<ProductComment> pageQuery(Page<ProductComment> page, final Map<String, Object> sqlMap) {
		return PageQueryUtils.pageQuery(page, sqlMap, new PageQuery<ProductComment>() {
			@Override
			public List<ProductComment> list() {
				List<ProductComment> list = productCommentMapper.list(sqlMap);
				return list;
			}

			@Override
			public long count() {
				return productCommentMapper.count(sqlMap);
			}
		});
	}

	/**
	 * 计算加积分的评论数量
	 * 
	 * @param sqlMap
	 * @return
	 */
	public long countForScore(Map<String, Object> sqlMap) {
		return productCommentMapper.countForScore(sqlMap);
	}

	public List<ProductComment> findByProductId(Long productId) {
		Map<String, Object> map = Maps.newHashMapWithExpectedSize(1);
		map.put("productId", productId);
		return productCommentMapper.list(map);
	}

	// 批量插入商品评论
	public void batchInsert(Long[] productId, Integer[] score, String[] advantage, String[] disadvantage,
			String[] experience) {
		List<ProductComment> pros = new ArrayList<ProductComment>();
		for (int i = 0; i < productId.length; i++) {
			ProductComment pComment = new ProductComment();
			pComment.setProductId(productId[i]);
			pComment.setAdvantage(advantage[i]);
			pComment.setDisadvantage(disadvantage[i]);
			pComment.setExperience(experience[i]);
			pComment.setContent("");
			pComment.setContainsForbiddenWords(0);
			pComment.setScore(score[i]);
			pComment.setUserId(SessionHolder.getMemberId());
			pros.add(pComment);
		}
		productCommentMapper.batchInsert(pros);
	}

	/**
	 * 添加商品评论信息
	 * 
	 * @param entity
	 * @return
	 */
	public int insert(ProductComment entity) {
		return productCommentMapper.insert(entity);
	}

	/**
	 * 删除某个评论
	 * 
	 * @param id
	 * @return
	 */
	public int delete(Long id) {
		return productCommentMapper.delete(id);
	}

	/**
	 * 获取单条评论
	 * 
	 * @param id
	 * @return
	 */
	public ProductComment findById(Long id) {
		return productCommentMapper.findById(id);
	}

	/**
	 * 修改即审核评论信息
	 * 
	 * @param entity
	 * @return
	 */
	public int update(ProductComment entity) {
		return productCommentMapper.update(entity);
	}

	public Page<ProductComment> pageQuerys(Page<ProductComment> page, final Map<String, Object> sqlMap) {
		return PageQueryUtils.pageQuery(page, sqlMap, new PageQuery<ProductComment>() {
			@Override
			public List<ProductComment> list() {
				List<ProductComment> list = productCommentMapper.list2(sqlMap);
				return list;
			}

			@Override
			public long count() {
				return productCommentMapper.count2(sqlMap);
			}
		});
	}
}
