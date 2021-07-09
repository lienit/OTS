package com.cdtu.ots.service;

import com.cdtu.ots.entity.Address;
import com.cdtu.ots.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class AddressService {
    @Autowired
    private AddressMapper addressMapper;

    public Address findDefault(Boolean isDefault, int aUserid){
        Address aDefault = addressMapper.findDefault(isDefault, aUserid);
        return aDefault;
    };

    public ArrayList<Map<String, Object>> findAll(int aUserid){
        ArrayList<Map<String, Object>> all = addressMapper.findAll(aUserid);
        return all;
    }

    public int findAddressnum(int aUserid){
        int addressnum = addressMapper.findAddressnum(aUserid);
        return addressnum;
    }
    public Boolean updateAddress(Address address){
        Boolean aBoolean = addressMapper.updateAddress(address);
        return aBoolean;
    }

    public Boolean deleteAddress(int aId){
        Boolean aBoolean = addressMapper.deleteAddress(aId);
        return aBoolean;
    }

    public Boolean updateDefault(int aId,Boolean isDefault){
        Boolean aBoolean = addressMapper.updateDefault(aId, isDefault);
        return aBoolean;
    }

    public Boolean insertAddress(Address address){
        Boolean aBoolean = addressMapper.insertAddress(address);
        return aBoolean;
    }

}
