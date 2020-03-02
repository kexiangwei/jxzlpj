package com.mycode.shaungchuangjiaoyu.xkzybs.controller;

import com.alibaba.fastjson.JSON;
import com.mycode.common.shenhe.domain.ShenHeItem;
import com.mycode.common.shenhe.service.ShenHeService;
import com.mycode.shaungchuangjiaoyu.Stu;
import com.mycode.shaungchuangjiaoyu.xkzybs.domian.Xkzybs;
import com.mycode.shaungchuangjiaoyu.xkzybs.service.XkzybsService;
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
 * 双创教育-学科专业比赛
 */
@CrossOrigin
@Controller
@RequestMapping("/xkzybs")
public class XkzybsController {

    @Autowired
    private XkzybsService xkzybsService;
    @Autowired
    private ShenHeService shenHeService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(Xkzybs obj){
        Map<String, Object> resultMap = xkzybsService.getPageList(obj);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(Xkzybs obj){
        boolean bool = xkzybsService.insert(obj);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(Xkzybs obj){
        boolean bool = xkzybsService.update(obj);
        if(!bool){
            return JsonResult.error("修改失败");
        }
        return JsonResult.success("修改成功",null);
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("objCode") String objCode){
        boolean bool = xkzybsService.delete(objCode);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
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
        List<Xkzybs> objList = JSON.parseArray(jsonStr, Xkzybs.class);
        boolean bool = xkzybsService.toSubimt(activeShenheCode,objList);
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
        List<Xkzybs> objList = JSON.parseArray(jsonStr, Xkzybs.class);
        boolean bool = xkzybsService.toShenhe(item,objList);
        if(!bool){
            return JsonResult.error("审核失败");
        }
        return JsonResult.success("审核成功",null);
    }

    @ResponseBody
    @RequestMapping("/getStuInfo.do")
    public JsonResult<Object> getStuInfo(@RequestParam("relationCode") String relationCode){
        List<Stu> mapList = xkzybsService.getStuInfo(relationCode);
        return JsonResult.success(mapList);
    }

    @ResponseBody
    @RequestMapping("/addStuInfo.do")
    public JsonResult<Object> addStuInfo(Stu stu){
        boolean bool = xkzybsService.addStuInfo(stu);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    @ResponseBody
    @RequestMapping("/delStuInfo.do")
    public JsonResult<Object> delStuInfo(@RequestParam("relationCode") String relationCode, @RequestParam("stuCode") String stuCode){
        boolean bool = xkzybsService.delStuInfo(relationCode, stuCode);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
    }

}
