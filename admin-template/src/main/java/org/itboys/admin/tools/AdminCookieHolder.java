package org.itboys.admin.tools;

import org.itboys.admin.constant.AdminCookieConstant;
import org.itboys.commons.utils.cookie.CookieUtils;
import org.itboys.commons.utils.servlet.ServletContextHolder;

/**
 * 管理后台 cookie相关的变量
 * @author 俊哥.Chen
 *
 */
public class AdminCookieHolder {

	public void setAdminUserName(String username){
		CookieUtils.addCookie(ServletContextHolder.getResponse(), AdminCookieConstant.COOKIE_USER_NAME, username, 100*24*60*60);
	}
	
	public void setUserName(String name){
		CookieUtils.addCookie(ServletContextHolder.getResponse(), AdminCookieConstant.COOKIE_NAME, name, 100*24*60*60);
	}
	
	public String getAdminUserName(){
		return CookieUtils.getValueFromCookie(ServletContextHolder.getRequest(), AdminCookieConstant.COOKIE_USER_NAME);
	}
	
	public String getUserName(){
		return CookieUtils.getValueFromCookie(ServletContextHolder.getRequest(), AdminCookieConstant.COOKIE_NAME);
	}
	/**
	 * 将登入密码放到cookie中
	 * @param password
	 * @param time
	 * @return
	 */
	public static void addPasswordToCookie(String password,int time){
		CookieUtils.addCookieWithBase64(ServletContextHolder.getResponse(), WebConstants.CookieKey.PASSWORD, password, time);
	}
}
