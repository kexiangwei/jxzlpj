package com.mycode.jxzlpj.shaungchuangjiaoyu.zdxscy.controller;

import com.mycode.jxzlpj.shaungchuangjiaoyu.zdxscy.domian.Zdxscy;
import com.mycode.jxzlpj.shaungchuangjiaoyu.zdxscy.service.ZdxscyService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 双创教育-指导学生创业
 */
@CrossOrigin
@Controller
@RequestMapping("/scjy_zdxscy")
public class ZdxscyController {

    @Autowired
    private ZdxscyService zdxscyService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(Zdxscy zdxscy){
        Map<String, Object> resultMap = zdxscyService.getPageList(zdxscy);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(Zdxscy zdxscy){
        boolean bool = zdxscyService.insert(zdxscy);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(Zdxscy zdxscy){
        boolean bool = zdxscyService.update(zdxscy);
        if(!bool){
            return JsonResult.error("修改失败");
        }
        return JsonResult.success("修改成功",null);
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = zdxscyService.delete(code);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
    }

}
