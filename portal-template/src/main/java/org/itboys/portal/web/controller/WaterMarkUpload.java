package org.itboys.portal.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.itboys.commons.utils.io.FileUtils;
import org.itboys.framework.resource.ResourceHolder;
import org.itboys.portal.service.KVConfigService;
import org.itboys.portal.service.WatermarkService;
import org.itboys.portal.tools.Exceptions;
import org.itboys.portal.tools.FileUploadController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 水印上传
 * @author huml
 *
 */
@RestController
public class WaterMarkUpload {
	
	@Autowired
	private WatermarkService watermarkService;
	@Autowired
	private KVConfigService kVConfigService;
	@Autowired
	private ResourceHolder resourceHolder;
	
	public String getFileUploadPath() {
		return resourceHolder.getStringValue("fileUploadPath");
	}

	public String getImageWebRoot() {
		return resourceHolder.getStringValue("imgRoot");
	}
	
	@RequestMapping(value = "/doUploadWithWaterMark")
	public void doUploadWithWaterMark(@RequestParam("imgFile") MultipartFile imgFile,
			@RequestParam(value="waterMark",required=false) String waterMark,
			HttpServletResponse response ){
		response.setContentType("text/html;charset=UTF-8");
		String fileUploadPath  = getFileUploadPath(); 
		String imageWebRoot  = getImageWebRoot();
		try{
			
			if(imgFile!=null && !imgFile.isEmpty()){
					String filePath = FileUtils.saveFile(imgFile.getInputStream(), fileUploadPath, imgFile.getOriginalFilename(), imgFile.getContentType());
					watermarkService.prepareWaterMark(waterMark, filePath);
					String imageUrl = imageWebRoot+filePath;
					response.getWriter().print(FileUploadController.SUCCESS_JSON.replace("{0}", imageUrl));
			}else{
				response.getWriter().print(FileUploadController.ERROR_JSON.replace("{0}", "请上传文件"));
			}
		}catch(Exception e){
			try {
				response.getWriter().print(FileUploadController.ERROR_JSON.replace("{0}", "上传失败"));
			} catch (IOException e1) {
				e.printStackTrace();
				throw Exceptions.unchecked(e1);
			}
		}
	}

	
}
