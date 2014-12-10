package org.itboys.admin.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.itboys.admin.entity.AdminUser;
import org.itboys.admin.service.AdminUserService;
import org.itboys.framework.spring.controller.BaseController;
import org.itboys.mongodb.utils.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/admin/user")  
public class AdminUserController extends BaseController{

	@Autowired
	private AdminUserService adminUserService;
	
	@ResponseBody
	@RequestMapping("/{id}")
	public AdminUser detail(@PathVariable("id") Long id){
		return adminUserService.getById(id);
	}
	
	/**
	 * 获取所有的用户信息（带分页）
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findAllUserAndPage")
	public Page<AdminUser> findAllUserAndPage(HttpServletRequest request){
		return adminUserService.findAllUserAndPage(request);
	}
	
}
