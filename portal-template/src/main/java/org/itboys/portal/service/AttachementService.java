package org.itboys.portal.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.itboys.commons.utils.io.FileUtils;
import org.itboys.framework.resource.ResourceConfig;
import org.itboys.framework.resource.ResourceHolder;
import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.service.BaseService;
import org.itboys.portal.entity.Attachement;
import org.itboys.portal.tools.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
/**
 * 附件
 * @author huml
 *
 */
@Service
public class AttachementService extends BaseService<Attachement, Long> {

	private static final long serialVersionUID = 4676462143789876595L;
	@Autowired
	private ResourceHolder resourceHolder;
	@Resource(name="portalDS")
	private MongoDataSource attachementDataSource;
	@Override
	protected MongoDataSource getMongoDataSource() {
		return attachementDataSource;
	}

	@Override
	protected Class<Attachement> getEntityClass() {
		return Attachement.class;
	}
	
	/**
	 * 根据关联的ID 和关联对象类型查找其附件
	 * @param objId
	 * @param type
	 * @return
	 */
	public List<Attachement> findBy(String objId,String type){
		return attachementDataSource.createQuery(getEntityClass()).filter("objId", objId).
				filter("type", type).asList();
	}
	
	/**
	 * 根据关联的ID 和关联对象类型查找其附件
	 * @param objId
	 * @param type
	 * @return
	 */
	public List<Attachement> findBy(Long objId,String type){
		return attachementDataSource.createQuery(getEntityClass()).filter("objId", objId).
				filter("type", type).asList();
	}
	
	public Attachement findAttachementBy(String objId,String type){
		List<Attachement> list = attachementDataSource.createQuery(getEntityClass()).filter("objId", objId).
				filter("type", type).asList();
		return list.size()>0?list.get(0):null;
	}
	
	public Attachement findAttachementBy(Long objId,String type){
		return this.findAttachementBy(objId.toString(), type);
	}
	
	
	/**
	 * 根据关联的ID 和关联对象类型删除其所有附件
	 * @param objId
	 * @param type
	 */
	public void deleteAll(String objId,String type){
		List<Attachement> list = findBy(objId, type);
		for(Attachement attachement : list){
			update(attachement.getId(), "isDeleted", 1);
		}
	}
	
	/**
	 * 根据ID删除
	 * @param id
	 */
	public void delete(long id){
		update(id, "isDeleted", 1);
		Attachement a = getById(id);
		if(a!=null){
			FileManager.deleteFile(a.getPath());
		}
	}
	
	/**
	 * 上传并组装附件信息
	 * @param name
	 * @param fileItem
	 * @return
	 */
	public Attachement prepareAttachement(String name,MultipartFile fileItem){
		try{
			if(fileItem==null || fileItem.getSize()<=0L){
				return null;
			}
			Attachement att = new Attachement();
			att.setName(name);
			att.setContentType(fileItem.getContentType());
			att.setFileName(fileItem.getOriginalFilename());
			att.setSize(fileItem.getSize());
			String filePath = FileUtils.saveFile(fileItem.getInputStream(), resourceHolder.getStringValue("fileUploadPath"), fileItem.getOriginalFilename(), fileItem.getContentType());
			att.setPath(filePath);
			return att;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 上传并组装附件信息
	 * @param fileItem
	 * @return
	 */
	public Attachement prepareAttachement(MultipartFile fileItem){
		return prepareAttachement(StringUtils.EMPTY, fileItem);
	}
	
	/**
	 * 上传并组装附件信息
	 * @param name
	 * @param fileItem
	 * @return
	 */
	public List<Attachement>  prepareAttachement(String[] name,MultipartFile[] files){
		List<Attachement> attahs = new ArrayList<Attachement>(files.length);
		int i=0;
		for(MultipartFile cmf:files){
			if(!cmf.isEmpty()){
				attahs.add(prepareAttachement(name[i], cmf));
			}
			i++;
		}
		return attahs;
	}
	
	/**
	 * 上传并组装附件信息
	 * @param name
	 * @param fileItem
	 * @return
	 */
	public List<Attachement>  prepareAttachement(MultipartFile... files){
		List<Attachement> attahs = new ArrayList<Attachement>(files.length);
		for(MultipartFile cmf:files){
			if(!cmf.isEmpty()){
				attahs.add(prepareAttachement(StringUtils.EMPTY, cmf));
			}
		}
		return attahs;
	}
}
