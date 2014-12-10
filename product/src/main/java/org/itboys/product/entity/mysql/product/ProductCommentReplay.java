package org.itboys.product.entity.mysql.product;

import java.util.Date;

import org.itboys.product.entity.mysql.BaseEntity;
/**
 * 评论回复
 * @author huml
 *
 */
public class ProductCommentReplay extends BaseEntity {

	private static final long serialVersionUID = 8916576516465L;
	
	private Long id;
	private Long productId;
	private Long commentId;
	private Integer type;//树形回复的时候,如果是1,说明是评价人在回复
	private Long parentId;//第一次回复为0,否则为树形回复的父id
	private Integer status;//状态:1.审核通过 2.删除 0待审核
	private Integer containsForbiddenWords;//1.含有违禁词  0无
	private Date replayTime;
	private Long replayer;//如果type=1 then 0 else 回复用户的ID 可能是后台管理用户 也可能是前台商家 业务含义具体定
	private String content;
	private String tag;//扩展标签
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getContainsForbiddenWords() {
		return containsForbiddenWords;
	}
	public void setContainsForbiddenWords(Integer containsForbiddenWords) {
		this.containsForbiddenWords = containsForbiddenWords;
	}
	public Date getReplayTime() {
		return replayTime;
	}
	public void setReplayTime(Date replayTime) {
		this.replayTime = replayTime;
	}
	public Long getReplayer() {
		return replayer;
	}
	public void setReplayer(Long replayer) {
		this.replayer = replayer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}

}
