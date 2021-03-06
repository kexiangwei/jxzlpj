package com.mycode.jxzlpj.jiaoxuesheji.skjh.controller;

import com.alibaba.fastjson.JSON;
import com.mycode.common.shenheSet.domain.ShenHeItem;
import com.mycode.common.shenheSet.service.ShenHeService;
import com.mycode.jxzlpj.jiaoxuesheji.skjh.domian.Skjh;
import com.mycode.jxzlpj.jiaoxuesheji.skjh.domian.SkjhItem;
import com.mycode.jxzlpj.jiaoxuesheji.skjh.service.SkjhService;
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
 * 教学设计-授课计划
 */
@CrossOrigin
@Controller
@RequestMapping("/skjh")
public class SkjhController {

    @Autowired
    private SkjhService skjhService;
    @Autowired
    private ShenHeService shenHeService;

    /**
     *
     * @param skjh
     * @return
     */
    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(Skjh skjh){
        Map<String, Object> resultMap = skjhService.getPageList(skjh);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/getSkjhItemList.do")
    public JsonResult<Object> getSkjhItemList(@RequestParam("relationCode") String relationCode){
        List<SkjhItem> skjhItemList = skjhService.getSkjhItemList(relationCode);
        return JsonResult.success(skjhItemList);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(SkjhItem skjhItem){ //必须继承Skjh，用于接收页面传递的父类属性值
        boolean bool = skjhService.insert(skjhItem);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    /*@ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(Skjh skjh){
        boolean bool = skjhService.update(skjh);
        if(!bool){
            return JsonResult.error("修改失败");
        }
        return JsonResult.success("修改成功",null);
    }*/

    /*@ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = skjhService.delete(code);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
    }*/

    /**
     * 提交，支持批量提交
     * @param menuId 用于获取当前处于激活状态的审核流程编号
     * @param jsonStr
     * @return
     */
    @ResponseBody
    @RequestMapping("/toSubimt.do")
    public JsonResult<Object> toSubimt(@RequestParam("menuId") Integer menuId,@RequestParam("jsonStr") String jsonStr){
        //
        String activeShenheCode = shenHeService.getActiveShenheCode(menuId);
        if(StringUtils.isEmpty(activeShenheCode)){
            return JsonResult.error("未设置审核流程");
        }
        //
        List<Skjh> skjhList = JSON.parseArray(jsonStr, Skjh.class);
        boolean bool = skjhService.toSubimt(activeShenheCode,skjhList);
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
    public JsonResult<Object> toShenhe(ShenHeItem item, @RequestParam("jsonStr") String jsonStr){
        List<Skjh> skjhList = JSON.parseArray(jsonStr, Skjh.class);
        boolean bool = skjhService.toShenhe(item,skjhList);
        if(!bool){
            return JsonResult.error("审核失败");
        }
        return JsonResult.success("审核成功",null);
    }
}
