package org.itboys.portal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.commons.utils.collection.CommonCollectionUtils;
import org.itboys.commons.utils.collection.FetchCondition;
import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.portal.constant.PortalConstants;
import org.itboys.portal.dto.PortalItemDTO;
import org.itboys.portal.entity.PortalAppElement;
import org.itboys.portal.entity.PortalInfo;
import org.itboys.portal.entity.PortalItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service
public class PortalItemService extends BasePortalService<PortalItem, Long> {

	private static final long serialVersionUID = 2406882811515821478L;
	@Autowired
	private PortalInfoService portalInfoService;
	@Autowired
	private PortalAppElementService portalAppElementService;
	
	@Resource(name="portalDS")
	private MongoDataSource itemDataSource;
	@Override
	protected MongoDataSource getMongoDataSource() {
		return itemDataSource;
	}

	@Override
	protected Class<PortalItem> getEntityClass() {
		return PortalItem.class;
	}
	
	public void insert(PortalItem portalItem) {
		if (portalItem.getParentId() == null) {
			portalItem.setParentId(0L);
		}
		if (PortalConstants.ItemParam.IS_LEAF != portalItem.getIsLeaf()) {
			portalItem.setType(PortalConstants.ItemParam.TYPE_0);
		}
		save(portalItem);
	}
	
	public void update(PortalItem portalItem){
		PortalItem item = getById(portalItem.getId());
		portalItem.setCreator(item.getCreator());
		portalItem.setCreateTime(item.getCreateTime());
		portalItem.setUpdater(AdminSessionHolder.getAdminUserId());
		portalItem.setUr(AdminSessionHolder.getAdminUserId());
		portalItem.setUpdateTime(new Date());
		portalItem.setUt(System.currentTimeMillis());
		super.update(portalItem);
	}

	public void deleteItemAndInfo(Long id) {
		portalInfoService.deleteByItem(id);// 删除栏目的同时删除该栏目下的信息
		doDelete(id);
	}

	public PortalItem findByCode(String code) {
		Map<String, Object> param = Maps.newHashMapWithExpectedSize(1);
		param.put("code", code);
		List<PortalItem> list = list(param);
		return list.isEmpty() ? null : list.get(0);
	}

	public List<PortalItem> findAll(Map<String, Object> sqlMap) {
		List<PortalItem> list = list(sqlMap);
		return list;
	}

	/**
	 * 获取一级栏目
	 * 
	 * @param item
	 * @return
	 */
	public List<PortalItem> getRootsItem(List<PortalItem> item) {
		return CommonCollectionUtils.filterCollection(item,
				new FetchCondition<PortalItem>() {
					@Override
					public boolean needFetch(PortalItem t) {
						return t.getParentId().equals(0L);
					}
				});
	}

	public List<PortalItem> findByCodes(List<String> code) {
		Map<String, Object> param = Maps.newHashMapWithExpectedSize(1);
		param.put("code in", code);
		return list(param);
	}

	/**
	 * 根据父ID加载子栏目
	 * 
	 * @param id
	 * @return
	 */
	public List<PortalItem> getChilidsByParentid(Long parentId) {
		Map<String, Object> param = Maps.newHashMapWithExpectedSize(1);
		param.put("parentId", parentId);
		return list(param);
	}

	public List<Long> getChilidIdsByParentid(Long parentId) {
		List<PortalItem> items = getChilidsByParentid(parentId);
		List<Long> longs = Lists.newArrayList();
		for (PortalItem portalItem : items) {
			longs.add(portalItem.getId());
		}
		return longs;
	}

	/**
	 * 根据父ID加载子栏目和次级栏目信息
	 * 
	 * @param id
	 * @return
	 */
	public List<PortalItemDTO> getChilidsinfoByParentid(Long parentId) {
		List<PortalItemDTO> dtos = new ArrayList<PortalItemDTO>();
		Map<String, Object> queryParam = Maps.newHashMapWithExpectedSize(1);
		queryParam.put("parentId", parentId);
		List<PortalItem> list = list(queryParam);
		for (int i = 0; i < list.size(); i++) {
			PortalItemDTO dto = new PortalItemDTO();
			dto.setPortalItem(list.get(i));
			List<PortalInfo> infolist = portalInfoService.findInfosByItemId(Long
					.valueOf(list.get(i).getId()));
			dto.setChild(infolist);
			dtos.add(dto);
		}
		return dtos;
	}
	/**
	 * select i.id from portal_app_element p,portal_item i where i.id=p.element_id and p.nav_id=#{id}
	 * @param id
	 * @return
	 */
	public List<Long> getItemByNavId(Long id) {
		Map<String, Object> param = Maps.newHashMapWithExpectedSize(1);
		param.put("navId", id);
		List<PortalAppElement> elements = portalAppElementService.list(param);
		List<Long> elementIds = Lists.transform(elements,
				new Function<PortalAppElement, Long>() {
					@Override
					public Long apply(PortalAppElement input) {
						return input.getElementId();
					}

				});
		param.clear();
		param.put("id in", elementIds);
		List<PortalItem> items = list(param);
		List<Long> itemIds = Lists.transform(items,
				new Function<PortalItem, Long>() {
					@Override
					public Long apply(PortalItem input) {
						return input.getId();
					}

				});
		return itemIds;
	}

	public Long count(Map<String, Object> sqlMap) {
		return (long)list(sqlMap).size();
	}

}
