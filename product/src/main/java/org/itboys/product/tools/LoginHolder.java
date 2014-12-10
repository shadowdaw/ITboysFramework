package org.itboys.product.tools;

import java.util.Date;

import org.itboys.admin.entity.BaseAdminEntity;
import org.itboys.commons.utils.servlet.ServletContextHolder;
import org.itboys.product.entity.mysql.BaseEntity;
import org.itboys.product.entity.mysql.product.ProductCategory;


/**
 * 登入用户基本信息获取
 * @author ChenJunhui
 */
public class LoginHolder {

	/**
	 * 获取登入用户信息
	 * @return
	 */
	public static LoginUser getLoginUser(String userSessionKey){
		LoginUser user =  (LoginUser)ServletContextHolder.getSession().getAttribute(userSessionKey);
		return user;
	}
	
	/**
	 * 将登入用户信息 创建时间 等信息注入对象
	 * @param adminEntity
	 * @return
	 */
	public static void prepareLoginMessage(BaseEntity adminEntity,String userSessionKey){
		if(adminEntity!=null){
			if(ServletContextHolder.getSession().getAttribute(userSessionKey)!=null){
				LoginUser user = getLoginUser(userSessionKey);
				adminEntity.setCreator(user.getUserid());
				adminEntity.setUpdater(user.getUserid());
				Date date = new Date();
				adminEntity.setCreateTime(date);
				adminEntity.setUpdateTime(date);
			}else{
				adminEntity.setCreator(1l);
				adminEntity.setUpdater(1l);
				Date date = new Date();
				adminEntity.setCreateTime(date);
				adminEntity.setUpdateTime(date);
			}
		}
	}
	
	/**
	 * 将登入用户信息 创建时间 等信息注入对象
	 * @param entity
	 * @return
	 */
	public static void prepareLoginMessage(BaseEntity entity,String[] userSessionKeys){
		for(String sessionKey:userSessionKeys){
			if(ServletContextHolder.getSession().getAttribute(sessionKey)!=null){
				prepareLoginMessage(entity,sessionKey);
				return;
			}
		}
	}
}
