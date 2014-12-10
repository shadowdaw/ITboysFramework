package org.itboys.product.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.itboys.admin.tools.AdminPermissionCheck;
import org.itboys.commons.CommonConstants;
import org.itboys.commons.utils.ajax.AjaxUtils;
import org.itboys.framework.query.JsonPageUtils;
import org.itboys.framework.spring.controller.BaseController;
import org.itboys.mongodb.utils.page.Page;
import org.itboys.product.entity.mongo.Adzone;
import org.itboys.product.service.AdzoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 广告位
 * @author huml
 */
@RestController
@RequestMapping(value = "/adzone/adzone")
public class AdzoneController extends BaseController{

	@Autowired
	private AdzoneService adzoneService;
	
	@RequestMapping("/vm")
	public ModelAndView vm(HttpServletRequest request,Model model){
		return new ModelAndView("/adzone/adzone/list");
	}
	
	/**
	 * 异步获取列表
	 * @param response
	 */
	@RequestMapping(value = "/admin/list")
	@AdminPermissionCheck("adzone:list")
	public void list(HttpServletRequest request,HttpServletResponse response){
		Page<Adzone> page = adzoneService.pageQuery(request);
		JsonPageUtils.renderJsonPage(page.getTotal(), page.getData(), response);
	}
	
	/**
	 * 异步获取单个对象
	 * @param id
	 * @param response
	 */
	@RequestMapping("/getAdzone/{id}")
	public void getAdzone(@PathVariable("id") Long id,HttpServletResponse response){
		Adzone entity = adzoneService.getById(id);
		AjaxUtils.renderJson(response, entity);
	}
	
	@RequestMapping("/getById")
	public ModelAndView input(HttpServletRequest request,HttpServletResponse response,Model model){
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			Adzone entity = adzoneService.getById(Long.valueOf(id));
			model.addAttribute("entity", entity);
		}
		return new ModelAndView("/adzone/adzone/input");
	}
	
	
	/**
	 * 异步保存
	 */
	@RequestMapping("/admin/save")
	@AdminPermissionCheck("adzone:save")
	public void save(@ModelAttribute Adzone entity,HttpServletRequest request,HttpServletResponse response){
		try {
			if(entity.getId()==0l){
				entity.setStatus(Adzone.STATUS_1);
				adzoneService.insert(entity);
			}else{
				adzoneService.update(entity);
			}
			AjaxUtils.renderText(response,CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("Adzone save error", e);
			AjaxUtils.renderText(response,CommonConstants.FAIL);
		}
	}
	
	
	/**
	 * 项目异步保存
	 * @param codes
	 * @param request
	 * @param response
	 */
	@RequestMapping("/prosave")
//	@ProjectPermissionCheck("adzone:save")
	public void saveByProject(@ModelAttribute Adzone entity,HttpServletRequest request,HttpServletResponse response){
		try {
			if(entity.getId()==0l){
				entity.setStatus(Adzone.STATUS_1);
				adzoneService.insert(entity);
			}else{
				adzoneService.update(entity);
			}
			AjaxUtils.renderText(response,CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("Adzone prosave error", e);
			AjaxUtils.renderText(response,CommonConstants.FAIL);
		}
	}
	
	/**
	 * 异步删除
	 */
	@RequestMapping("/delete/{id}")
//	@AdminPermissionCheck("adzone:delete")
	public void delete(@PathVariable("id") Long id,HttpServletResponse response){
		try {
			adzoneService.deleteById(id);
			AjaxUtils.renderText(response,CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("Adzone delete error", e);
			AjaxUtils.renderText(response,CommonConstants.FAIL);
		}
	}
	
	/**
	 * 异步失效
	 */
	@RequestMapping("/lose/{id}")
	public void lose(@PathVariable("id") Long id,HttpServletResponse response){
		AjaxUtils.renderText(response,String.valueOf(adzoneService.updateStatus(Adzone.STATUS_0,id)));
	}
	
	/**
	 * 异步生效
	 */
	@RequestMapping("/take/{id}")
	//@AdminPermissionCheck("adzone:take")
	public void take(@PathVariable("id") Long id,HttpServletResponse response){
		AjaxUtils.renderText(response,String.valueOf(adzoneService.updateStatus(Adzone.STATUS_1,id)));
	}
}
