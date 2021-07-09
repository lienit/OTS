package com.cdtu.ots.Controller;

import com.alibaba.fastjson.JSON;
import com.cdtu.ots.entity.Goods;
import com.cdtu.ots.service.GoodsService;
import com.cdtu.ots.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StroeController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/detailedSto")
    public String detailedSto(int gId){
        Goods byGId = goodsService.findByGId(gId);
        String s = JSON.toJSONString(byGId);

        RedisUtil redisUtil = new RedisUtil();
        Jedis redis = redisUtil.getRedis();

        redis.set("goods", s);


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


}
