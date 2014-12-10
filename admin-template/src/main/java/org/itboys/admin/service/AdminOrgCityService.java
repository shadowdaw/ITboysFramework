package org.itboys.admin.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.itboys.admin.dto.EasyUiTreeDO;
import org.itboys.admin.entity.AdminOrgCity;
import org.itboys.admin.tools.AdminSessionHolder;
import org.itboys.admin.tools.EasyUiTreeHelper;
import org.itboys.commons.dto.IdValueOption;
import org.itboys.mongodb.core.MongoDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.code.morphia.query.Query;
import com.google.common.collect.Lists;

@Service
public class AdminOrgCityService extends BaseAdminService<AdminOrgCity,Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8592840059506884127L;
	@Autowired
	private CityService cityService;
	@Resource(name="adminDS")
	private MongoDataSource adminOrgCityDataSource;
	
	@Override
	protected MongoDataSource getMongoDataSource() {
		return adminOrgCityDataSource;
	}

	@Override
	protected Class<AdminOrgCity> getEntityClass() {
		return AdminOrgCity.class;
	}
	
	public List<Long> getAdminOrgCitys(Long orgId){
		Query<AdminOrgCity> query = adminOrgCityDataSource.createQuery(getEntityClass())
				.filter("objId", orgId).filter("type", 1);
		List<Long> ids = new ArrayList<>();
		if(query != null){
			for(AdminOrgCity adminOrgCity : query.asList()){
				ids.add(adminOrgCity.getCityId());
			}
		}
		return ids;
	}
	
	public List<Long> getAdminUserCitys(Long userId){
		
		Query<AdminOrgCity> query = adminOrgCityDataSource.createQuery(getEntityClass())
				.filter("objId", userId).filter("type", 2);
		List<Long> ids = new ArrayList<>();
		if(query != null){
			for(AdminOrgCity adminOrgCity : query.asList()){
				ids.add(adminOrgCity.getId());
			}
		}
		return ids;
	}
	
	public List<IdValueOption> getUserCitysForSel(Long userId){
		List<Long> cityIds = getAdminUserCitys(userId);
		List<IdValueOption> list = Lists.newArrayListWithCapacity(cityIds.size());
		for(Long cityId:cityIds){
			String cityname = cityService.getCityFullName(cityId);
			if(StringUtils.isNotBlank(cityname)){
				IdValueOption o = new IdValueOption();
				o.setId(cityId);
				o.setValue(cityname);
				list.add(o);
			}
		}
		return list;
	}
	
	public List<EasyUiTreeDO> getUserCityTree(Long orgId,Long userId){
		List<Long> orgCityIds = getAdminOrgCitys(orgId);
		List<Long> userCityIds = getAdminUserCitys(userId);
		List<EasyUiTreeDO> tree = Lists.newArrayList();
		List<IdValueOption> _provinces = cityService.getProvinces();
		for(IdValueOption p:_provinces){
			if(orgCityIds.contains(p.getId())){
				EasyUiTreeDO pt = new EasyUiTreeDO();
				pt.setId(p.getId());
				pt.setText(p.getValue());
				List<IdValueOption> _districts = cityService.getDistricts(p.getId());
				for(IdValueOption d:_districts){
					if(orgCityIds.contains(d.getId())){
						EasyUiTreeDO dt = new EasyUiTreeDO();
						dt.setId(d.getId());
						dt.setText(d.getValue());
						List<IdValueOption> _citys = cityService.getCitys(d.getId());
						for(IdValueOption c:_citys){
							if(orgCityIds.contains(c.getId())){
								EasyUiTreeDO ct = new EasyUiTreeDO();
								ct.setId(c.getId());
								ct.setText(c.getValue());
								if(userCityIds.contains(c.getId())){
									ct.setChecked(true);
								}
								dt.getChildren().add(ct);
							}
						}
						if (userCityIds.contains(p.getId())
								&& EasyUiTreeHelper.isAllChecked(dt.getChildren())){
							dt.setChecked(true);
						}
						pt.getChildren().add(dt);
					}
				}
				if(userCityIds.contains(p.getId()) && EasyUiTreeHelper.isAllChecked(pt.getChildren())){
					pt.setChecked(true);
				}
				tree.add(pt);
			}
		}
		return tree;
	}
	
	public void doRealOrg(Long orgId,List<Long> cityIds){
//		cityIds=cityService.mergeParentIds(cityIds);
		List<AdminOrgCity> ocs = new ArrayList<AdminOrgCity>(cityIds.size());
		for(Long cityId:cityIds){
			AdminOrgCity aoc=new AdminOrgCity();
			aoc.setCityId(cityId);
			aoc.setObjId(orgId);
			aoc.setType(AdminOrgCity.TYPE_O);
			AdminSessionHolder.prepareAdminLoginData(aoc);
			ocs.add(aoc);
		}
		List<AdminOrgCity> list = findByField("objId", orgId);
		for(AdminOrgCity adminOrgCity : list){
			delete(adminOrgCity.getId());
		}
		batchSave(ocs);
	}
	
	public void doRealUser(Long userId,List<Long> cityIds){
		cityIds=cityService.mergeParentIds(cityIds);
		List<AdminOrgCity> ocs = new ArrayList<AdminOrgCity>(cityIds.size());
		for(Long cityId:cityIds){
			AdminOrgCity aoc=new AdminOrgCity();
			aoc.setCityId(cityId);
			aoc.setObjId(userId);
			aoc.setType(AdminOrgCity.TYPE_U);
			AdminSessionHolder.prepareAdminLoginData(aoc);
			ocs.add(aoc);
		}
		List<AdminOrgCity> list = findByField("objId", userId);
		for(AdminOrgCity adminOrgCity : list){
			update(adminOrgCity.getId()
					, "isDeleted", 1);
		}
		batchSave(ocs);
	}

}
