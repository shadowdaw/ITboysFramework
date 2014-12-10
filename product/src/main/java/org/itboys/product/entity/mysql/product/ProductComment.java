package org.itboys.product.entity.mysql.product;

import java.util.Date;

/**
 * 商品评论
 * @author WangRan
 *
 */
public class ProductComment {
	private Long id;
	private Long productId;
	private String title;			 	//评论标题
	private String content;				//评论内容
	private Integer score;				//最终系统根据各项数据算出的总分
	private Long userId;				//评论ID
	private Integer status;				//评论审核状态1 审核通过,0 待审核 2 删除
	private Integer containsForbiddenWords;//是否含有敏感词汇1:含有违禁词 0:不含有
	private Date createTime;			//评论创建时间
	private Date replayTime;			//回复时间
	private Integer replayer;			//回复人员ID
	private Integer score1;
	private Integer score2;
	private Integer score3;
	private Integer score4;
	private Integer score5;
	private Integer score6;
	private String advantage;			//优点
	private String disadvantage;		//缺点
	private String experience;			//使用心得
	private String fied1;				//预留字段方便以后
	private String fied2;		
	private String fied3;	
	private String fied4;	
	private String fied5;	
	private Integer isDeleted;			//是否删除
	
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getReplayTime() {
		return replayTime;
	}
	public void setReplayTime(Date replayTime) {
		this.replayTime = replayTime;
	}
	public Integer getReplayer() {
		return replayer;
	}
	public void setReplayer(Integer replayer) {
		this.replayer = replayer;
	}
	public Integer getScore1() {
		return score1;
	}
	public void setScore1(Integer score1) {
		this.score1 = score1;
	}
	public Integer getScore2() {
		return score2;
	}
	public void setScore2(Integer score2) {
		this.score2 = score2;
	}
	public Integer getScore3() {
		return score3;
	}
	public void setScore3(Integer score3) {
		this.score3 = score3;
	}
	public Integer getScore4() {
		return score4;
	}
	public void setScore4(Integer score4) {
		this.score4 = score4;
	}
	public Integer getScore5() {
		return score5;
	}
	public void setScore5(Integer score5) {
		this.score5 = score5;
	}
	public Integer getScore6() {
		return score6;
	}
	public void setScore6(Integer score6) {
		this.score6 = score6;
	}
	public String getAdvantage() {
		return advantage;
	}
	public void setAdvantage(String advantage) {
		this.advantage = advantage;
	}
	public String getDisadvantage() {
		return disadvantage;
	}
	public void setDisadvantage(String disadvantage) {
		this.disadvantage = disadvantage;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getFied1() {
		return fied1;
	}
	public void setFied1(String fied1) {
		this.fied1 = fied1;
	}
	public String getFied2() {
		return fied2;
	}
	public void setFied2(String fied2) {
		this.fied2 = fied2;
	}
	public String getFied3() {
		return fied3;
	}
	public void setFied3(String fied3) {
		this.fied3 = fied3;
	}
	public String getFied4() {
		return fied4;
	}
	public void setFied4(String fied4) {
		this.fied4 = fied4;
	}
	public String getFied5() {
		return fied5;
	}
	public void setFied5(String fied5) {
		this.fied5 = fied5;
	}
}