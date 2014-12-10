package org.itboys.product.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.itboys.admin.entity.AdminUser;
import org.itboys.admin.service.AdminUserService;
import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.commons.CommonConstants;
import org.itboys.commons.utils.ajax.AjaxUtils;
import org.itboys.commons.utils.io.FileUtils;
import org.itboys.framework.query.JsonPageUtils;
import org.itboys.framework.resource.ResourceHolder;
import org.itboys.framework.spring.controller.BaseController;
import org.itboys.product.entity.mongo.Advertisement;
import org.itboys.product.entity.mongo.AdvertisementItem;
import org.itboys.product.service.AdvertisementItemService;
import org.itboys.product.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;

/**
 * 广告素材
 * 
 * @author huml
 */
@RestController
@RequestMapping(value = "/adzone/advertisementItem")
public class AdvertisementItemController extends BaseController {

	@Autowired
	private AdvertisementItemService advertisementItemService;
	@Autowired
	private AdvertisementService advertisementService;
	@Autowired
	private ResourceHolder resourceHolder;
	@Autowired
	private AdminUserService adminUserService;

	@RequestMapping("/vm")
	public ModelAndView projectMenus(HttpServletRequest request, Model model) {
		Long advertisementId = Long.parseLong(request
				.getParameter("advertisementId"));
		model.addAttribute("advertisementId", advertisementId);
		return new ModelAndView("/adzone/advertisementItem/list");
	}

	@RequestMapping("/list")
	public void list(HttpServletRequest request, HttpServletResponse response) {
		Long advertisementId = Long.parseLong(request
				.getParameter("advertisementId"));
		Map<String, Object> param = Maps.newHashMap();
		param.put("advertisementId", advertisementId);
		//新商店广告图有后台添加和代理商添加的区别
		AdminUser user = adminUserService.getById(AdminSessionHolder.getAdminUserId());
		if(user.getOrgId() > 0L){
			param.put("orgId", user.getOrgId());
		}
		List<AdvertisementItem> list = advertisementItemService
				.list(param);
		JsonPageUtils.renderJsonPage(
				list != null && list.size() > 0 ? list.size() : 0L, list,
				response);
	}

	@RequestMapping("/getById")
	public ModelAndView input(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			AdvertisementItem entity = advertisementItemService.getById(Long
					.valueOf(id));
			model.addAttribute("entity", entity);
		}
		Long advertisementId = Long.parseLong(request
				.getParameter("advertisementId"));
		model.addAttribute("advertisementId", advertisementId);
		Advertisement ast = advertisementService.getById(advertisementId);
		model.addAttribute("ast", ast);
		model.addAttribute("staticRoot",resourceHolder.getStringValue("staticRoot"));
		model.addAttribute("adminRoot",resourceHolder.getStringValue("adminRoot"));
		model.addAttribute("imgRoot",resourceHolder.getStringValue("imgRoot"));
		return new ModelAndView("/adzone/advertisementItem/input");
	}

	@RequestMapping("/save")
	public void save(
			@ModelAttribute AdvertisementItem entity,
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
		try {
			Long advertisementId = Long.parseLong(request
					.getParameter("advertisementId"));
			if (imageFile != null && !imageFile.isEmpty()) {
				String filePath = FileUtils.saveFile(imageFile.getInputStream(),
						resourceHolder.getStringValue("fileUploadPath"),
						imageFile.getOriginalFilename(), imageFile.getContentType());
				entity.setFilePath(filePath);
			}
			if (entity.getId() == 0l) {
				entity.setAdvertisementId(advertisementId);
				advertisementItemService.save(entity);
			} else {
				advertisementItemService.update(entity);
			}
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("AdvertisementItem save fail", e);
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}

	@RequestMapping("/save_noImg")
	public void save_noImg(@ModelAttribute AdvertisementItem entity,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			Long advertisementId = Long.parseLong(request
					.getParameter("advertisementId"));
			if (entity.getId() == 0l) {
				entity.setAdvertisementId(advertisementId);
				advertisementItemService.save(entity);
			} else {
				advertisementItemService.update(entity);
			}
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("AdvertisementItem save_noImg fail", e);
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}

	@RequestMapping("/delete/{id}")
	public void delete(@PathVariable("id") Long id, HttpServletResponse response) {
		try {
			advertisementItemService.doDelete(id);
			AjaxUtils.renderText(response, "1");
		} catch (Exception e) {
			logger.error("AdvertisementItem delete fail", e);
			AjaxUtils.renderText(response, "0");
		}
	}
}
