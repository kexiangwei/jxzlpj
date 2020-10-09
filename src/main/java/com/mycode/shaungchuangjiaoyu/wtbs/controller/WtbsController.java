package com.mycode.shaungchuangjiaoyu.wtbs.controller;

import com.mycode.shaungchuangjiaoyu.wtbs.domian.Wtbs;
import com.mycode.shaungchuangjiaoyu.wtbs.service.WtbsService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 双创教育-文、体类比赛
 */
@CrossOrigin
@Controller
@RequestMapping("/scjy_wtbs")
public class WtbsController {

    @Autowired
    private WtbsService wtbsService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(Wtbs wtbs){
        Map<String, Object> resultMap = wtbsService.getPageList(wtbs);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(Wtbs wtbs){
        boolean bool = wtbsService.insert(wtbs);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(Wtbs wtbs){
        boolean bool = wtbsService.update(wtbs);
        if(!bool){
            return JsonResult.error("修改失败");
        }
        return JsonResult.success("修改成功",null);
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = wtbsService.delete(code);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
    }

}
