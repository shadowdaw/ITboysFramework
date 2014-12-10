package org.itboys.portal.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.itboys.commons.CommonConstants;
import org.itboys.commons.utils.ajax.AjaxUtils;
import org.itboys.framework.query.JsonPageUtils;
import org.itboys.framework.spring.controller.BaseController;
import org.itboys.mongodb.utils.page.Page;
import org.itboys.portal.entity.KVConfig;
import org.itboys.portal.service.KVConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/common/kvconfig")
public class KVConfigController extends BaseController{

	@Autowired
	private KVConfigService kVConfigService;
	
	/**
	 * 异步获取列表
	 * @param response
	 */
	@RequestMapping(value = {"/admin/list","/project/list"})
	public void list(HttpServletRequest request,HttpServletResponse response){
		Page<KVConfig> page = kVConfigService.pageQuery(request);
		JsonPageUtils.renderJsonPage(page.getTotal(), page.getData(), response);
	}
	
	@RequestMapping("/getConfig/{id}")
	public void getConfig(@PathVariable("id") Long id,HttpServletResponse response){
		AjaxUtils.renderJson(response, kVConfigService.getById(id));
	}
	
	
	@RequestMapping("/save")
	public void save(@ModelAttribute KVConfig config,HttpServletResponse response){
		try {
			if(config.getId()==0l){	
				kVConfigService.insert(config);	
			}else{
				kVConfigService.update(config);
			}
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("kvconfig sav fail", e);
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}
	
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable("id") Long id,HttpServletResponse response){
		try {
			kVConfigService.delete(id);
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		} catch (Exception e) {
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}
	
	
	@RequestMapping("/project")
	public String project(HttpServletRequest request,HttpServletResponse response,Model model){
		String appKey = request.getParameter("appKey");
		model.addAttribute("appKey", appKey);
		return "/project/kvconfig/list";
	}
	

}
