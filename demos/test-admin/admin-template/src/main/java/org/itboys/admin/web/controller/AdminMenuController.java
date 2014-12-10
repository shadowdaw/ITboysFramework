package org.itboys.admin.web.controller;

import java.util.List;

import org.itboys.admin.dto.AdminMenuDto;
import org.itboys.admin.entity.AdminMenu;
import org.itboys.admin.service.AdminMenuService;
import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.framework.spring.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
/**
 * 菜单管理
 * @author WeiSky
 *
 */
@RestController 
@RequestMapping("/admin/menu") 
public class AdminMenuController extends BaseController{

	@Autowired
	private AdminMenuService adminMenuService;
	
	/**
	 * 获取菜单列表，并按一定的数据格式返回
	 * @param parentId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findAllMenu")
	public List<AdminMenuDto> findAllMenu(){
		return adminMenuService.findAllMenu2();
	}
	
	/**
	 * 新增菜单
	 * @param am
	 */
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public boolean add(AdminMenu am){
		try{
			am.setCr(AdminSessionHolder.getAdminUserId());
			am.setCt(System.currentTimeMillis());
			adminMenuService.save(am);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 根据菜单ID，删除一条数据
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public boolean delete(long id){
		try {
			adminMenuService.delete(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 根据ID，获取一条数据
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getById")
	public AdminMenu getById(long id){
		return adminMenuService.getById(id);
	}
	
	@ResponseBody
	@RequestMapping("/modifyMune")
	public boolean modifyMune(AdminMenu am){
		try{
			adminMenuService.updateMenu(am);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
