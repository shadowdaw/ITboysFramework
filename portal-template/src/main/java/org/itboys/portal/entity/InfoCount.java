package org.itboys.portal.entity;

import java.io.Serializable;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@Entity(value = "InfoCount", noClassnameStored = true)
public class InfoCount implements Serializable{

	private static final long serialVersionUID = -3404641989949565092L;
	@Id
	private long id;
	
	private Long infoId;
	private Integer viewCount;// 浏览次数
	private Integer collectCount;// 收藏次数
	private Integer goodCount;// 被赞或被顶的次数
	private Integer count1;
	private Integer count2;
	private Integer count3;
	private Integer count4;
	private Integer count5;
	private Integer count6;
	private Integer count7;
	private Integer count8;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getInfoId() {
		return infoId;
	}

	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public Integer getCollectCount() {
		return collectCount;
	}

	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}

	public Integer getGoodCount() {
		return goodCount;
	}

	public void setGoodCount(Integer goodCount) {
		this.goodCount = goodCount;
	}

	public Integer getCount1() {
		return count1;
	}

	public void setCount1(Integer count1) {
		this.count1 = count1;
	}

	public Integer getCount2() {
		return count2;
	}

	public void setCount2(Integer count2) {
		this.count2 = count2;
	}

	public Integer getCount3() {
		return count3;
	}

	public void setCount3(Integer count3) {
		this.count3 = count3;
	}

	public Integer getCount4() {
		return count4;
	}

	public void setCount4(Integer count4) {
		this.count4 = count4;
	}

	public Integer getCount5() {
		return count5;
	}

	public void setCount5(Integer count5) {
		this.count5 = count5;
	}

	public Integer getCount6() {
		return count6;
	}

	public void setCount6(Integer count6) {
		this.count6 = count6;
	}

	public Integer getCount7() {
		return count7;
	}

	public void setCount7(Integer count7) {
		this.count7 = count7;
	}

	public Integer getCount8() {
		return count8;
	}

	public void setCount8(Integer count8) {
		this.count8 = count8;
	}
	
	
}
