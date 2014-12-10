package org.itboys.portal.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.commons.utils.collection.CommonCollectionUtils;
import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.utils.page.Page;
import org.itboys.mongodb.utils.query.PageQueryUtils;
import org.itboys.portal.dto.PortalInfoDTO;
import org.itboys.portal.entity.Attachement;
import org.itboys.portal.entity.InfoCount;
import org.itboys.portal.entity.InfoObjRel;
import org.itboys.portal.entity.PortalInfo;
import org.itboys.portal.entity.PortalItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service
public class PortalInfoService extends BasePortalService<PortalInfo, Long> {

	private static final long serialVersionUID = -9210373059727376945L;
	@Autowired
	private AttachementService attachementService;
	@Autowired
	private InfoCountService infoCountService;
	@Autowired
	private InfoObjRelService infoObjRelService;
	@Autowired
	private PortalItemService portalItemService;
	@Resource(name="portalDS")
	private MongoDataSource infoDataSource;
	@Override
	protected MongoDataSource getMongoDataSource() {
		return infoDataSource;
	}

	@Override
	protected Class<PortalInfo> getEntityClass() {
		return PortalInfo.class;
	}
	
	public void insert(PortalInfo portalInfo,List<Attachement> images,List<Attachement> attachs) {
		AdminSessionHolder.prepareAdminLoginData(portalInfo);
		save(portalInfo);
		InfoCount infoCount = new InfoCount();
		infoCount.setInfoId(portalInfo.getId());
		infoCountService.save(infoCount);
		prepareInfoAttachements(portalInfo.getId(), images,attachs);
	}

	/**
	 * 组装信息附件相关数据
	 * @param infoid
	 * @param images
	 * @param attachs
	 */
	private void prepareInfoAttachements(Long infoid,List<Attachement> images,List<Attachement> attachs) {
		if(images!=null && !images.isEmpty()){
			for(Attachement img:images){
				img.setObjId(infoid.toString());
				img.setType(PortalInfo.ATTACHEMENT_TYPE_IMAGE);
			}
			attachementService.batchSave(images);
		}
		
		if(attachs!=null && !attachs.isEmpty()){
			for(Attachement att:attachs){
				att.setObjId(infoid.toString());
				att.setType(PortalInfo.ATTACHEMENT_TYPE_ATTACH);
			}
			attachementService.batchSave(attachs);
		}
	}

	public void update(PortalInfo portalInfo,List<Attachement> images,List<Attachement> attachs) {
		AdminSessionHolder.prepareAdminLoginData(portalInfo);
		update(portalInfo);
		prepareInfoAttachements(portalInfo.getId(), images,attachs);
	}
	
	public void update(PortalInfo info){
		PortalInfo portalInfo = getById(info.getId());
		info.setCreateTime(portalInfo.getCreateTime());
		info.setCreator(portalInfo.getCreator());
		info.setUpdater(AdminSessionHolder.getAdminUserId());
		info.setUpdateTime(new Date());
		info.setPublicUser(portalInfo.getPublicUser());
		super.update(info);
	}

	/**
	 *  获取某个栏目下的第一条信息
	 * @param itemId
	 * @return
	 */
	public PortalInfo findInfoByItemId(Long projectItemId){
		List<PortalInfo> infos = findInfosByItemId(projectItemId);
		return CommonCollectionUtils.isNotEmpty(infos)?infos.get(0):null;
	}
	
	/**
	 * 获取某个栏目下的第一条信息 附带附件信息
	 * @param projectItemId
	 * @param attachementType
	 * @return
	 */
	public PortalInfoDTO findInfoByItemId(Long projectItemId,String... attachementType){
		List<PortalInfo> infos = findInfosByItemId(projectItemId);
		PortalInfo info = CommonCollectionUtils.isNotEmpty(infos)?infos.get(0):null;
		PortalInfoDTO dto = new PortalInfoDTO();
		filled(dto, info);
		prepareAttachements(dto,info, attachementType);
		return dto;
	}
	
	/**
	 * 加载多条信息
	 * @param itemId
	 * @return
	 */
	public List<PortalInfo> findInfosByItemId(Long itemId){
		Map<String, Object> sqlMap = Maps.newHashMapWithExpectedSize(2);
		sqlMap.put("itemId", itemId);
		sqlMap.put("status", PortalInfo.YES_STATUS);
		List<PortalInfo> infos = list(sqlMap);
		return infos;
	}
	
