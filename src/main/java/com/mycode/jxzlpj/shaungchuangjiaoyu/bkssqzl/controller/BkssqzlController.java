package com.mycode.jxzlpj.shaungchuangjiaoyu.bkssqzl.controller;

import com.mycode.jxzlpj.shaungchuangjiaoyu.bkssqzl.domian.Bkssqzl;
import com.mycode.jxzlpj.shaungchuangjiaoyu.bkssqzl.service.BkssqzlService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 双创教育-本科生申请专利
 */
@CrossOrigin
@Controller
@RequestMapping("/scjy_bkssqzl")
public class BkssqzlController {

    @Autowired
    private BkssqzlService bkssqzlService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(Bkssqzl bkssqzl){
        Map<String, Object> resultMap = bkssqzlService.getPageList(bkssqzl);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(Bkssqzl bkssqzl){
        boolean bool = bkssqzlService.insert(bkssqzl);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(Bkssqzl bkssqzl){
        boolean bool = bkssqzlService.update(bkssqzl);
        if(!bool){
            return JsonResult.error("修改失败");
        }
        return JsonResult.success("修改成功",null);
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = bkssqzlService.delete(code);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
    }

}
