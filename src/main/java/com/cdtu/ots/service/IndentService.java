package com.cdtu.ots.service;

import com.cdtu.ots.entity.Indent;
import com.cdtu.ots.mapper.IndentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Service
public class IndentService {
    @Autowired
    private IndentMapper indentMapper;


    public ArrayList<Map<String, Object>> findByStoreGoods(int iUserId,int page, int size){
        ArrayList<Map<String, Object>> byStoreGoods = indentMapper.findByStoreGoods(iUserId, page, size);
        return byStoreGoods;
    }

    public String findIndentCount(int iUserId){
        String indentCount = indentMapper.findIndentCount(iUserId);
        return indentCount;
    }

    public boolean updateIndentUser(String iId){
        boolean b = indentMapper.updateIndentUser(iId);
        return b;
    }

    public boolean insertIndent(Indent indent){
        boolean b = indentMapper.insertIndent(indent);
        return b;
    }

}
