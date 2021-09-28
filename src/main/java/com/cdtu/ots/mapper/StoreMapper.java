package com.cdtu.ots.mapper;

import com.cdtu.ots.entity.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StoreMapper {
    @Select("select * from store where sId=#{sId}")
    public Store findBysId(int sId);
}
