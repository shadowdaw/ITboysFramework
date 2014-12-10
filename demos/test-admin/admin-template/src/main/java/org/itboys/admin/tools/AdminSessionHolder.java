package org.itboys.admin.tools;

import java.util.List;
import java.util.Map;

import org.itboys.admin.constant.AdminSessionConstant;
import org.itboys.commons.utils.servlet.ServletContextHolder;

/**
 * 管理后台session相关
 * @author 俊哥.Chen
 *
 */
public class AdminSessionHolder {

	/**
	 * 获取session中的用户ID
	 * @return
	 */
	public static final Long getAdminUserId(){
		return (Long) ServletContextHolder.getSession().getAttribute(AdminSessionConstant.SESSION_USER_ID);
	}
	
	/**
	 * 设置session中的用户ID
	 * @return
	 */
	public static final void setAdminUserId(Long userId){
		 ServletContextHolder.getSession().setAttribute(AdminSessionConstant.SESSION_USER_ID,userId);
	}
	
	/**
	 * 获取session中的用户类型
	 * @return
	 */
	public static final Integer getUserType(){
		return (Integer) ServletContextHolder.getSession().getAttribute(AdminSessionConstant.SESSION_USER_TYPE);
	}
	
	/**
	 * 设置session中的用户类型
	 * @return
	 */
	public static final void setUserType(Integer type){
		 ServletContextHolder.getSession().setAttribute(AdminSessionConstant.SESSION_USER_TYPE,type);
	}
	
	/**
	 * 获取session中的权限
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final Map<String,List<String>> getPermissionMap(){
		return (Map<String,List<String>>) ServletContextHolder.getSession().getAttribute(AdminSessionConstant.SESSION_PERMISSION);
	}
	
	/**
	 * 设置session中的权限
	 * @return
	 */
	public static final void setPermissionMap(Map<String,List<String>> perssmionMap){
		ServletContextHolder.getSession().setAttribute(AdminSessionConstant.SESSION_PERMISSION,perssmionMap);
	}
	

	/**
	 * 获取session中的用户组织ID
	 * @return
	 */
	public static final Long getOrgId(){
		return (Long) ServletContextHolder.getSession().getAttribute(AdminSessionConstant.SESSION_ORG_ID);
	}
	
	/**
	 * 设置session中的用户组织ID
	 * @return
	 */
	public static final void setOrgId(long orgId){
	  ServletContextHolder.getSession().setAttribute(AdminSessionConstant.SESSION_ORG_ID,orgId);
	}
}
