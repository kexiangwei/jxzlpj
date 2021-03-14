package com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.controller;

import com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.domain.JiaoGaiXiangMuCountry;
import com.mycode.jxzlpj.jiaoxueyanjiu.jiaogaixiangmu.service.JiaoGaiXiangMuCountryService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/jxyj_jgxm_country")
public class JiaoGaiXiangMuCountryController {

    @Autowired
    private JiaoGaiXiangMuCountryService jiaoGaiXiangMuCountryService;

    @RequestMapping("/getPageList.do")
    public JsonResult<Object> getPageList(JiaoGaiXiangMuCountry jiaoGaiXiangMuCountry){
        Map<String, Object> resultMap = jiaoGaiXiangMuCountryService.getPageList(jiaoGaiXiangMuCountry);
        return JsonResult.success(resultMap);
    }

    @RequestMapping("/insert.do")
    public JsonResult<Object> insert(JiaoGaiXiangMuCountry jiaoGaiXiangMuCountry){
        boolean bool = jiaoGaiXiangMuCountryService.insert(jiaoGaiXiangMuCountry);
        if(!bool){
            return JsonResult.error("新增失败");
        }
        return JsonResult.success("新增成功", null);
    }

    @RequestMapping("/update.do")
    public JsonResult<Object> update(JiaoGaiXiangMuCountry jiaoGaiXiangMuCountry){
        boolean bool = jiaoGaiXiangMuCountryService.update(jiaoGaiXiangMuCountry);
        if(!bool){
            return JsonResult.error("修改失败");
        }
        return JsonResult.success("修改成功", null);
    }

    @RequestMapping("/delete.do")
    public JsonResult<Object> delete(@RequestParam("code") String code){
        boolean bool = jiaoGaiXiangMuCountryService.delete(code);
        if(!bool){
            return JsonResult.error("删除失败");
        }
        return JsonResult.success("删除成功", null);
    }
}
