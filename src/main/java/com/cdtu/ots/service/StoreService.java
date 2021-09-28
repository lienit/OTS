package com.cdtu.ots.service;

import com.cdtu.ots.entity.Store;
import com.cdtu.ots.mapper.StoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {
    @Autowired
    private StoreMapper storeMapper;

    public Store findBysId(int sId){
        Store bysId = storeMapper.findBysId(sId);
        return bysId;
    }
}
