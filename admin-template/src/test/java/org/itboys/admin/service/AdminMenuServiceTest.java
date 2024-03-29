package org.itboys.admin.service;

import static org.junit.Assert.fail;

import org.itboys.admin.entity.AdminMenu;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;

public class AdminMenuServiceTest extends BaseServiceTest{
	
	@Autowired
	private AdminMenuService adminMenuService;

	@Test
	public void testGetById() {
		AdminMenu am = new AdminMenu();
		am.setCt(System.currentTimeMillis());
		am.setUt(System.currentTimeMillis());
		am.setMenuName("测试菜单名称3");
		am.setUrl("http://www.baidu.com4444");
		adminMenuService.save(am);
		
		AdminMenu am2 = adminMenuService.getById(am.getId());
		
		Assert.assertTrue(am2.getUrl().equals(am.getUrl()));
	}

	@Ignore("忽略")
	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	

	@Test
	public void testGetQueryById() {
		Query<AdminMenu> am = adminMenuService.getQueryById(1L);
		Assert.assertNotNull(am);
	}

	@Test
	public void testDelete() {
		AdminMenu am = new AdminMenu();
		am.setCt(System.currentTimeMillis());
		am.setUt(System.currentTimeMillis());
		am.setMenuName("测试菜单名称");
		am.setUrl("http://www.baidu.com");
		adminMenuService.save(am);
		
		adminMenuService.delete(am.getId());
		
		Assert.assertNull(adminMenuService.getById(am.getId()));
	}

	@Test
	public void testUpdate() {
		UpdateOperations<AdminMenu> uo = adminMenuService.getMongoDataSource().createUpdateOperations(AdminMenu.class);
		uo.set("menuName", "修改菜单名称");
		adminMenuService.update(6L, uo);
		AdminMenu am = adminMenuService.getById(6L);
		Assert.assertTrue(am.getMenuName().equals("修改菜单名称"));
	}

	@Ignore("忽略")
	@Test
	public void testGetNextId() {
		fail("Not yet implemented");
	}

}
