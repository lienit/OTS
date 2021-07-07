package com.cdtu.ots.mapper;

import com.cdtu.ots.entity.Goods;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

/**
 * @ClassName GoodsMapper
 * @Description TODO
 * @Author mars_sea
 * @Date 2021/7/6
 **/
@Mapper
public interface GoodsMapper {

    /**
     * 更具商家当前页面查询商品信息
     * @param gStoreId 商家id
     * @param page  当前页数
     * @param size  每页显示条目数
     * @return 物品list
     */
    @Select("select * from Goods where gStoreId = #{gStoreId} LIMIT #{page},#{size}")
    public ArrayList<Goods> findAllByStoreIdAndSize(@Param("gStoreId") String gStoreId, @Param("page") int page,
                                             @Param("size") int size);

    /**
     * 查询商家共多少件商品
     * @param gStoreId 商家id
     * @return int 总数
     */
    @Select("select count(gId) from Goods where gStoreId = #{gStoreId}")
    public int findSizeByStoreId(@Param("gStoreId") String gStoreId);

    /**
     * 删除商品信息
     * @param gId 商品id
     * @return 成功影响条目数
     */
    @Delete("delete from Goods where gId = #{gId}")
    public int deleteAllBygID(@Param("gId") String gId);

    /**
     * 修改商品信息通过商品id
     * @param goods 商品实体类信息
     * @return 成功影响条目数
     */
    @Update("UPDATE Goods SET gCatId = #{goods.gCatId}, gName = #{goods.gName}, gImage = #{goods.gImage}, " +
            "gPrice = #{goods.gPrice}, gParameter = #{goods.gParameter}, gCategory = #{goods.gCategory}" +
            "where gId = #{goods.gId}")
    public int updateGoodsDataByGid(@Param("goods") Goods goods);

    @Insert("INSERT INTO Goods (gCatId, gStoreId, gName, gPrice, gImage, gParameter) " +
            "VALUES(#{goods.gCatId}), #{goods.gStoreId}), #{goods.gName}),#{goods.gPrice})," +
            "#{goods.gImage}), #{goods.gParameter})")
    public int addGoodData(@Param("goods") Goods goodData);
}
