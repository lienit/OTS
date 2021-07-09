package com.cdtu.ots.service;

import com.cdtu.ots.entity.Goods;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdtu.ots.mapper.GoodsMapper;

import java.util.ArrayList;
import java.util.Map;

@Service
public class GoodsService {
    @Autowired
    private  GoodsMapper goodsMapper;

    public ArrayList<Map<String, Object>> findAll(int  page, int size){
        ArrayList<Map<String, Object>> all = goodsMapper.findAll(page, size);
        return all;
    }
    public int findSize(){
        int size = goodsMapper.findSize();
        return size;
    }

    public Goods findByGId(int gId){
        Goods byGId = goodsMapper.findByGId(gId);
        return byGId;
    }
}
