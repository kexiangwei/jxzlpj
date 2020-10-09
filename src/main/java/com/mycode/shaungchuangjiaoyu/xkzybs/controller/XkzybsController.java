package com.mycode.shaungchuangjiaoyu.xkzybs.controller;

import com.mycode.shaungchuangjiaoyu.xkzybs.domian.Xkzybs;
import com.mycode.shaungchuangjiaoyu.xkzybs.service.XkzybsService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 双创教育-学科专业比赛
 */
@CrossOrigin
@Controller
@RequestMapping("/scjy_xkzybs")
public class XkzybsController {

    @Autowired
    private XkzybsService xkzybsService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(Xkzybs xkzybs){
        Map<String, Object> resultMap = xkzybsService.getPageList(xkzybs);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(Xkzybs xkzybs){
        boolean bool = xkzybsService.insert(xkzybs);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(Xkzybs xkzybs){
        boolean bool = xkzybsService.update(xkzybs);
        if(!bool){
            return JsonResult.error("修改失败");
        }
        return JsonResult.success("修改成功",null);
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = xkzybsService.delete(code);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
    }

}
