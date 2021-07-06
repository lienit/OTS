package com.cdtu.ots.mapper;

import com.cdtu.ots.entity.Goods;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * @ClassName GoodsMapper
 * @Description TODO
 * @Author mars_sea
 * @Date 2021/7/6
 **/
@Mapper
public interface GoodsMapper {

    @Select("select * from Goods where gStoreId = #{gStoreId}")
    public ArrayList<Goods> findAllByStoreId(@Param("gStoreId") String gStoreId);

    @Delete("delete from Goods where gId = #{gId}")
    public int deleteAllBygID(@Param("gId") String gId);
}
