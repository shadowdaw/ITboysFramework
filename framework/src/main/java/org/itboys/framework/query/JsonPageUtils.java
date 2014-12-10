package org.itboys.framework.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.itboys.commons.utils.ajax.AjaxUtils;
import org.itboys.framework.resource.ResourceHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class JsonPageUtils {
	@Autowired
	private static ResourceHolder resourceHolder;

	public static final String COUNT_KEY = resourceHolder.getStringValue("countKey");
	public static final String COUNT_ROWS = resourceHolder.getStringValue("rows");
	
	/**
	 * 异步render分页的json结果
	 * @param count
	 * @param result
	 * @param response
	 */
	public  static <T>  void renderJsonPage(Long count,List<T> result,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String, Object>(3);
		map.put(COUNT_KEY, count);
		map.put(COUNT_ROWS, result);
		AjaxUtils.renderJson(response, map);
	}
	
	/**
	 * 异步render分页的json结果
	 * @param count
	 * @param result
	 * @param response
	 */
	public  static <T>  void renderJsonPage(Long count,T[] result,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String, Object>(3);
		map.put(COUNT_KEY, count);
		int length = result==null?0:result.length;
		List<T> list = new ArrayList<T>(length);
		if(result!=null){
			for(int i = 0; i < length; i++){
				list.add(result[i]);
			}
		}
		map.put(COUNT_ROWS, list);
		AjaxUtils.renderJson(response, map);
	}
}
