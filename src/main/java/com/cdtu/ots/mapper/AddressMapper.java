package com.cdtu.ots.mapper;

import com.cdtu.ots.entity.Address;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.Map;

@Mapper
public interface AddressMapper {
    @Select("select * from address where isDefault = #{isDefault} and aUserid =#{aUserid}")
    public Address findDefault(Boolean isDefault, int aUserid);

    @Select("select * from address where aUserid=#{aUserid} order by isDefault desc")
    public ArrayList<Map<String, Object>> findAll(int aUserid);

    @Select("select count(aId) from address where aUserid=#{aUserid}")
    public int findAddressnum(int aUserid);

    @Update("update address set isDefault = #{isDefault} where aId = #{aId}")
    public Boolean updateDefault(int aId,Boolean isDefault);

    @Update("update address set aAddress=#{aAddress},aPostcode=#{aPostcode},aConsignee=#{aConsignee}, aPhone=#{aPhone},  isDefault=#{isDefault} where aUserid = #{aUserid} and aId=#{aId}")
    public Boolean updateAddress(Address address);

    @Delete("delete from address where aId=#{aId}")
    public Boolean deleteAddress(int aId);

    @Insert("insert into address(aUserid,aAddress,aPostcode,aConsignee,aPhone,isDefault) values(#{aUserid},#{aAddress},#{aPostcode},#{aConsignee},#{aPhone},#{isDefault})")
    public Boolean insertAddress(Address address);
}
