package org.itboys.portal.entity;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.itboys.commons.utils.encryption.Digests;
import org.itboys.commons.utils.random.RandomUtils;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

/**
 * 通用附件表
 * @author huml
 *
 */
@Entity(value = "Attachement", noClassnameStored = true)
public class Attachement implements Serializable{

	private static final long serialVersionUID = 6196988174830928642L;
	@Id
	private Long id;
	private String objId;// 关联对象的ID 为了将来的扩展性 这里没有设计成long类型 考虑可能会存储外站附件
	private String type;// 关联对象的类型
	private String path;// 附件存储路径
	private String name;// 附件可以被人为改过的名称
	private String fileName;// 附件上传时候原名
	private String contentType;// 附件content_type
	private long size = 0l;// 附件大小
	
	public String getRelName(){
		return StringUtils.isNotBlank(name)?name:fileName;
	}

	public String getSign(){
		return Digests.md5(RandomUtils.getRandomStr(8)+id);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
}
