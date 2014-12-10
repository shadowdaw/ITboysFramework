package org.itboys.portal.web.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.StringUtils;
import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.commons.CommonConstants;
import org.itboys.commons.dto.Option;
import org.itboys.commons.utils.ajax.AjaxUtils;
import org.itboys.commons.utils.common.CommonUtils;
import org.itboys.commons.utils.io.FileUtils;
import org.itboys.commons.utils.number.ToNumberUtils;
import org.itboys.framework.query.JsonPageUtils;
import org.itboys.framework.resource.ResourceConfig;
import org.itboys.framework.resource.ResourceHolder;
import org.itboys.framework.spring.controller.BaseController;
import org.itboys.mongodb.utils.page.Page;
import org.itboys.mongodb.utils.query.PageQueryUtils;
import org.itboys.mongodb.utils.query.QueryParamUtils;
import org.itboys.portal.constant.PortalConstants;
import org.itboys.portal.dto.PortalInfoDTO;
import org.itboys.portal.entity.Attachement;
import org.itboys.portal.entity.InfoObjRel;
import org.itboys.portal.entity.PortalInfo;
import org.itboys.portal.entity.PortalItem;
import org.itboys.portal.service.AttachementService;
import org.itboys.portal.service.CodesService;
import org.itboys.portal.service.InfoObjRelService;
import org.itboys.portal.service.KVConfigService;
import org.itboys.portal.service.PortalInfoImageService;
import org.itboys.portal.service.PortalInfoService;
import org.itboys.portal.service.PortalItemService;
import org.itboys.portal.service.PortalTagService;
import org.itboys.portal.service.WatermarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;

@RestController
@RequestMapping(value = "/portal/info")
public class PortalInfoController extends BaseController {

	@Autowired
	private PortalInfoService portalInfoService;
	@Autowired
	private PortalItemService portalItemService;
	@Autowired
	private PortalInfoImageService protalInfoImageService;
	@Autowired
	private AttachementService attachementService;
	@Autowired
	private KVConfigService kVConfigService;
	@Autowired
	private WatermarkService watermarkService;
	@Autowired(required = false)
	private PortalTagService portalTagService;
	@Autowired
	private CodesService codesService;
	@Autowired
	private InfoObjRelService infoObjRelService;
	@Autowired
	private ResourceHolder resourceHolder;

	@RequestMapping("/tovm")
	public ModelAndView tovm(Model model,
			@RequestParam("parentId") String parentId,
			HttpServletResponse response, HttpServletRequest request) {
		model.addAttribute("parentId", parentId);
		return new ModelAndView("/portal/info/portalinfo");
	}

	@RequestMapping("/list")
	public void list(HttpServletRequest request, HttpServletResponse response) {
		final Map<String, Object> sqlMap = QueryParamUtils
				.builderQueryMap(request);

		Long itemId = ToNumberUtils
				.getLongValue(request.getParameter("itemId"));
		sqlMap.put("itemId", itemId);
		String defaultOrderBy = kVConfigService
				.getConfigValue("DEFAULT_PORTAL_INFO_ORDER_BY");
		if (StringUtils.isNotBlank(defaultOrderBy)) {
			sqlMap.put(PageQueryUtils.SORT_FIELD, defaultOrderBy);
		}
		Page<PortalInfo> list = portalInfoService.pageQuery(sqlMap);
		JsonPageUtils.renderJsonPage(list.getTotal(), list.getData(), response);
	}

	@RequestMapping("/info/{itemId}")
	public ModelAndView info(@PathVariable("itemId") Long itemId,
			HttpServletRequest request, Model model) {
		PortalItem item = portalItemService.getById(itemId);
		model.addAttribute("item", item);
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		if (item != null) {
			// 单个信息加载一条
			if (CommonUtils.isIn(item.getType(),
					PortalConstants.ItemParam.TYPE_1)) {
				model.addAttribute("info",
						portalInfoService.findInfoByItemId(itemId));
			}
		}
		return new ModelAndView("/portal/info/info");
	}

	@RequestMapping("/input")
	public ModelAndView input(HttpServletRequest request,
			@RequestParam(required = false) Long id,
			@RequestParam(required = false) Long itemId, Model model) {
		model.addAttribute(
				"info",
				id == null ? null : portalInfoService.findById(id,
						PortalInfo.ATTACHEMENT_TYPE_IMAGE,
						PortalInfo.ATTACHEMENT_TYPE_ATTACH));
		model.addAttribute("item",
				itemId == null ? null : portalItemService.getById(itemId));
		String parentId = request.getParameter("parentId");
		model.addAttribute("parentId", parentId);
		if (itemId != null) {
			Map<String, Object> sqlMap = Maps.newHashMapWithExpectedSize(2);
			sqlMap.put("itemId", itemId);
			sqlMap.put("type", PortalConstants.TAG_TYPE);
			model.addAttribute("infoTags", portalTagService.getAll(sqlMap));
		}
		if (id == null) {
			model.addAttribute("infoImages", Collections.EMPTY_LIST);
		} else {
			model.addAttribute("infoImages",
					protalInfoImageService.findByInfo(id));
		}
		model.addAttribute("staticRoot",resourceHolder.getStringValue("staticRoot"));
		model.addAttribute("adminRoot",resourceHolder.getStringValue("adminRoot"));
		model.addAttribute("imgRoot",resourceHolder.getStringValue("imgRoot"));
		return new ModelAndView("/portal/info/input");
	}

