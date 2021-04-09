package com.mycode.optionSet.controller;

import com.mycode.optionSet.service.OptionService;
import com.mycode.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 通用模块-选项设置
 */
@CrossOrigin
@RestController
@RequestMapping("/optionset")
public class OptionController {

    @Autowired
    private OptionService optionService;

    @RequestMapping("/getOptionPageList.do")
    public JsonResult<Object> getOptionPageList(){
        List<Map<String, Object>> mapList = optionService.getOptionPageList();
        return JsonResult.success(mapList);
    }

    @RequestMapping("/insertOption.do")
    public JsonResult<Object> insertOption(@RequestParam("name") String name){
        Map<String, Object> option = optionService.getOption(name);
        if(option !=null){
            return JsonResult.error("选项已存在！");
        }
        boolean bool = optionService.insertOption(name);
        if(!bool){
            return JsonResult.error("新增失败！");
        }
        return JsonResult.success("新增成功！",null);
    }

    @RequestMapping("/updateOption.do")
    public JsonResult<Object> updateOption(@RequestParam("code") Long code, @RequestParam("name") String name){
        Map<String, Object> option = optionService.getOption(name);
        if(option !=null){
            return JsonResult.error("选项已存在！");
        }
        boolean bool = optionService.updateOption(code,name);
        if(!bool){
            return JsonResult.error("更新失败！");
        }
        return JsonResult.success("更新成功！",null);
    }

    @RequestMapping("/deleteOption.do")
    public JsonResult<Object> deleteOption(@RequestParam("code") Long code){
        boolean bool = optionService.deleteOption(code);
        if(!bool){
            return JsonResult.error("删除失败！");
        }
        return JsonResult.success("删除成功！",null);
    }

}
