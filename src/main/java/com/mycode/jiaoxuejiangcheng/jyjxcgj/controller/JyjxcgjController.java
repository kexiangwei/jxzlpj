package com.mycode.jiaoxuejiangcheng.jyjxcgj.controller;

import com.alibaba.fastjson.JSON;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.service.ShenHeService;
import com.mycode.jiaoxuejiangcheng.jyjxcgj.domian.Jyjxcgj;
import com.mycode.jiaoxuejiangcheng.jyjxcgj.service.JyjxcgjService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 教学奖惩-教育教学成果奖
 */
@CrossOrigin
@Controller
@RequestMapping("/jyjxcgj")
public class JyjxcgjController {

    @Autowired
    private JyjxcgjService service;
    @Autowired
    private ShenHeService shenHeService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(Jyjxcgj obj){
        Map<String, Object> resultMap = service.getPageList(obj);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(Jyjxcgj obj){
        boolean bool = service.insert(obj);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(Jyjxcgj obj){
        boolean bool = service.update(obj);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("objCode") String objCode){
        boolean bool = service.delete(objCode);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }

    /**
     * 提交，支持批量提交
     * @param menuId 用于获取当前处于激活状态的审核流程编号
     * @param jsonStr
     * @return
     */
    @ResponseBody
    @RequestMapping("/toSubimt.do")
    public JsonResult<Object> toSubimt(@RequestParam("menuId") Integer menuId,@RequestParam("jsonStr") String jsonStr){
        String activeShenheCode = shenHeService.getActiveShenheCode(menuId);
        if(StringUtils.isEmpty(activeShenheCode)){
            return JsonResult.error("未设置审核流程");
        }
        List<Jyjxcgj> objList = JSON.parseArray(jsonStr, Jyjxcgj.class);
        boolean bool = service.toSubimt(activeShenheCode,objList);
        if(!bool){
            return JsonResult.error("提交失败");
        }
        return JsonResult.success("提交成功",null);
    }

    /**
     *  审核，支持批量审核
     * @param item
     * @return
     */
    @ResponseBody
    @RequestMapping("/toShenhe.do")
    public JsonResult<Object> toShenhe(ShenHeItem item,@RequestParam("jsonStr") String jsonStr){
        List<Jyjxcgj> objList = JSON.parseArray(jsonStr, Jyjxcgj.class);
        boolean bool = service.toShenhe(item,objList);
        if(!bool){
            return JsonResult.error();
        }
        return JsonResult.success();
    }
}