	@RequestMapping("/save")
	public void insert(HttpServletResponse response,
			@ModelAttribute PortalInfo pi, HttpServletRequest request,
			@RequestParam("imgFile") MultipartFile imgFile,
			@RequestParam("listImgFile") MultipartFile listImgFile,
			@RequestParam("tjImageFile") MultipartFile tjImageFile,
			@RequestParam("imgFiles") MultipartFile[] imgFiles,
			@RequestParam("imgName") String[] imgName,
			@RequestParam("attachements") MultipartFile[] attachements,
			@RequestParam("attachName") String[] attachName) {
		try {
			// 组装图片信息
			prepareInfoImage(pi, imgFile, listImgFile, tjImageFile);
			List<Attachement> images = attachementService.prepareAttachement(
					imgName, imgFiles);
			List<Attachement> attachFiles = attachementService
					.prepareAttachement(attachName, attachements);
			if (pi.getId() == 0L) {
				portalInfoService.insert(pi, images, attachFiles);
				String objId = request.getParameter("objId");
				if (StringUtils.isNotBlank(objId)) {
					saveOjeRel(objId, pi.getId());// 报存信息关联的对象
				}
			} else {
				portalInfoService.update(pi, images, attachFiles);
				String objId = request.getParameter("objId");
				if (StringUtils.isNotBlank(objId)) {
					saveOjeRel(objId, pi.getId());// 报存信息关联的对象
				}
			}
			AjaxUtils.renderText(response, String.valueOf(pi.getId()));
		} catch (Exception e) {
			logger.error("insert info error", e);
			e.printStackTrace();
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}

	}

	private void prepareInfoImage(PortalInfo pi, MultipartFile imgFile,
			MultipartFile listImgFile, MultipartFile tjImageFile)
			throws IOException {
		if (imgFile != null && !imgFile.isEmpty()) {
			String filePath = FileUtils.saveFile(imgFile.getInputStream(),
					resourceHolder.getStringValue("fileUploadPath"),
					imgFile.getOriginalFilename(), imgFile.getContentType());
			watermarkService.prepareWaterMark(
					PortalConstants.DEFAULT_WATERMARK, filePath);// 水印图片
			pi.setImage(filePath);
		}
		if (listImgFile != null && !listImgFile.isEmpty()) {
			String filePath = FileUtils.saveFile(listImgFile.getInputStream(),
					resourceHolder.getStringValue("fileUploadPath"),
					listImgFile.getOriginalFilename(), listImgFile.getContentType());
			pi.setListImage(filePath);
		}
		if (tjImageFile != null && !tjImageFile.isEmpty()) {
			String filePath = FileUtils.saveFile(tjImageFile.getInputStream(),
					resourceHolder.getStringValue("fileUploadPath"),
					tjImageFile.getOriginalFilename(), tjImageFile.getContentType());
			pi.setTjImage(filePath);
		}
	}

	// 针对链接信息的添加/修改
	@RequestMapping("/saveurl")
	public void saveurl(HttpServletResponse response,
			@ModelAttribute PortalInfo pi) {
		try {
			if (pi.getId() == 0L) {
				portalInfoService.insert(pi, null, null);
				pi.setStatus(PortalInfo.YES_STATUS);
				portalInfoService.update(pi);
			} else {
				portalInfoService.update(pi, null, null);
			}
			AjaxUtils.renderText(response, String.valueOf(pi.getId()));
		} catch (Exception e) {
			e.printStackTrace();
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}

	}

