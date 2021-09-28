package com.cdtu.ots.controller;

import com.alibaba.fastjson.JSON;
import com.cdtu.ots.entity.Address;
import com.cdtu.ots.entity.Goods;
import com.cdtu.ots.entity.Indent;
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
    private IndentService indentService;


    /**
     * 访问具体商品页面
     * @param gId
     * @return
     */
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
    /**
     * 获取用户收货地址
     */
    @PostMapping("/getUserAddress")
    public String getUserAddress(String userId){
        System.out.println("userId="+userId);
        return  null;
    }

    /**
     * 从Redis数据库中获取对应商品信息
     * @return
     */
    @PostMapping("/getParticular")
    @ResponseBody
    public String getParticular(){
        RedisUtil redisUtil = new RedisUtil();
        Jedis redis = redisUtil.getRedis();
        String goods = redis.get("goods");
        System.out.println(goods);
        return goods;
    }

    /**
     * 从Redis数据库中获取对应商家信息
     * @return
     */
    @PostMapping("/getStoreInfo")
    @ResponseBody
    public String getStore(){
        RedisUtil redisUtil = new RedisUtil();
        Jedis redis = redisUtil.getRedis();
        String store= redis.get("store");
        return store;
    }

    /**
     * 用户提交订单
     */
    @PostMapping("/commitIndet")
    @ResponseBody
    public String commitIndent(Indent indentList){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        indentList.setiDate(simpleDateFormat.format(new Date()));
        boolean b = indentService.insertIndent(indentList);
        System.out.println(b);
        return "";
    }

}
