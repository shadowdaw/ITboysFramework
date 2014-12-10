package org.itboys.product.service;

import org.itboys.product.dao.product.ProductMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductTest extends BaseDAOTest {
	@Autowired
	private ProductMapper productMapper;
	@Override
	protected String[] getSqlFiles() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Test
	public void list(){
		System.out.println(productMapper.count(null));
	}

}
