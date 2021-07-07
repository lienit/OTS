package com.cdtu.ots.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cdtu.ots.entity.Goods;
import com.cdtu.ots.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Map;

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

    /**
     * 打开商家管理页面
     *
     * @return 返回商家管理页面
     */
    @RequestMapping("/businessCenter")
    public String businessCenter() {
        return "/businessman/businessCenter";
    }

    /**
     * 获取商家商品信息
     *
     * @param dataPage 当前页数
     * @param pageSize 每页显示多少条数据
     * @return 返回商品信息JSON格式
     */
    @PostMapping("/getGoodsData")
    @ResponseBody
    public String getGoodsData(int dataPage, int pageSize) {
        int page = (dataPage - 1) * pageSize;
        ArrayList<Map<String, Object>> goodsArrayList;
        goodsArrayList = goodsMapper.findAllByStoreIdAndSize("123", page, pageSize);

        return JSON.toJSONString(goodsArrayList);
    }

    /**
     * 获取当前商家商品总个数
     *
     * @return 商品个数
     */
    @PostMapping("/getGoodsDataSize")
    @ResponseBody
    public int getGoodsDataSize() {
        return goodsMapper.findSizeByStoreId("123");
    }

    /**
     * 删除商品信息
     *
     * @param gId 商品id
     * @return 返回影响条目数
     */
    @PostMapping("/deleteGoodsData")
    @ResponseBody
    public int deleteGoodByGid(String gId) {
        int err = goodsMapper.deleteAllBygID(gId);
        return err;
    }

    /**
     * 修改商品信息
     * @param goods 获取修改后商品信息
     * @return 修改信息
     */
    @PostMapping("/updateGoodsData")
    @ResponseBody
    public int updateGoodsData(String goods){
        System.out.println(goods);
        JSONObject good_Obj = JSONObject.parseObject(goods);
        Goods _goods = new Goods(good_Obj.getInteger("gId"), good_Obj.getInteger("cId"),
                123, good_Obj.getString("gName"),
                good_Obj.getDouble("gPrice"), good_Obj.getString("gImage"),
                good_Obj.getString("gParameter"));
        int err = 0;
        try {
            err = goodsMapper.updateGoodsDataByGid(_goods);
        }catch (Exception e){
            e.printStackTrace();
        }

        return err;
    }

    @PostMapping("/addGoodsData")
    @ResponseBody
    public int addGoodsData(String goods){
        int err = 0;
        JSONObject good_Obj = JSONObject.parseObject(goods);
        Goods _goods = new Goods(good_Obj.getInteger("gCatId"), 123, good_Obj.getString("gName"),
                good_Obj.getDouble("gPrice"), good_Obj.getString("gImage"),
                good_Obj.getString("gParameter"), good_Obj.getInteger("gNumber"));

        try {
            err = goodsMapper.addGoodData(_goods);
        }catch (Exception e){
            e.printStackTrace();
        }
        return err;
    }

    @PostMapping("getCategoryData")
    @ResponseBody
    public String getCategoryData(){
        ArrayList<Map<String, Object>> categoryArrayList;
        categoryArrayList = goodsMapper.findAllCategory();

        return JSON.toJSONString(categoryArrayList);
    }
}
