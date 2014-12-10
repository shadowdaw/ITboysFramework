package org.itboys.product.service.order;

import java.util.List;

import org.itboys.product.dao.order.ReceiveAddressMapper;
import org.itboys.product.entity.mysql.ReceiveAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiveAddressBO {
	@Autowired
	private ReceiveAddressMapper receiveAddressMapper;
	
	public List<ReceiveAddress> getReceiveAddresses(Long memberId){
		return receiveAddressMapper.getAddress(memberId);
	}
	
	public ReceiveAddress getReceiveAddressById(Long id){
		return receiveAddressMapper.getReceiveAddressById(id);
	}
	
	public void insert(ReceiveAddress receiveAddress){
		if (receiveAddress.getProvinceId() == null) {
			receiveAddress.setProvinceId(0);
		}
		if(receiveAddress.getCityId() == null){					
			receiveAddress.setCityId(0);
		}
		if(receiveAddress.getAreaId() == null){					
			receiveAddress.setAreaId(0);
		}
		if(receiveAddress.getDistrictId() == null){										
			receiveAddress.setDistrictId(0);
		}
		 receiveAddressMapper.insert(receiveAddress);
	}
	
	public int update(ReceiveAddress receiveAddress){
		return receiveAddressMapper.update(receiveAddress);
	}
	
	public int delete(Integer id){
		return receiveAddressMapper.delete(id);
	}
	
	public List<ReceiveAddress> getByIds(List<Long>list){
		return receiveAddressMapper.getByIds(list);
	}
}