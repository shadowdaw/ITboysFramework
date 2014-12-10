package org.itboys.admin.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.itboys.admin.dto.AdminUserData;
import org.itboys.admin.entity.AdminUser;
import org.itboys.admin.service.AdminMenuService;
import org.itboys.admin.service.AdminUserService;
import org.itboys.admin.service.InitAdminDataService;
import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.framework.resource.ResourceHolder;
import org.itboys.framework.spring.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 首页相关
 * @author 伟哥好强
 *
 */
@RestController 
public class AdminIndexController extends BaseController{
	
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private ResourceHolder resourceHolder;
	@Autowired
	private AdminMenuService adminMenuService;

	@RequestMapping("/getAdminUserData")  
	public AdminUserData getAdminUserData(){
		AdminUserData userData = new AdminUserData();
		Long userId = AdminSessionHolder.getAdminUserId();
		if(userId==null){
			userData.setLogined(false);
			return userData;
		}
		userData = adminUserService.installAdminUserData(userId);
		
		return userData;
	}
	
	/**
	 * 首页跳转
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value={"/",""})  
	public ModelAndView index(HttpServletResponse response, Model model) throws Exception{
		model.addAttribute("staticRoot",resourceHolder.getStringValue("staticRoot"));
		if(AdminSessionHolder.getAdminUserId()==null){
			return new ModelAndView(resourceHolder.getStringValue("adminLoginUrl"));
			//response.sendRedirect(resourceHolder.getStringValue("adminLoginUrl"));
		}else{
			AdminUser au = adminUserService.getById(AdminSessionHolder.getAdminUserId());
			//判断是否是超级管理员登陆
			if(StringUtils.equals(InitAdminDataService.username, au.getUsername())){
				model.addAttribute("menu", adminMenuService.findAllMenu());
			}
			return new ModelAndView(resourceHolder.getStringValue("adminIndexUrl"));
			//response.sendRedirect(resourceHolder.getStringValue("adminIndexUrl"));
		}
	}
	
	
}