	/**
	 * 加载多条信息
	 * @param itemId
	 * @return
	 */
	public List<PortalInfo> findByItemId(Long itemId){
		List<PortalInfo> infos =findByField("itemId", itemId);
		return infos;
	}

	
	public PortalInfoDTO findById(Long id,String... attachementType){
		PortalInfoDTO dto = new PortalInfoDTO();
		PortalInfo info = getById(id);
		filled(dto, info);
		prepareAttachements(dto ,info, attachementType);
		return dto;
	}

	/**
	 * 组装附件信息
	 * @param id
	 * @param info
	 * @param attachementType
	 */
	private void prepareAttachements(PortalInfoDTO dto,PortalInfo info,String... attachementType) {
		if(dto!=null && attachementType!=null && attachementType.length>0){
			for(String atype:attachementType){
				if(StringUtils.equals(atype, PortalInfo.ATTACHEMENT_TYPE_IMAGE)){
					dto.setImages(this.attachementService.findBy(dto.getId(), PortalInfo.ATTACHEMENT_TYPE_IMAGE));
				}else if(StringUtils.equals(atype, PortalInfo.ATTACHEMENT_TYPE_ATTACH)){
					dto.setAttachs(this.attachementService.findBy(dto.getId(), PortalInfo.ATTACHEMENT_TYPE_ATTACH));
				}
			}
		}
	}
	/**
	 * 装载图片及附件信息的分页查询
	 * @param request
	 * @return
	 */
	public Page<PortalInfoDTO> pageQueryAll(HttpServletRequest request){
		Page<PortalInfo> page = pageQuery(request);
		List<PortalInfoDTO> list = Lists.newArrayList();
		for(PortalInfo info : page.getData()){
			PortalInfoDTO dto = new PortalInfoDTO();
			filled(dto, info);
			dto.setImages(this.attachementService.findBy(dto.getId(), PortalInfo.ATTACHEMENT_TYPE_IMAGE));
			dto.setAttachs(this.attachementService.findBy(dto.getId(), PortalInfo.ATTACHEMENT_TYPE_ATTACH));
			list.add(dto);
		}
		return new Page<PortalInfoDTO>(page.getTotal(), list) ;
	}
	
	public List<PortalInfoDTO> listAll(Map<String, Object> sqlMap){
		List<PortalInfo> list = list(sqlMap);
		List<PortalInfoDTO> dtos = Lists.newArrayListWithCapacity(list.size());
		for(PortalInfo info:list){
			PortalInfoDTO dto = new PortalInfoDTO();
			filled(dto, info);
			dto.setImages(attachementService.findBy(info.getId(), PortalInfo.ATTACHEMENT_TYPE_IMAGE));
			dto.setAttachs(attachementService.findBy(info.getId(), PortalInfo.ATTACHEMENT_TYPE_ATTACH));
			dtos.add(dto);
		}
		return dtos;
	}
	
	/*//根据信息浏览量进行排序
	public List<PortalInfo> findOrderByViewCount(Map<String, Object> sqlMap){
		sqlMap.put(PageQueryUtils.SORT_FIELD, "view");
		return portalInfoMapper.findOrderByViewCount(sqlMap);
	}
		
	//根据信息浏览量排序的分页方法
	public Page<PortalInfo> pageQueryByViewcount(Page<PortalInfo> page,final Map<String, Object> sqlMap){
		return PageQueryUtils.pageQuery(page,sqlMap, new PageQuery<PortalInfo>() {
			public List<PortalInfo> list() {
				return portalInfoMapper.findOrderByViewCount(sqlMap);
			}
			
			public long count() {
				return portalInfoMapper.viewCount(sqlMap);
			}
			
		});
	}*/
	
	/**
	 * 删除某个栏目下的信息(删除栏目是也要删除信息)
	 * @param itemId
	 * @return
	 * update portal_info set
		is_deleted=0 where item_id=#{id} or item_id in
		(select id from
		portal_item where parent_id=#{id})
	 */
	public void deleteByItem(Long itemId) {
		List<PortalInfo> list = findInfosByItemId(itemId);
		List<Long> itemIds = 
		Lists.transform(portalItemService.findByField("parentId", itemId), new Function<PortalItem, Long>() {
			@Override
			public Long apply(PortalItem permission) {
				return permission.getId();
			}
		});
		list.addAll(infoDataSource.createQuery(getEntityClass()).filter("isDeleted", 0).filter("itemId in", itemIds).asList());
		for(PortalInfo info : list){
			doDelete(info.getId());
		}
	}
	
