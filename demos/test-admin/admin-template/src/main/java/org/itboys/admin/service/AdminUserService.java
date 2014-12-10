package org.itboys.admin.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.itboys.admin.dto.AdminUserData;
import org.itboys.admin.entity.AdminOrg;
import org.itboys.admin.entity.AdminPost;
import org.itboys.admin.entity.AdminUser;
import org.itboys.mongodb.core.MongoDataSource;
import org.itboys.mongodb.utils.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.code.morphia.query.UpdateOperations;

@Service
public class AdminUserService extends BaseAdminService<AdminUser, Long> {
	
	@Autowired
	private AdminOrgService adminOrgService;
	@Autowired
	private AdminPostService adminPostService;
	@Autowired
	private AdminRoleService adminRoleService;

	@Resource(name="adminDS")
	private MongoDataSource ds;
	
	@Override
	protected MongoDataSource getMongoDataSource() {
		return ds;
	}

	@Override
	protected Class<AdminUser> getEntityClass() {
		return AdminUser.class;
	}

	public int updatePassword(long userId,String password){
		UpdateOperations<AdminUser> ops = ds.createUpdateOperations(AdminUser.class);
		ops.set("password", password);
		return update(userId, ops);
	}
	
	/**
	 * 组装登录人相关的信息
	 * @return
	 */
	public AdminUserData installAdminUserData(Long userId){
		AdminUserData aud = new AdminUserData();
		
		AdminUser adminUser = getById(userId);
		aud.setLogined(true);
		aud.setUsername(adminUser.getUsername());
		aud.setName(adminUser.getName());
		aud.setOrgId(adminUser.getOrgId());
		
		/* 获取当前登录人的组织部门信息  */
		AdminOrg ao = adminOrgService.getById(adminUser.getOrgId());
		if(ao != null){
			aud.setOrgName(ao.getName());
		}
		/* 获取当前登陆人的职务信息  */
		AdminPost ap = adminPostService.getById(adminUser.getPostId());
		if(ap != null){
			aud.setPostName(ap.getName());
		}
		return aud;
	}
	
	/**
	 * 获取所有的用户列表(带分页)
	 * 
	 * @return
	 */
	public Page<AdminUser> findAllUserAndPage(HttpServletRequest request){
		return pageQuery(request);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
