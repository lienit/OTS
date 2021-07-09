package com.cdtu.ots.mapper;

import com.alibaba.fastjson.JSONObject;
import com.cdtu.ots.entity.Goods;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.Map;

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
    @Select("select * from Goods g, category c where gStoreId = #{gStoreId} AND g.gCatId = c.cId LIMIT #{page},#{size}")
    public ArrayList<Map<String, Object>> findAllByStoreIdAndSize(@Param("gStoreId") String gStoreId, @Param("page") int page,
                                                                  @Param("size") int size);

    @Select("select * from Goods limit #{page},#{size}")
    public ArrayList<Map<String, Object>> findAll(int  page, int size);

    @Select("select * from goods where gId=#{gId}")
    public Goods findByGId(int gId);
    /**
     * 查询商家共多少件商品
     * @param gStoreId 商家id
     * @return int 总数
     */
    @Select("select count(gId) from Goods where gStoreId = #{gStoreId}")
    public int findSizeByStoreId(@Param("gStoreId") String gStoreId);

    @Select("select count(gId) from Goods ")
    public int findSize();

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
            "gPrice = #{goods.gPrice}, gParameter = #{goods.gParameter}" +
            "where gId = #{goods.gId}")
    public int updateGoodsDataByGid(@Param("goods") Goods goods);

    @Insert("INSERT INTO Goods (gCatId, gStoreId, gName, gPrice, gImage, gParameter, gNumber) " +
            "VALUES(#{goods.gCatId}, #{goods.gStoreId}, #{goods.gName},#{goods.gPrice}," +
            "#{goods.gImage}, #{goods.gParameter}, #{goods.gNumber})")
    public int addGoodData(@Param("goods") Goods goodData);

    /**
     * 查询所有商品类别
     * @return list map商品类别信息
     */
    @Select("select * from category")
    public ArrayList<Map<String, Object>> findAllCategory();

    /**
     * 更具用户输入对内容进行商品查询
     * @param searchIn 用户输入的内容
     * @param gStoreId 用户商家的id
     * @return 商品数组
     */
    @Select("select * from Goods g, category c where gStoreId = #{gStoreId} and (gName like #{searchIn}" +
            "or gParameter like #{searchIn}) and g.gCatId = c.cId")
    public ArrayList<Map<String, Object>> findAllGoodsBySearchIn(@Param("searchIn") String searchIn,
                                                                 @Param("gStoreId") String gStoreId);

    /**
     * 更新商品图片
     * @param gStoreId 商家id
     * @param gImage 图片名称
     * @return 影响条目数
     */
    @Update("update Goods set gImage = #{gImage} where gStoreId = #{gStoreId} and gId = #{gId}")
    public int updateGoodImage(@Param("gStoreId") String gStoreId,
                               @Param("gId") String gId,
                               @Param("gImage") String gImage);

    @Select("select sId from store where sUserId = #{sUserId}")
    public String useuIdGetgStoreId(@Param("sUserId") String sUserId);
}