	@RequestMapping("/fabuInfo")
	public void fabuInfo(HttpServletResponse response,
			@ModelAttribute PortalInfo pi, HttpServletRequest request,
			@RequestParam("imgFile") MultipartFile imgFile,
			@RequestParam("listImgFile") MultipartFile listImgFile,
			@RequestParam("tjImageFile") MultipartFile tjImageFile,
			@RequestParam("imgFiles") MultipartFile[] imgFiles,
			@RequestParam("imgName") String[] imgName,
			@RequestParam("attachements") MultipartFile[] attachements,
			@RequestParam("imgName") String[] attachName) {
		try {
			// 组装图片信息
			prepareInfoImage(pi, imgFile, listImgFile, tjImageFile);
			pi.setStatus("pub");
			if (pi.getPublicTime() == null) {
				pi.setPublicTime(new Date());
			}
			pi.setPublicUser(AdminSessionHolder.getAdminUserId());
			portalInfoService.update(pi, attachementService.prepareAttachement(
					imgName, imgFiles), attachementService.prepareAttachement(
					attachName, attachements));
			String objId = request.getParameter("objId");
			if (StringUtils.isNotBlank(objId)) {
				saveOjeRel(objId, pi.getId());// 报存信息关联的对象
			}
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("fabuInfo info error", e);
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}

	/**
	 * 创建并发布信息
	 * 
	 * @param ppi
	 * @param response
	 */
	@RequestMapping("/doPubInfo")
	public void doPubInfo(@ModelAttribute PortalInfo ppi,
			HttpServletResponse response, HttpServletRequest request,
			@RequestParam("imgFile") MultipartFile imgFile,
			@RequestParam("listImgFile") MultipartFile listImgFile,
			@RequestParam("tjImageFile") MultipartFile tjImageFile,
			@RequestParam("imgFiles") MultipartFile[] imgFiles,
			@RequestParam("imgName") String[] imgName,
			@RequestParam("attachements") MultipartFile[] attachements,
			@RequestParam("imgName") String[] attachName) {
		try {
			ppi.setPublicUser(AdminSessionHolder.getAdminUserId());
			insert(response, ppi, request, imgFile, listImgFile, tjImageFile,
					imgFiles, imgName, attachements, attachName);
			// 此处只为修改一个发布状态，因为添加默认的是create
			ppi.setStatus("pub");
			if (ppi.getPublicTime() == null) {
				ppi.setPublicTime(new Date());
			}
			// ppi.setPublicTime(new Date());
			String objId = request.getParameter("objId");
			if (StringUtils.isNotBlank(objId)) {
				saveOjeRel(objId, ppi.getId());// 报存信息关联的对象
			}
			portalInfoService.update(ppi);
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("doPubInfo info error", e);
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}

	@RequestMapping("/getInfo")
	public void getInfo(HttpServletRequest request, HttpServletResponse response) {
		Long itemId = ToNumberUtils
				.getLongValue(request.getParameter("itemId"));
		AjaxUtils.renderJson(response,
				portalInfoService.findInfoByItemId(itemId));
	}

	// 根据信息id获取单条信息
	@RequestMapping("/findInfo/{id}")
	public void findInfo(@PathVariable("id") Long id,
			HttpServletResponse response) {
		PortalInfoDTO info = portalInfoService.findById(id);
		AjaxUtils.renderJson(response, info);
	}

	@RequestMapping("/delete/{id}")
	public void detele(HttpServletResponse response, @PathVariable("id") Long id) {
		try {
			portalInfoService.doDelete(id);
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		} catch (Exception e) {
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}

	/**
	 * 异步修改字段 比如推荐 是否首页等
	 */
	@RequestMapping("/updateRecommend")
	public void updateRecommend(Long id, Integer value,
			HttpServletResponse response) {
		try {
			if (CommonUtils.isIn(value, PortalInfo.RECOMMENT_NO,
					PortalInfo.RECOMMENT_YES)) {
				PortalInfo ppi = portalInfoService.getById(id);
				ppi.setIsRecommend(value);
				portalInfoService.update(ppi);
				AjaxUtils.renderText(response, CommonConstants.SUCCESS);
			} else {
				AjaxUtils.renderText(response, CommonConstants.FAIL);
			}
		} catch (Exception e) {
			logger.error("update recommend fail", e);
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}

	}

	/**
	 * 异步修改发布时间 比如置顶
	 */
	@RequestMapping("/setTopInfo/{id}")
	public void setTopInfo(@PathVariable("id") Long id,
			HttpServletResponse response) {
		try {

			PortalInfoDTO ppi = portalInfoService.findById(id, "");
			// ppi.setId(id);
			ppi.setIsRecommend(2);// 设为推荐
			ppi.setPublicTime(new Date());
			portalInfoService.update(ppi);
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);

		} catch (Exception e) {
			logger.error("update recommend fail", e);
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}

	// 报存信息关联的对象
	private void saveOjeRel(String objId, Long infoId) {
		if (StringUtils.isNotBlank(objId)) {
			InfoObjRel entity = new InfoObjRel();
			entity.setInfoId(infoId);
			entity.setObjId(Long.valueOf(objId));
			entity.setType(Integer.valueOf(PortalConstants.OBJTYPE));
			infoObjRelService.insert(entity);
		}
	}

	@RequestMapping("/getConfig")
	public void getConfig(HttpServletResponse response) {
		List<Option> entity = codesService
				.getCodes(PortalConstants.Code.INDEX_INFO);
		AjaxUtils.renderJson(response, entity);
	}

	// 获取信息选中的id
	@RequestMapping("/getSelected")
	public void getSelected(HttpServletResponse response,
			HttpServletRequest request) {
		String infoId = request.getParameter("infoId");
		List<Long> entity = infoObjRelService.getObjIdsByInfoId(Long
				.valueOf(infoId));
		AjaxUtils.renderText(response, entity.get(0).toString());
	}

}
