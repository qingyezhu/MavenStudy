package com.qingyezhu.spring.mapper;

import java.util.List;

import com.qingyezhu.spring.model.Area;

public interface AreaMapper {

	List<Area> queryArea();
	
	boolean saveArea(Area area);
}