	//获取infolistId 中最后发布的一条数据
	public PortalInfo getInfo(List<Long> infoIds){
		List<PortalInfo> list = infoDataSource.createQuery(getEntityClass()).filter("isDeleted", 0).filter("id in", infoIds)
				.order("-publicTime").limit(1).asList();
		return list.isEmpty()?null:list.get(0);
	}
	
	public int deleteTjImageById(Long id){
		return update(id, "tjImage", "");
	}
	/**
	 * 
	 * @param itemId
	 * @param id
	 * @return
	 * select id,title
		from
		portal_info where
		<![CDATA[  item_id = #{itemId} and id>#{Id} ]]>
		and is_deleted = 1
		order by is_recommend desc ,id asc
		limit 1
	 */
	public PortalInfo findAfter(Long itemId,Long id){
		List<PortalInfo> list = infoDataSource.createQuery(getEntityClass()).filter("isDeleted", 0).filter("itemId", itemId).filter("id >", id)
				.order("-isRecommend,id").limit(1).asList();
		return list.isEmpty()?null:list.get(0);
	}
	/**
	 * 
	 * @param itemId
	 * @param id
	 * @return
	 * select id,title
		from
		portal_info where
		<![CDATA[ item_id = #{itemId} and id<#{Id} ]]>
		and is_deleted = 1
		order by is_recommend desc ,id desc
		limit 1
	 */
	public PortalInfo findBefore(Long itemId,Long id){
		List<PortalInfo> list = infoDataSource.createQuery(getEntityClass()).filter("isDeleted", 0).filter("itemId", itemId).filter("id <", id)
				.order("-isRecommend,id").limit(1).asList();
		return list.isEmpty()?null:list.get(0);
	}
	/**
	 * 将info的信息填充到dto中
	 * @param dto
	 * @param info
	 */
	public void filled(PortalInfoDTO dto,PortalInfo info){
		dto.setId(info.getId());
		dto.setItemId(info.getItemId());
		dto.setTitle(info.getTitle());
		dto.setSubtitle(info.getSubtitle());
		dto.setSummary(info.getSummary());
		dto.setContent(info.getContent());
		dto.setStatus(info.getStatus());
		dto.setIsHead(info.getIsHead());
		dto.setIsSend(info.getIsSend());
		dto.setIsRecommend(info.getIsRecommend());
		dto.setUrl(info.getUrl());
		dto.setImage(info.getImage());
		dto.setPublicUser(info.getPublicUser());
		dto.setPublicTime(info.getPublicTime());
		dto.setListImage(info.getListImage());
		dto.setTjImage(info.getTjImage());
		dto.setLastSendTime(info.getLastSendTime());
		dto.setSource(info.getSource());
		dto.setTagId(info.getTagId());
		dto.setAuthor(info.getAuthor());
		dto.setKeyword(info.getKeyword());
		dto.setField1(info.getField1());
		dto.setCreateTime(info.getCreateTime());
		dto.setCreator(info.getCreator());
		dto.setUpdateTime(info.getUpdateTime());
		dto.setUpdater(info.getUpdater());
		dto.setIsDeleted(info.getIsDeleted());
	}
	/**
	 * 将dto的信息填充到info中
	 * @param info
	 * @param dto
	 */
	public void filled(PortalInfo info,PortalInfoDTO dto){
		info.setId(dto.getId());
		info.setItemId(dto.getItemId());
		info.setTitle(dto.getTitle());
		info.setSubtitle(dto.getSubtitle());
		info.setSummary(dto.getSummary());
		info.setContent(dto.getContent());
		info.setStatus(dto.getStatus());
		info.setIsHead(dto.getIsHead());
		info.setIsSend(dto.getIsSend());
		info.setIsRecommend(dto.getIsRecommend());
		info.setUrl(dto.getUrl());
		info.setImage(dto.getImage());
		info.setPublicUser(dto.getPublicUser());
		info.setPublicTime(dto.getPublicTime());
		info.setListImage(dto.getListImage());
		info.setTjImage(dto.getTjImage());
		info.setLastSendTime(dto.getLastSendTime());
		info.setSource(dto.getSource());
		info.setTagId(dto.getTagId());
		info.setAuthor(dto.getAuthor());
		info.setKeyword(dto.getKeyword());
		info.setField1(dto.getField1());
		info.setCreateTime(dto.getCreateTime());
		info.setCreator(dto.getCreator());
		info.setUpdateTime(dto.getUpdateTime());
		info.setUpdater(dto.getUpdater());
		info.setIsDeleted(dto.getIsDeleted());
	}

}
