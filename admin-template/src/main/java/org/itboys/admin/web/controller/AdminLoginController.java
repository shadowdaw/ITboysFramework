package org.itboys.admin.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.itboys.admin.entity.AdminUser;
import org.itboys.admin.service.AdminUserService;
import org.itboys.admin.tools.AdminCookieHolder;
import org.itboys.admin.tools.AdminHolder;
import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.admin.tools.WebConstants;
import org.itboys.commons.CommonConstants;
import org.itboys.commons.utils.ajax.AjaxUtils;
import org.itboys.commons.utils.cookie.CookieUtils;
import org.itboys.commons.utils.encryption.Digests;
import org.itboys.framework.resource.ResourceHolder;
import org.itboys.framework.spring.controller.BaseController;
import org.omg.PortableServer.ServantLocatorPackage.CookieHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController 
public class AdminLoginController extends BaseController{

	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private ResourceHolder resourceHolder;

	/**
	 * 异步登入地址
	 * @param user
	 * @param response
	 */
	@RequestMapping("/admin/doLogin")
	public void doLogin(@ModelAttribute AdminUser user,@RequestParam String yzm,
			HttpServletRequest request,HttpServletResponse response){
		AdminUser proUser = adminUserService.getAdminUser(user.getUsername());
		if(proUser==null){
			AjaxUtils.renderText(response, "0");//用户不存在
			return ;
		}
		if(!StringUtils.equals(proUser.getPassword(), Digests.md5(user.getPassword()))){
			AjaxUtils.renderText(response, "-1");//密码不正确
			return ;
		}
		String _yzm = (String) request.getSession().getAttribute("rand");
		if(!StringUtils.equals(_yzm, yzm)){
			AjaxUtils.renderText(response, "-2");//验证码不正确
			return ;
		}
		//把用户名 弄到cookie里
		CookieUtils.addCookieWithBase64(response, WebConstants.CookieKey.COOKIE_KEY_ADMIN_USER_NAME, proUser.getUsername(), 864000);
		if("true".equals(request.getParameter("autoLogin"))){
			AdminCookieHolder.addPasswordToCookie(proUser.getPassword(), 24*60*60);
		}
		AdminSessionHolder.setAdminUserId(proUser.getId());
		//组装登入信息
		AdminHolder.initLoginUser(request, proUser);
		AjaxUtils.renderText(response, "1");//登入成功
	}
	
	/**
	 * 异步退出
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/admin/logout")
	public ModelAndView logout(HttpServletRequest request,HttpServletResponse response){
		request.getSession().setAttribute(WebConstants.SessionKey.ADMIN_USER, null);
		CookieUtils.removeCookie(response, WebConstants.CookieKey.PASSWORD);
		AdminSessionHolder.removeAdminUserId();
		return new ModelAndView(resourceHolder.getStringValue("adminLoginUrl")); 
	}
}
