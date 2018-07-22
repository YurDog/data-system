package com.data.system.mapper;

import com.data.system.po.BaseArea;
import java.util.List;

import org.springframework.stereotype.Repository;
@Repository
public interface BaseAreaMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(BaseArea record);

	BaseArea selectByPrimaryKey(Integer id);

	List<BaseArea> selectAll();

	int updateByPrimaryKey(BaseArea record);
}