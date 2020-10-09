package com.mycode.shaungchuangjiaoyu.zdxscjcyxm.controller;

import com.mycode.shaungchuangjiaoyu.zdxscjcyxm.domian.Zdxscjcyxm;
import com.mycode.shaungchuangjiaoyu.zdxscjcyxm.service.ZdxscjcyxmService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 双创教育-指导学生参加创业项目
 */
@CrossOrigin
@Controller
@RequestMapping("/scjy_zdxscjcyxm")
public class ZdxscjcyxmController {

    @Autowired
    private ZdxscjcyxmService zdxscjcyxmService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(Zdxscjcyxm zdxscjcyxm){
        Map<String, Object> resultMap = zdxscjcyxmService.getPageList(zdxscjcyxm);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(Zdxscjcyxm zdxscjcyxm){
        boolean bool = zdxscjcyxmService.insert(zdxscjcyxm);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(Zdxscjcyxm zdxscjcyxm){
        boolean bool = zdxscjcyxmService.update(zdxscjcyxm);
        if(!bool){
            return JsonResult.error("修改失败");
        }
        return JsonResult.success("修改成功",null);
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = zdxscjcyxmService.delete(code);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
    }

}
