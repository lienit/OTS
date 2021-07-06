package com.cdtu.ots.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cdtu.ots.entity.Goods;
import com.cdtu.ots.mapper.GoodsMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * @ClassName BusinessManController
 * @Description 商家管理页面的功能实现
 * @Author mars_sea
 * @Date 2021/7/2
 **/
@Controller
public class BusinessManController {

    @Autowired
    GoodsMapper goodsMapper;

    @RequestMapping("/businessCenter")
    public String businessCenter(){
        return "/businessman/businessCenter";
    }

    @PostMapping("/getGoodsData")
    @ResponseBody
    public String getGoodsData(){
        ArrayList<Goods> goodsArrayList;
        goodsArrayList = goodsMapper.findAllByStoreId("123");
        return JSON.toJSONString(goodsArrayList);
    }

    @PostMapping("/deleteGoodsData")
    @ResponseBody
    public int deleteGoodByGid(String gId){
        int err = goodsMapper.deleteAllBygID(gId);
        return err;
    }
}
