package org.itboys.admin.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.itboys.admin.entity.AdminUser;
import org.itboys.admin.service.AdminUserService;
import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.commons.CommonConstants;
import org.itboys.framework.spring.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/admin/login")  
public class AdminLoginController extends BaseController{

	@Autowired
	private AdminUserService adminUserService;

	/**
	 * 登录验证
	 * 
	 * 出参：
	 * "-1":用户名不存在
	 * "-2":密码不正确
	 * "-3":验证码错误
	 * 
	 * @param username
	 * @param password
	 * @param yzm
	 * @param request
	 * @return
	 */
	@RequestMapping("/doLogin")  
	public String doLogin(@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("yzm") String yzm,HttpServletRequest request){
		
		String _yzm = (String) request.getSession().getAttribute("yzm");
		if(!StringUtils.equals(_yzm, yzm)){
			/* 验证码错误  */
			//return "-3";
		}
		/* 判断用户名是否存在  */
		boolean usernameExists = adminUserService.exists("username", username);
		if(usernameExists){
			List<AdminUser> adminUser = adminUserService.findByField("username", username);
			/* 对比密码是否正确，密码没有做加密处理  */
			if(StringUtils.equals(password, adminUser.get(0).getPassword())){
				/* 设置session中的用户ID */
				AdminSessionHolder.setAdminUserId(adminUser.get(0).getId());
				/* 设置session中的用户组织ID */
				AdminSessionHolder.setOrgId(adminUser.get(0).getOrgId());
				
			}else{
				/* 密码不正确  */
				return "-2";
			}
		}else{
			/* 用户名不存在  */
			return "-1";
		}
		return CommonConstants.SUCCESS;
	}
}
