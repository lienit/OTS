package com.cdtu.ots.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cdtu.ots.entity.Goods;
import com.cdtu.ots.entity.User;
import com.cdtu.ots.mapper.GoodsMapper;
import com.cdtu.ots.mapper.IndentMapper;
import com.cdtu.ots.util.FileUtil;
import com.cdtu.ots.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    IndentMapper indentMapper;

    /**
     * 打开商家管理页面
     *
     * @return 返回商家管理页面
     */
    @RequestMapping("/businessCenter")
    public String businessCenter() {
        return "/businessman/businessCenter";
    }

    @RequestMapping("/businessChart")
    public String businessChart() {
        return "/businessman/businessChart";
    }

    @RequestMapping("/businessOrderManagement")
    public String businessOrderManagement() {
        return "/businessman/businessOrderManagement";
    }

    /**
     *
     */
    @PostMapping("/findBySotreId")
    @ResponseBody
    public String findBySotreId(HttpServletRequest request){
        String gStoreId = UseuIdGetgStoreId(request);
        System.out.println("过的痕迹复合地基"+gStoreId);
        return gStoreId;
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
    public String getGoodsData(int dataPage, int pageSize, HttpServletRequest request) {
        String gStoreId = UseuIdGetgStoreId(request);
        int page = (dataPage - 1) * pageSize;
        ArrayList<Map<String, Object>> goodsArrayList;
        goodsArrayList = goodsMapper.findAllByStoreIdAndSize(gStoreId, page, pageSize);

        return JSON.toJSONString(goodsArrayList);
    }

    /**
     * 获取当前商家商品总个数
     *
     * @return 商品个数
     */
    @PostMapping("/getGoodsDataSize")
    @ResponseBody
    public int getGoodsDataSize(HttpServletRequest request) {
        String gStoreId = UseuIdGetgStoreId(request);
        return goodsMapper.findSizeByStoreId(gStoreId);
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
    public int updateGoodsData(String goods, HttpServletRequest request){
        request.getSession(false);
        String gStoreId = UseuIdGetgStoreId(request);
        System.out.println(Integer.getInteger(gStoreId)+"");
        JSONObject good_Obj = JSONObject.parseObject(goods);
        Goods _goods = new Goods(good_Obj.getInteger("gId"), good_Obj.getInteger("cId"),
                Integer.parseInt(gStoreId), good_Obj.getString("gName"),
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
    public int addGoodsData(String goods, HttpServletRequest request){
        String gStoreId = UseuIdGetgStoreId(request);
        int err = 0;
        System.out.println("sffdassdfasdfasdfsdfsdf"+goods);
        JSONObject good_Obj = JSONObject.parseObject(goods);
                Goods _goods = new Goods(good_Obj.getInteger("gCatId"), Integer.parseInt(gStoreId),
                good_Obj.getString("gName"),
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

    @PostMapping("/searchGoods")
    @ResponseBody
    public String searchGoods( String searchIn, HttpServletRequest request){
        String gStoreId = UseuIdGetgStoreId(request);
        String err = "1";
        try {
            err = JSON.toJSONString(goodsMapper.findAllGoodsBySearchIn("%"+searchIn+"%", gStoreId));
        }catch (Exception e){
            e.printStackTrace();
        }
        return err;
    }

    @RequestMapping("/uploadFile")
    @ResponseBody
    public int uploadFile(MultipartFile file, String gStoreId, String gId){
        // 图片文件类型
        String contentType = file.getContentType();
        // 图片名字
        String fileName =gStoreId + file.getOriginalFilename();
        //文件存放路径
        String filePath = "E:\\code\\OTS\\src\\main\\resources\\static\\image";
        System.out.println(gId);
        int err = 0;
        try {
            //文件处理
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
            err = goodsMapper.updateGoodImage(gStoreId,gId,fileName);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        // 返回图片的存放路径
        System.out.println("图片存放路径"+err);
        return err;
    }

    /**
     * test password
     * @param p password
     * @return md5 password
     */
    @RequestMapping("/test")
    @ResponseBody
    public String test(String p) {
        String md5 = MD5Util.getMD5(p);
        return md5;
    }


    public String UseuIdGetgStoreId(HttpServletRequest request){
        request.getSession(false);
        User user = (User) request.getSession().getAttribute("user");
        String gStoreId = goodsMapper.useuIdGetgStoreId(user.getuId()+"");
        System.out.println("gsdds"+gStoreId);
        return gStoreId;
    }

    @PostMapping("/getIndentData")
    @ResponseBody
    public String getIndentData(HttpServletRequest request){
        String gStoreId = UseuIdGetgStoreId(request);
        ArrayList<Map<String, Object>> data = indentMapper.getEventDayIndent(gStoreId);
        ArrayList<String> time = new ArrayList<>();
        ArrayList<Object> number = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            time.add(data.get(i).get("time").toString());
            number.add(data.get(i).get("number"));
        }
        JSONObject chartData = new JSONObject();
        chartData.put("number", number);
        chartData.put("time", time);

        return JSON.toJSONString(chartData);
    }

    @PostMapping("/getCategoryIndent")
    @ResponseBody
    public String getCategoryIndent(HttpServletRequest request){
        String gStoreId = UseuIdGetgStoreId(request);
        ArrayList<Map<String, Object>> data = indentMapper.getCategoryIndent(gStoreId);
        ArrayList<String> name = new ArrayList<>();
        ArrayList<Object> number = new ArrayList<>();
        ArrayList<Object> price = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            name.add(data.get(i).get("cName").toString());
            number.add(data.get(i).get("number"));
            price.add(data.get(i).get("price"));
        }
        JSONObject chartData = new JSONObject();
        chartData.put("number", number);
        chartData.put("name", name);
        chartData.put("price", price);

        return JSON.toJSONString(chartData);
    }

    /**
     * 获取商家订单信息
     *
     * @param dataPage 当前页数
     * @param pageSize 每页显示多少条数据
     * @return 返回订单信息JSON格式
     */
    @PostMapping("/getIndentDatas")
    @ResponseBody
    public String getIndentDatas(int dataPage, int pageSize, HttpServletRequest request) {
        String gStoreId = UseuIdGetgStoreId(request);
        int page = (dataPage - 1) * pageSize;
        ArrayList<Map<String, Object>> indetArrayList = indentMapper.getIndentInfo(gStoreId, page, pageSize);

        return JSON.toJSONString(indetArrayList);
    }

    /**
     * 获取当前商家订单总个数
     *
     * @return 订单个数
     */
    @PostMapping("/getIndentDataSize")
    @ResponseBody
    public int getIndentDataSize(HttpServletRequest request) {
        String gStoreId = UseuIdGetgStoreId(request);
        return indentMapper.findSizeByStoreId(gStoreId);
    }

    @PostMapping("/updateIndentData")
    @ResponseBody
    public int updateIndentData(String indent){

        JSONObject indent_Obj = JSONObject.parseObject(indent);
        int err = 0;
        try {
            err = indentMapper.updateIndentDataByGid(indent_Obj.getString("iAddress"),
                    indent_Obj.getString("iState"), indent_Obj.getDouble("iPrice"),
                    indent_Obj.getString("sId"), indent_Obj.getString("iId"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return err;

    }
}
