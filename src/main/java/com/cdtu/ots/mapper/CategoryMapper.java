package com.cdtu.ots.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface CategoryMapper {
    @Select("select * from category")
    public List<Map<String, Object>> findAll();
}
