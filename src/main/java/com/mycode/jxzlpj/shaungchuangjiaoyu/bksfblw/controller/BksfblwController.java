package com.mycode.jxzlpj.shaungchuangjiaoyu.bksfblw.controller;

import com.mycode.jxzlpj.shaungchuangjiaoyu.bksfblw.domian.Bksfblw;
import com.mycode.jxzlpj.shaungchuangjiaoyu.bksfblw.service.BksfblwService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 双创教育-本科生发表论文
 */
@CrossOrigin
@Controller
@RequestMapping("/scjy_bksfblw")
public class BksfblwController {

    @Autowired
    private BksfblwService bksfblwService;

    @ResponseBody
    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(Bksfblw bksfblw){
        Map<String, Object> resultMap = bksfblwService.getPageList(bksfblw);
        return JsonResult.success(resultMap);
    }

    @ResponseBody
    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(Bksfblw bksfblw){
        boolean bool = bksfblwService.insert(bksfblw);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功",null);
    }

    @ResponseBody
    @RequestMapping("/update.do")
    public JsonResult<Object> update(Bksfblw bksfblw){
        boolean bool = bksfblwService.update(bksfblw);
        if(!bool){
            return JsonResult.error("修改失败");
        }
        return JsonResult.success("修改成功",null);
    }

    @ResponseBody
    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = bksfblwService.delete(code);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功",null);
    }

}
