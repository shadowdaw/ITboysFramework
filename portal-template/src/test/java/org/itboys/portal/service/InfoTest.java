package org.itboys.portal.service;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;

public class InfoTest extends BaseServiceTest {
	
	@Autowired
	private PortalInfoService portalInfoService;
	@Test
	public void test() {
		Map<String, Object> param = Maps.newHashMap();
		System.out.println(portalInfoService.list(param).size());
	}

}
