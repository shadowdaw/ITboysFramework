package org.itboys.trade.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.itboys.trade.dao.WeixinConfigMapper;
import org.itboys.trade.entity.WeixinConfig;
import org.itboys.trade.entity.WeixinConfigExample;

public class TestService {

	WeixinConfigMapper weixinConfigMapper;
	
	public void test(){
		WeixinConfigExample example = new WeixinConfigExample();
		example.createCriteria().andAppIdIn(new ArrayList<String>()).andNameLike("%dfgdf")
		.andCreateTimeGreaterThan(new Date());
		List<WeixinConfig> list = weixinConfigMapper.selectByExample(example);
		
	}
}
