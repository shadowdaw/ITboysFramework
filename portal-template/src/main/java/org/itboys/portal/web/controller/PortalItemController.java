package org.itboys.portal.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.StringUtils;
import org.itboys.admin.dto.EasyUiTreeDO;
import org.itboys.commons.CommonConstants;
import org.itboys.commons.utils.ajax.AjaxUtils;
import org.itboys.commons.utils.io.FileUtils;
import org.itboys.framework.query.JsonPageUtils;
import org.itboys.framework.resource.ResourceConfig;
import org.itboys.framework.resource.ResourceHolder;
import org.itboys.framework.spring.controller.BaseController;
import org.itboys.mongodb.utils.page.Page;
import org.itboys.portal.constant.PortalConstants;
import org.itboys.portal.entity.PortalItem;
import org.itboys.portal.service.PortalItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@RestController
@RequestMapping(value = "/portal/item")
public class PortalItemController extends BaseController {
	@Autowired
	private PortalItemService portalItemService;
	@Autowired
	private ResourceHolder resourceHolder;

	/**
	 * 加载所有栏目页面上显示
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public void list(HttpServletResponse response, HttpServletRequest request) {
		Page<PortalItem> page = portalItemService.pageQuery(request);// 组装page对象
		JsonPageUtils.renderJsonPage(page.getTotal(), page.getData(), response);
	}

	@RequestMapping(value="/save",method=RequestMethod.POST)
	public void insert(HttpServletResponse response, @ModelAttribute PortalItem ppi,
			@RequestParam(value="xyzz") MultipartFile xyzz) {
		try {
			if (xyzz != null && !xyzz.isEmpty()) {
				String filePath = FileUtils.saveFile(xyzz.getInputStream(),
						resourceHolder.getStringValue("fileUploadPath"),
						xyzz.getOriginalFilename(), xyzz.getContentType());
				ppi.setImage(filePath);
			}
			if (ppi.getId() == 0L) {
				portalItemService.insert(ppi);
			} else {
				portalItemService.update(ppi);
			}

			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}

	@RequestMapping("/getItem/{id}")
	public void getItem(@PathVariable("id") Long id,
			HttpServletResponse response) {
		AjaxUtils.renderJson(response, portalItemService.getById(id));
	}

	@RequestMapping("/delete")
	public void delete(HttpServletResponse response, HttpServletRequest request) {
		try {
			Long id = Long.valueOf(request.getParameter("id"));
			portalItemService.doDelete(id);
			AjaxUtils.renderJson(response, 1);
		} catch (Exception e) {
			AjaxUtils.renderJson(response, 0);
		}
	}

	@RequestMapping("/update")
	public void update(HttpServletResponse response,
			@ModelAttribute PortalItem ppi) {
		try {
			portalItemService.update(ppi);
			AjaxUtils.renderJson(response, 1);
		} catch (Exception e) {
			AjaxUtils.renderJson(response, 0);
		}
	}

	@RequestMapping("/items/{id}")
	public ModelAndView item(@PathVariable("id") Long id, Model model) {
		PortalItem item = portalItemService.getById(id);
		model.addAttribute("item", item);
		model.addAttribute("staticRoot",resourceHolder.getStringValue("staticRoot"));
		model.addAttribute("adminRoot",resourceHolder.getStringValue("adminRoot"));
		model.addAttribute("imgRoot",resourceHolder.getStringValue("webRoot"));
		return new ModelAndView("/portal/vip/infos");
	}

	@RequestMapping("/getTreeItem")
	public void getTreeItem(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		List<PortalItem> itemList = portalItemService.findAll(sqlMap);
		String parentId = request.getParameter("parentId");
		List<PortalItem> roots = Lists.newArrayList();
		if (StringUtils.isNotBlank(parentId)) {
			roots = portalItemService.getChilidsByParentid(Long
					.valueOf(parentId));
		} else {
			roots = portalItemService.getRootsItem(itemList);
		}
		List<EasyUiTreeDO> tree = Lists.newArrayListWithCapacity(roots.size());
		for (PortalItem item : roots) {
			EasyUiTreeDO menuTree = new EasyUiTreeDO();
			menuTree.setId(item.getId());
			menuTree.setText(item.getName());
			tree.add(menuTree);
		}

		List<EasyUiTreeDO> endTree = new ArrayList<EasyUiTreeDO>();
		for (EasyUiTreeDO eachRootTree : tree) {
			EasyUiTreeDO eachTree = fn(eachRootTree, itemList);
			endTree.add(eachTree);
		}
		AjaxUtils.renderJson(response, tree);
	}

	/**
	 * 递归无限极树:在外面循环每个顶级节点时调用此递归 eachRootTree:每个顶级节点 all:所有参与递归列表
	 * 
	 * @param request
	 * @param response
	 */
	public EasyUiTreeDO fn(EasyUiTreeDO eachRootTree, List<PortalItem> all) {
		List<PortalItem> childList = portalItemService
				.getChilidsByParentid(eachRootTree.getId());
		if (childList.size() > 0) {
			List<EasyUiTreeDO> newTreeList = new ArrayList<EasyUiTreeDO>();
			for (PortalItem pCategory : childList) {
				EasyUiTreeDO tree = new EasyUiTreeDO();
				tree.setId(pCategory.getId());
				tree.setText(pCategory.getName());
				fn(tree, all);
				newTreeList.add(tree);
			}
			eachRootTree.setChildren(newTreeList);
		}
		return eachRootTree;
	}

	@SuppressWarnings("unused")
	private void prepareChildren(List<PortalItem> itemList,
			List<EasyUiTreeDO> tree) {
		for (EasyUiTreeDO uitree : tree) {
			for (PortalItem citem : itemList) {
				if (citem.getParentId().equals(uitree.getId())) {
					EasyUiTreeDO menuTree = new EasyUiTreeDO();
					menuTree.setId(citem.getId());
					menuTree.setText(citem.getName());
					uitree.getChildren().add(menuTree);
					if (citem.getIsLeaf() != PortalConstants.ItemParam.IS_LEAF) {
						prepareChildren(itemList, uitree.getChildren());// 递归
					}
				}
			}
		}
	}

	@RequestMapping("/checkcode")
	public void checkCode(HttpServletRequest request,
			HttpServletResponse response) {
		String code = request.getParameter("codes");
		Map<String, Object> sqlMap = Maps.newHashMapWithExpectedSize(1);
		sqlMap.put("code", code);
		Long count = portalItemService.count(sqlMap);
		AjaxUtils.renderText(response, count.toString());
	}
}
