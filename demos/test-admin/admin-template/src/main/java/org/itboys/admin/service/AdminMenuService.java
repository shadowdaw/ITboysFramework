package org.itboys.admin.service;

import java.util.List;

import javax.annotation.Resource;

import org.itboys.admin.dto.AdminMenuDto;
import org.itboys.admin.entity.AdminMenu;
import org.itboys.mongodb.core.MongoDataSource;
import org.springframework.stereotype.Service;

import com.google.code.morphia.query.UpdateOperations;
import com.google.common.collect.Lists;

/**
 * 菜单服务层
 * @author WeiSky
 *
 */
@Service
public class AdminMenuService extends BaseAdminService<AdminMenu, Long>{
	
	@Resource(name="adminDS")
	private MongoDataSource ds;
	

	@Override
	protected MongoDataSource getMongoDataSource() {
		return ds;
	}

	@Override
	protected Class<AdminMenu> getEntityClass() {
		return AdminMenu.class;
	}
	
	/**
	 * 获取所有的菜单
	 * @return
	 */
	public List<AdminMenuDto> findAllMenu2(){
		List<AdminMenu> amList = ds.createQuery(getEntityClass()).asList();
		List<AdminMenuDto> ad = Lists.newArrayList();
		boolean flage = true;
		for(AdminMenu am : amList){
			AdminMenuDto amd = new AdminMenuDto();
			amd.setId(am.getId());
			amd.setMenuName(am.getMenuName());
			amd.setPid(am.getParentId());
			amd.setUrl(am.getUrl());
			if(flage){
				amd.setExpanded(true);
				flage = false;
			}
			ad.add(amd);
		}
		return ad;
	}
	
	/**
	 * 获取所有的菜单，并分配层级关系
	 * @return
	 */
	public List<AdminMenuDto> findAllMenu(){
		boolean flage = true;//如果是第一个根菜单，就展开
		List<AdminMenuDto> ad = Lists.newArrayList();
		List<AdminMenu> rootList = findRootMenu();
		for(AdminMenu am : rootList){
			List<AdminMenuDto> childDtoList = Lists.newArrayList();
			AdminMenuDto amd = new AdminMenuDto();
			amd.setMenuName(am.getMenuName());
			List<AdminMenu> childList = findByRootId(am.getId());
			for(AdminMenu child : childList){
				AdminMenuDto childDto = new AdminMenuDto();
				childDto.setId(child.getId());
				childDto.setMenuName(child.getMenuName());
				childDto.setPid(child.getParentId());
				childDto.setUrl(child.getUrl());
				childDtoList.add(childDto);
				amd.setChildren(childDtoList);
			}
			if(flage){
				amd.setExpanded(true);
				flage = false;
			}
			ad.add(amd);
		}
		return ad;
	}
	
	/**
	 * 更新菜单
	 * @param am
	 */
	public void updateMenu(AdminMenu am){
		UpdateOperations<AdminMenu> uo = ds.createUpdateOperations(AdminMenu.class);
		uo.set("menuName", am.getMenuName());
		uo.set("url", am.getUrl());
		super.update(am.getId(), uo);
	}
	
	/**
	 * 获取最顶级的菜单
	 * @return
	 */
	public List<AdminMenu> findRootMenu(){
		return findByField("parentId", 0);
	}
	
	/**
	 * 根据父级ID，获取下面所有的子菜单
	 * @param parentId
	 * @return
	 */
	public List<AdminMenu> findByRootId(long parentId){
		return findByField("parentId", parentId);
	}
	
}
