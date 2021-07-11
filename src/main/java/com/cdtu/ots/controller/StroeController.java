package com.cdtu.ots.controller;

import com.alibaba.fastjson.JSON;
import com.cdtu.ots.entity.Address;
import com.cdtu.ots.entity.Goods;
import com.cdtu.ots.entity.Store;
import com.cdtu.ots.service.AddressService;
import com.cdtu.ots.service.GoodsService;
import com.cdtu.ots.service.IndentService;
import com.cdtu.ots.service.StoreService;
import com.cdtu.ots.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class StroeController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private IndentService indentService;

    @RequestMapping("/detailedSto")
    public String detailedSto(int gId){
        Goods byGId = goodsService.findByGId(gId);

        Store bysId = storeService.findBysId(byGId.getgStoreId());
        String s1 = JSON.toJSONString(bysId);
        String s = JSON.toJSONString(byGId);


        RedisUtil redisUtil = new RedisUtil();
        Jedis redis = redisUtil.getRedis();

        redis.set("goods", s);
        redis.set("store",s1);


        return "/user/detailedSto";
    }

    @PostMapping("/getParticular")
    @ResponseBody
    public String getParticular(){
        RedisUtil redisUtil = new RedisUtil();
        Jedis redis = redisUtil.getRedis();
        String goods = redis.get("goods");
        System.out.println(goods);
        return goods;
    }

    @PostMapping("/getStoreInfo")
    @ResponseBody
    public String getStore(){
        RedisUtil redisUtil = new RedisUtil();
        Jedis redis = redisUtil.getRedis();
        String store= redis.get("store");
        return store;
    }

    @PostMapping("/setIndent")
    @ResponseBody
    public String setIndent(int iUserId,int iSotreId, int iCatId, int iGoodsId, Double iPrice){
        Address aDefault = addressService.findDefault(true, iUserId);
        String address = aDefault.getaAddress();
        String format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        indentService.insertINdent(iUserId,iSotreId,iCatId,iGoodsId,iPrice, address, format);

        return "success";
    }


}
