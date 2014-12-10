package org.itboys.admin.dao;

import java.util.List;

import org.itboys.admin.entity.City;

/**
 * 省市县区DAO层
 * 
 * @author ChenJunhui
 */
public interface CityMapper {

	public List<City> getAll();

	public City getCity(Long id);

	public List<City> getCityByName(String name);

	public List<City> getCityByFullName(String fullName);

	public List<City> getCItyList();

	public List<City> getCitysByParentId(long id); 
}
