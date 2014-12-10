package org.itboys.product.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.itboys.commons.CommonConstants;
import org.itboys.commons.utils.ajax.AjaxUtils;
import org.itboys.framework.query.JsonPageUtils;
import org.itboys.framework.resource.ResourceHolder;
import org.itboys.framework.spring.controller.BaseController;
import org.itboys.product.entity.mongo.Advertisement;
import org.itboys.product.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 广告管理
 * @author huml
 */
@RestController
@RequestMapping(value="/adzone/advertisement")
public class AdvertisementController extends BaseController{

	@Autowired
	private AdvertisementService advertisementService;
	@Autowired
	private ResourceHolder resourceHolder;
	
	@RequestMapping("/vm")
	public ModelAndView projectMenus(HttpServletRequest request,Model model){
		Long adzoneId = Long.parseLong(request.getParameter("adzoneId"));
		model.addAttribute("adzoneId", adzoneId);
		return new ModelAndView("/adzone/advertisement/list");
	}
	
	@RequestMapping("/list")
	public void list(HttpServletRequest request,HttpServletResponse response){
		Long adzoneId = Long.parseLong(request.getParameter("adzoneId"));
		List<Advertisement> list = advertisementService.getListByAdzoneId(adzoneId);
		JsonPageUtils.renderJsonPage(list!=null&&list.size()>0?list.size():0L, list, response);
	}
	
	@RequestMapping("/getById")
	public ModelAndView input(HttpServletRequest request,HttpServletResponse response,Model model){
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			Advertisement entity = advertisementService.getById(Long.valueOf(id));
			model.addAttribute("entity", entity);
		}
		Long adzoneId = Long.parseLong(request.getParameter("adzoneId"));
		model.addAttribute("adzoneId", adzoneId);
		model.addAttribute("staticRoot",resourceHolder.getStringValue("staticRoot"));
		model.addAttribute("adminRoot",resourceHolder.getStringValue("adminRoot"));
		model.addAttribute("imgRoot",resourceHolder.getStringValue("imgRoot"));
		return new ModelAndView("/adzone/advertisement/input");
	}
	
	@RequestMapping("/save")
	public void save(@ModelAttribute Advertisement entity,HttpServletRequest request,HttpServletResponse response){
		try {
			Long adzoneId = Long.parseLong(request.getParameter("adzoneId"));
			if(entity.getId()==0l){
				entity.setStatus(Advertisement.STATUS_0);
				entity.setAdzoneId(adzoneId);
				advertisementService.save(entity);
			}else {
				advertisementService.update(entity);
			}
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("save Advertisement fail",e);
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}
	
	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable("id") Long id,HttpServletResponse response){
		try {
			advertisementService.doDelete(id);
			AjaxUtils.renderText(response, "1");
		} catch (Exception e) {
			logger.error("delete Advertisement fail",e);
			AjaxUtils.renderText(response, "0");
		}
	}
}
