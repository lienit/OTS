package com.cdtu.ots.mapper;

import com.cdtu.ots.entity.Indent;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.Map;

/**
 * @ClassName IndentMapper
 * @Description TODO
 * @Author mars_sea
 * @Date 2021/7/8
 **/
@Mapper
public interface IndentMapper {

    @Select("SELECT  COUNT(i.iId) number, sum(i.iPrice) price, i.iDate time\n" +
            "from indent i, `user` u, category c, goods g, store s \n" +
            "WHERE i.iUserId = u.uId AND i.iCatId = c.cId AND i.iGoodsId = g.gId AND s.sId = #{sId} GROUP BY i.iDate")
    public ArrayList<Map<String, Object>> getEventDayIndent(@Param("sId") String sId);

    /**
     * 订单查询
     * @param iUserId
     * @return
     */
    @Select("select * from goods g, store s, indent i where i.iSotreId=s.sId and i.iGoodsId = g.gId and i.iUserId=#{iUserId} limit #{page},#{size}")
    public ArrayList<Map<String, Object>> findByStoreGoods(int iUserId,int page, int size);

    /**
     * 查询用户订单数量
     * @param
     * @return
     */
    @Select("select count(iUserId) from goods g, store s, indent i where i.iSotreId=s.sId and i.iGoodsId = g.gId and i.iUserId=#{iUserId}")
    public String findIndentCount(int iUserId);

    @Select("SELECT  COUNT(i.iId) number, sum(i.iPrice) price , c.cName \n" +
            "from indent i, `user` u, category c, goods g, store s \n" +
            "WHERE i.iUserId = u.uId AND i.iCatId = c.cId AND i.iGoodsId = g.gId AND s.sId = #{sId} GROUP BY c.cId")
    public ArrayList<Map<String, Object>> getCategoryIndent(@Param("sId") String sId);

    @Select("SELECT i.iId, i.iAddress, i.iMessage, i.iPrice, i.iDate, u.userName, c.cName, g.gName, s.sId ,i.iState\n" +
            "from indent i, `user` u, category c, goods g, store s \n" +
            "WHERE i.iUserId = u.uId AND i.iCatId = c.cId AND i.iGoodsId = g.gId AND s.sId = #{sId} LIMIT #{page},#{size}")
    public ArrayList<Map<String, Object>> getIndentInfo(@Param("sId") String sId, @Param("page") int page,@Param("size") int size);

    /**
     * 查询商家共多少订单
     * @param gStoreId 商家id
     * @return int 总数
     */
    @Select("SELECT COUNT(iId) FROM indent WHERE iSotreId = #{gStoreId};")
    public int findSizeByStoreId(@Param("gStoreId") String gStoreId);

    @Update("UPDATE indent SET iAddress = #{iAddress},iState = #{iState}, iPrice = #{iPrice} WHERE iSotreId = #{iSotreId} and iId = #{iId}")
    public int updateIndentDataByGid(@Param("iAddress") String iAddress, @Param("iState") String iState,
                                     @Param("iPrice") Double iPrice,
                                     @Param("iSotreId") String iSotreId, @Param("iId") String iId);

    /**
     * 修改用户订单状态
     * @param iId
     * @return
     */
    @Update("update indent set iState = 2 where iId=#{iId}")
    public boolean updateIndentUser(String iId);

    @Insert("insert into indent(iUserId,iSotreId,iCatId,iGoodsId,iAddress,iMessage,iPrice,iState,iDate) values(#{iUserId},#{iSotreId},#{iCatId},#{iGoodsId},#{iAddress},#{iMessage},#{iPrice},#{iState},#{iDate})")
    public boolean insertIndent(Indent indent);
}
