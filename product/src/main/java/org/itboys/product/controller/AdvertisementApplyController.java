package org.itboys.product.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.itboys.commons.CommonConstants;
import org.itboys.commons.utils.ajax.AjaxUtils;
import org.itboys.framework.query.JsonPageUtils;
import org.itboys.framework.resource.ResourceHolder;
import org.itboys.framework.spring.controller.BaseController;
import org.itboys.mongodb.utils.page.Page;
import org.itboys.product.entity.mongo.AdvertisementApply;
import org.itboys.product.service.AdvertisementApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value="/adzone/advertisementapply")
public class AdvertisementApplyController extends BaseController{

	@Autowired
	private AdvertisementApplyService advertisementApplyService;
	@Autowired
	private ResourceHolder resourceHolder;
	
	/**
	 * 异步获取列表
	 * @param response
	 */
	@RequestMapping("/list")
	public void list(HttpServletRequest request,HttpServletResponse response){
		Page<AdvertisementApply> page = advertisementApplyService.pageQuery(request);
		JsonPageUtils.renderJsonPage(page.getTotal(), page.getData(), response);
	}
	
	@RequestMapping("/getApply")
	public ModelAndView getApply(@RequestParam(required=false) Long id,HttpServletResponse response,Model model){
		AdvertisementApply entity = advertisementApplyService.getById(id);
		model.addAttribute("apply", entity);
		model.addAttribute("staticRoot",resourceHolder.getStringValue("staticRoot"));
		model.addAttribute("adminRoot",resourceHolder.getStringValue("adminRoot"));
		model.addAttribute("imgRoot",resourceHolder.getStringValue("imgRoot"));
		return new ModelAndView("/adzone/advertisementapply/input");
	}
	
	
	@RequestMapping("/updateStatus")
	public void delete(HttpServletRequest request,HttpServletResponse response){
		try {
			Long id = Long.parseLong(request.getParameter("id"));
			Integer status = Integer.parseInt(request.getParameter("status"));
			AdvertisementApply entity =advertisementApplyService.getById(id);
			entity.setStatus(status);
			advertisementApplyService.update(entity);
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("AdvertisementApply updateStatus error", e);
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}
	
	/**
	 * 异步删除
	 */
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable("id") Long id,HttpServletResponse response){
		try {
			advertisementApplyService.doDetele(id);
			AjaxUtils.renderText(response, "1");
		} catch (Exception e) {
			logger.error("delete AdvertisementApply error", e);
			AjaxUtils.renderText(response, "0");
		}
	}
}
