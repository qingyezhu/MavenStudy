package com.qingyezhu.spring.config.mapper;

import java.util.List;

import com.qingyezhu.spring.config.model.Area;

public interface AreaMapper {

	List<Area> queryArea();
	
	boolean saveArea(Area area);
}
