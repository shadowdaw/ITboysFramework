package org.itboys.product.service.product;

import java.util.List;
import java.util.Map;

import org.itboys.product.dao.product.ProductCommentReplayMapper;
import org.itboys.product.entity.mysql.product.ProductCommentReplay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCommentReplayBO {
	@Autowired
	private ProductCommentReplayMapper productCommentReplayMapper;
	
	public List<ProductCommentReplay> list(Map<String, Object> param){
		return productCommentReplayMapper.list(param);
	}
	public Long count(Map<String, Object> param){
		return productCommentReplayMapper.count(param);
	}
	
	public void insert(ProductCommentReplay productCommentReplay){
		productCommentReplayMapper.insert(productCommentReplay);
	}
	public void update(ProductCommentReplay productCommentReplay){
		productCommentReplayMapper.update(productCommentReplay);
	}
	public void delete(Long id){
		productCommentReplayMapper.delete(id);
	}
	
	public List<ProductCommentReplay> findByCommentId(Long commentId){
		return productCommentReplayMapper.findByCommentId(commentId);
	}
	public List<ProductCommentReplay> findByParentId(Long parentId){
		return productCommentReplayMapper.findByParentId(parentId);
	}
	
	public ProductCommentReplay findById(Long id){
		return productCommentReplayMapper.findById(id);
	}
}
