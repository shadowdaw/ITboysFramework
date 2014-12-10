package org.itboys.portal.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.itboys.commons.CommonConstants;
import org.itboys.commons.utils.ajax.AjaxUtils;
import org.itboys.commons.utils.common.CommonUtils;
import org.itboys.commons.utils.io.FileUtils;
import org.itboys.framework.query.JsonPageUtils;
import org.itboys.framework.resource.ResourceHolder;
import org.itboys.framework.spring.controller.BaseController;
import org.itboys.mongodb.utils.page.Page;
import org.itboys.portal.entity.Watermark;
import org.itboys.portal.service.WatermarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 水印 controller
 * @author huml
 */
@RestController
public class WatermarkController extends BaseController{

	@Autowired
	private WatermarkService watermarkService;
	@Autowired
	private ResourceHolder resourceHolder;
	
	/**
	 * 获取列表到页面
	 * @return
	 * OL
	 */
	@RequestMapping("/watermark/input")
	public ModelAndView watermark(Model model){
		return new ModelAndView("/watermark/list");
	}
	/**
	 * 异步查询列表
	 * @param response
	 * @param request
	 */
	@RequestMapping("/watermark/list")
	public void list(HttpServletResponse response,HttpServletRequest request){
		Page<Watermark> page = watermarkService.pageQuery(request);
		JsonPageUtils.renderJsonPage(page.getTotal(), page.getData(), response);
	}
	
	/*@RequestMapping("/watermark/lisByProject")
	public void lisByProject(HttpServletResponse response,HttpServletRequest request){
		final Map<String, Object> sqlMap = QueryParamUtils.builderQueryMap(request);
		sqlMap.put("projectId", SessionHolder.getProjectId());;
		List<Watermark>menus = watermarkService.list(sqlMap);
		JsonPageUtils.renderJsonPage(menus!=null&&menus.size()>0?menus.size():0L, menus, response);
	}*/
	
	/**
	 * 异步查询-根据id
	 * @param response
	 * @param request
	 */
	@RequestMapping("/watermark/getById")
	public void getById(HttpServletResponse response,HttpServletRequest request){
		String id = request.getParameter("id");
		if(StringUtils.isNotEmpty(id)){
			Watermark watermark = watermarkService.getById(Long.parseLong(id));
			AjaxUtils.renderJson(response, watermark);
		}
	}
	/**
	 * 新增(保存)水印
	 */
	@RequestMapping("/admin/watermark/save")
	public void save(@ModelAttribute Watermark entity,HttpServletRequest request,HttpServletResponse response,@RequestParam("watermarkPic") MultipartFile watermarkPic){
		try{
			if(entity.getType() == 1){
				if(null != watermarkPic && !watermarkPic.isEmpty()){
					if(watermarkPic.getSize()>0){
						String filePath = FileUtils.saveFile(watermarkPic.getInputStream(), resourceHolder.getStringValue("fileUploadPath"), watermarkPic.getOriginalFilename(), watermarkPic.getContentType());
						entity.setWatermark(filePath);
					}
				}
			}
			if(entity.getFlag()!=null&&entity.getFlag()!=4){
				entity.setX(-1);
				entity.setY(-1);
			}

			if(entity.getId()==0l){
				//entity.setProjectId(0L);//entity.setAdvertisementId(advertisementId);
				entity.setObjId(0L);
				watermarkService.save(entity);
			}else {
				watermarkService.update(entity);
			}
			AjaxUtils.renderText(response, CommonConstants.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("save project menu fail",e);
			AjaxUtils.renderText(response, CommonConstants.FAIL);
		}
	}
	/**
	 * 删除
	 */
	@RequestMapping("/admin/watermark/delete")
	public void deleteWatermark(@RequestParam long id,HttpServletResponse response){
		if(StringUtils.isNotEmpty(String.valueOf(id))){
			try{
				AjaxUtils.renderText(response, String.valueOf(watermarkService.delete(id)));
			}catch(Exception e){
				logger.error("deleteWatermark project menu fail",e);
				AjaxUtils.renderText(response,CommonConstants.FAIL);
			}
		}
	}
	
	@RequestMapping("/watermar/updateStatus")
	public void updateStatus(Long id,Integer value,HttpServletResponse response){
		try{
			if(CommonUtils.isIn(value, Watermark.STATUS_NO,Watermark.STATUS_YES)){
				Watermark wmark = new Watermark();
				wmark.setId(id);
				wmark.setStatus(value);
				watermarkService.update(wmark);
				AjaxUtils.renderText(response,CommonConstants.SUCCESS);
			}else{
				AjaxUtils.renderText(response, CommonConstants.FAIL);
			}
		}catch (Exception e) {
			logger.error("update status fail",e);
			AjaxUtils.renderText(response,CommonConstants.FAIL);
		}
		
	}
}
